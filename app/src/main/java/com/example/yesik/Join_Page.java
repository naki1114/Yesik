package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Join_Page extends AppCompatActivity {

    String tag;

    EditText idInput;
    EditText pwInput;
    EditText pwReInput;
    EditText userName;
    EditText birth;

    Button finishButton;
    Button cancelButton;
    Button duplicateCheck;

    ArrayList id_res;
    ArrayList pw_res;
    ArrayList id_per;
    ArrayList pw_per;

    int member_res;
    int member_per;

    int duplicate_check;

    String getID;
    String getPW;
    String getRePW;
    String getName;
    String getBirth;

    SharedPreferences userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page);

        Log.v(tag, "onCreate() 호출됨");

        initializing();
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

        duplicateCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duplicate_check = 1;
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Join_Page.this, Login_Page.class);
                startActivity(intent);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getID = idInput.getText().toString();
                getPW = pwInput.getText().toString();
                getRePW = pwReInput.getText().toString();
                getName = userName.getText().toString();
                getBirth = birth.getText().toString();

//                memberJoin();

                SharedPreferences.Editor editor = userInfo.edit();

                editor.putString("UserId", userInfo.getString("UserId", "") + " " + getID);
                editor.putString("UserPassword", userInfo.getString("UserPassword", "") + " " + getPW);
                editor.putString("UserName", userInfo.getString("UserName", "") + " " + getName);
                editor.putString("UserBirth", userInfo.getString("UserBirth", "") + " " + getBirth);

                editor.commit();

                getUserInfo();

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
        Log.v(tag, "onStop() 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(tag, "onDestroy() 호출됨");
    }

    // Life Cycle Finish

    // Custom Method

    public void initializing() {
        tag = "회원가입 페이지";

        duplicateCheck = findViewById(R.id.duplicateCheck);
        finishButton = findViewById(R.id.finishButton);
        cancelButton = findViewById(R.id.cancelButton);

        idInput = findViewById(R.id.idInput);
        pwInput = findViewById(R.id.passwordInput);
        pwReInput = findViewById(R.id.passwordReInput);
        userName = findViewById(R.id.nameInput);
        birth = findViewById(R.id.birthInput);

        userInfo = getSharedPreferences("UserInformation", MODE_PRIVATE);
    }

    public void memberJoin() {
//        회원가입 기능
//        if (getID.equals(null) || getPW.equals(null) || getRePW.equals(null) || getName.equals(null) || getBirth.equals(null) || duplicate_check == 0 || !getPW.equals(getRePW)) {
//            if (duplicate_check == 0) {
//                Toast.makeText(getApplicationContext(), "ID 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
//            }
//            if (!getPW.equals(getRePW)) {
//                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
//            }
//            if (getID.equals(null) || getPW.equals(null) || getRePW.equals(null) || getName.equals(null) || getBirth.equals(null)) {
//                Toast.makeText(getApplicationContext(), "모든 항목을 입력하세요.", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "회원 가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(Join_Page.this, Login_Page.class);
//            startActivity(intent);
//        }
    }

    public void getUserInfo() {
        String[] userID = userInfo.getString("UserId", "").split(" ");
        String[] userPW = userInfo.getString("UserPassword", "").split(" ");
        String[] userName = userInfo.getString("UserName", "").split(" ");
        String[] userBirth = userInfo.getString("UserBirth", "").split(" ");

        for (int userCount = 1; userCount < userID.length; userCount++) {
            Log.v(userCount + "번째 User ID", userID[userCount]);
            Log.v(userCount + "번째 User PW", userPW[userCount]);
            Log.v(userCount + "번째 User Name", userName[userCount]);
            Log.v(userCount + "번째 User Birth", userBirth[userCount]);
        }
    }

}