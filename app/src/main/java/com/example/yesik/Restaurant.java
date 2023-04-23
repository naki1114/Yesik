package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Restaurant extends AppCompatActivity {

    String tag;

    Button restaurantRegister;
    Button menuRegister;
    Button manageTable;
    Button logout;

    SharedPreferences autoLogin;

    static final String USER_INFO_SPLIT = "UserInfoSplit";
    static final String AUTO_LOGIN_CHECK = "Auto Login Check";

    long finishTime = 1000;
    long pressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        tag = "식당회원 페이지";
        Log.v(tag, "onCreate() 호출됨");

        restaurantRegister = findViewById(R.id.restaurantRegister);
        menuRegister = findViewById(R.id.menuRegister);
        manageTable = findViewById(R.id.manageTable);
        logout = findViewById(R.id.logout);

        autoLogin = getSharedPreferences(USER_INFO_SPLIT, MODE_PRIVATE);

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
                SharedPreferences.Editor editor = autoLogin.edit();

                editor.putString(AUTO_LOGIN_CHECK, "x");
                editor.commit();

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

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - pressTime;

        if (0 <= intervalTime && finishTime >= intervalTime)
        {
            finishAffinity();
        }
        else
        {
            pressTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }

}