package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

//public class Menu extends AppCompatActivity {
//    ImageButton home;
//    ImageButton edit;
//    ImageButton search;
//    ImageButton menu;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//    }
//    private void addView(){
//        home=findViewById(R.id.home);
//        edit=findViewById(R.id.edit);
//        search=findViewById(R.id.search);
//        menu=findViewById(R.id.menu);
//    }
//    private void addEvents(){
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Menu.this,Edit.class);
//                startActivity(intentEdit);
//                Toast.makeText(Menu.this, "edit", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Menu.this,Search.class);
//                startActivity(intentEdit);
//                Toast.makeText(Menu.this, "search", Toast.LENGTH_SHORT).show();
//            }
//        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Menu.this,MainActivity.class);
//                startActivity(intentEdit);
//                Toast.makeText(Menu.this, "menu", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    ImageButton home;
    ImageButton edit;
    ImageButton search;
    ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        addView();
        addEvents();
    }

    private void addView(){
        home=findViewById(R.id.home);
        edit=findViewById(R.id.edit);
        search=findViewById(R.id.search);
        menu=findViewById(R.id.menu);
    }

    private void addEvents(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Edit.class));
                Toast.makeText(Menu.this, "edit", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Search.class));
                Toast.makeText(Menu.this, "search", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, MainActivity.class));
                Toast.makeText(Menu.this, "home", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        addEvents();
    }
}
