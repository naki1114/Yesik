package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Login_Page extends AppCompatActivity {

    Button joinButton;
    Button loginButton;

    RadioGroup memberCheck;
    RadioButton restaurantButton;
    RadioButton personButton;

    ArrayList idRestaurant;
    ArrayList pwRestaurant;
    ArrayList idPerson;
    ArrayList pwPerson;

    String tag;

    int memberRestaurant;
    int memberPerson;

    int member_check;

    Intent putIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        tag = "로그인 페이지";
        Log.v(tag, "onCreate() 호출됨");

        member_check = 0;

        joinButton = findViewById(R.id.joinButton);
        loginButton = findViewById(R.id.loginButton);

        memberCheck = findViewById(R.id.memberCheck);

        restaurantButton = findViewById(R.id.restaurantMember);
        personButton = findViewById(R.id.personalMember);

        memberCheck.check(-1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(tag, "Login_Page.onStart() 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(tag, "Login_Page.onResume() 호출됨");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (restaurantButton.isChecked()) {
                    putIntent = new Intent(Login_Page.this, Restaurant.class);
                    startActivity(putIntent);
                }
                else if (personButton.isChecked()) {
                    putIntent = new Intent(Login_Page.this, Personal.class);
                    startActivity(putIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "회원 종류를 선택하세요.", Toast.LENGTH_LONG).show();
                }

            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putIntent = new Intent(Login_Page.this, Join_Page.class);
                startActivity(putIntent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(tag, "Login_Page.onPause() 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(tag, "Login_Page.onStop() 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(tag, "Login_Page.onDestroy() 호출됨");
    }

}