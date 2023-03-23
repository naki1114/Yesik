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
    RadioButton resButton;
    RadioButton perButton;

    ArrayList id_res;
    ArrayList pw_res;
    ArrayList id_per;
    ArrayList pw_per;

    String tag;

    int member_res;
    int member_per;

    int member_check;

    Intent putIntent;
    Intent getIntent;

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

        resButton = findViewById(R.id.restaurantMember);
        perButton = findViewById(R.id.personalMember);

        memberCheck.check(-1);

        getIntent = getIntent();
        member_res = getIntent.getIntExtra("Member_Res",0);
        member_per = getIntent.getIntExtra("Member_Per",0);

        if (member_res == 0) {
            id_res = new ArrayList();
            pw_res = new ArrayList();
        }
        if (member_per == 0) {
            id_per = new ArrayList();
            pw_res = new ArrayList();
        }

        if (member_res != 0 & member_per != 0) {
            id_res = getIntent.getStringArrayListExtra("ID_Res_New");
            pw_res = getIntent.getStringArrayListExtra("PW_Res_New");
            id_per = getIntent.getStringArrayListExtra("ID_Per_New");
            pw_per = getIntent.getStringArrayListExtra("PW_Per_New");

            Log.v(tag, "Login_Page.onCreate() 호출됨");
            Log.v(tag, String.valueOf(member_res));
        }

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

                if (resButton.isChecked()) {
                    putIntent = new Intent(Login_Page.this, Restaurant.class);
                    startActivity(putIntent);
                }
                else if (perButton.isChecked()) {
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
                putIntent.putStringArrayListExtra("ID_Res_List",id_res);
                putIntent.putStringArrayListExtra("PW_Res_List",pw_res);
                putIntent.putStringArrayListExtra("ID_Per_List",id_per);
                putIntent.putStringArrayListExtra("PW_Per_List",pw_per);
                putIntent.putExtra("Member_Res",member_res);
                putIntent.putExtra("Member_Per",member_per);
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