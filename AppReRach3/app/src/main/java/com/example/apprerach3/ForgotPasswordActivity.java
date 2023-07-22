package com.example.apprerach3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText editTextTenEmailQuenMK,editTextNhapMKQuenMK,editTextNhapLaiMatKhauQuenMK,editTextOTPEmailQuenMK;
    Button btnThayDoi,btnQuayLaiQuenMK,btnMaOTP;
    int content;
    boolean checkEmail = false;
    Switch btnSwitch;
    LinearLayout layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        layout = findViewById(R.id.layoutBGR);
        btnSwitch = findViewById(R.id.btnSwitch);
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(btnSwitch.isChecked()){
                    layout.setBackground(getResources().getDrawable(R.drawable.bgr_login));
                }
                else{
                    layout.setBackground(getResources().getDrawable(R.drawable.bgr_register));
                }
            }
        });
        addView();
        btnQuayLaiQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailOld = editTextTenEmailQuenMK.getText().toString().trim();
                String newPassWord = editTextNhapMKQuenMK.getText().toString().trim();
                String newPassWordAgain = editTextNhapLaiMatKhauQuenMK.getText().toString().trim();
                String maOTP = editTextOTPEmailQuenMK.getText().toString().trim();

                if (emailOld.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (maOTP.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (newPassWord.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPassWordAgain.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Vui lòng nhập lại password", Toast.LENGTH_SHORT).show();
                    return;
                }
                ApiService apiServices = RetrofitClient.getRetrofitInstance().create(ApiService.class);
                Call<List<User>> callGet = apiServices.getUsers();
                callGet.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        List<User> users = response.body();
                        for (User user : users) {
                            if( user.getEmail().equals(emailOld) ){
                               checkEmail = true;
                               break;
                            }
                            else {
                                checkEmail = false;
                            }
                        }
                        if(newPassWordAgain.equals(newPassWord) && checkEmail == true && maOTP.equals(String.valueOf(content))){
                            User user = new User(emailOld, newPassWord);
                            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//                        Log.d("TAG", "Email: " + user.getEmail() + ", Password: " + user.getPassword());
                            Call<Void> callPut = apiService.fogotpassword(user);
                            callPut.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(ForgotPasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ForgotPasswordActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    // Xử lý khi có lỗi xảy ra
                                    Toast.makeText(ForgotPasswordActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            Toast.makeText(ForgotPasswordActivity.this, "Mật khẩu không trùng khớp hoặc email không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });
            }
        });
        btnMaOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void addView() {
        editTextTenEmailQuenMK = findViewById(R.id.editTextTenEmailQuenMK);
        editTextNhapMKQuenMK = findViewById(R.id.editTextNhapMKQuenMK);
        editTextNhapLaiMatKhauQuenMK = findViewById(R.id.editTextNhapLaiMatKhauQuenMK);
        btnThayDoi = findViewById(R.id.btnThayDoi);
        btnQuayLaiQuenMK = findViewById(R.id.btnQuayLaiQuenMK);
        editTextOTPEmailQuenMK=findViewById(R.id.editTextOTPEmailQuenMK);
        btnMaOTP=findViewById(R.id.btnMaOTP);
    }
    private void sendEmail(){
        try {
            String fromEmail ="ndhung2003x@gmail.com";
            String emailPassword ="bvhvcgpxevqahcff";
            String toEmail =editTextTenEmailQuenMK.getText().toString().trim();
            String subject = "Đây là mã OTP của bạn";
            content = generateOTP();
            String host ="smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host",host);
            properties.put("mail.smtp.port",465);
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.auth","true");
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail,emailPassword);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(String.valueOf(content));
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            Toast.makeText(ForgotPasswordActivity.this,"Mã OTP đã được gửi tới Email của bạn" ,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ForgotPasswordActivity.this,"Có lỗi",Toast.LENGTH_SHORT).show();
        }
    }

    private int generateOTP() {
        return (int) Math.floor(((Math.random() * 899999) + 100000));
    }
}
