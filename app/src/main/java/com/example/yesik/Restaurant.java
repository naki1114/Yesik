package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;

public class Restaurant extends AppCompatActivity {

    String tag;

    Button restaurantRegister;
    Button menuRegister;
    Button tableRegister;
    Button manageTable;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        tag = "식당회원 페이지";
        Log.v(tag, "onCreate() 호출됨");

        restaurantRegister = findViewById(R.id.restaurantRegister);
        menuRegister = findViewById(R.id.menuRegister);
        tableRegister = findViewById(R.id.tableRegister);
        manageTable = findViewById(R.id.manageTable);
        logout = findViewById(R.id.logout);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(tag, "onStart() 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(tag, "onResume() 호출됨");

        restaurantRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Restaurant.this, Restaurant_Register.class);
                startActivity(intent);
            }
        });

        menuRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Restaurant.this, Menu_Register.class);
                startActivity(intent);
            }
        });

        tableRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Restaurant.this, Table_Register.class);
                startActivity(intent);
            }
        });

        manageTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Restaurant.this, Manage_Table.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Restaurant.this, Login_Page.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(tag, "onPause() 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(tag, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(tag, "onDestroy()");
    }

}