package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

//public class Search extends AppCompatActivity {
//    ImageButton home;
//    ImageButton edit;
//    ImageButton search;
//    ImageButton menu;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
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
//                Intent intentEdit=new Intent(Search.this,Edit.class);
//                startActivity(intentEdit);
//                Toast.makeText(Search.this, "edit", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Search.this,MainActivity.class);
//                startActivity(intentEdit);
//                Toast.makeText(Search.this, "search", Toast.LENGTH_SHORT).show();
//            }
//        });
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Search.this,Menu.class);
//                startActivity(intentEdit);
//                Toast.makeText(Search.this, "menu", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    ImageButton home;
    ImageButton edit;
    ImageButton search;
    ImageButton menu;
    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addView();
        addEvents();
        rcvUser=findViewById(R.id.rcv_users);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        userAdapter =new UserAdapter(getListUser());
        rcvUser.setAdapter(userAdapter);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(itemDecoration);
        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });




    }

    private void filterList(String newText) {
        List<User> filterdList=new ArrayList<>();
        for(User user:getListUser()){
            if(user.getFullName().toLowerCase().contains(newText.toLowerCase())){
                filterdList.add(user);
            }
        }
        if(filterdList.isEmpty()){
            Toast.makeText(this,"No data Found",Toast.LENGTH_SHORT).show();
        }else{
            userAdapter.setFilteredList(filterdList);
        }
    }

    private List<User> getListUser() {
        List<User> list=new ArrayList<>();
        list.add(new User(R.drawable.sena,"715105107","Van A","k71.E2","2003-7-26"));
        list.add(new User(R.drawable.sena,"715105107","Van B","k71.E2","2003-7-26"));

        return list;
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

                startActivity(new Intent(Search.this, Edit.class));
                Toast.makeText(Search.this, "edit", Toast.LENGTH_SHORT).show();

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Search.this, MainActivity.class));
                Toast.makeText(Search.this, "home", Toast.LENGTH_SHORT).show();

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Search.this, Menu.class));
                Toast.makeText(Search.this, "menu", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
