package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonalUserInfo extends AppCompatActivity {

    String tag;

    SharedPreferences getUserInfo;

    Button logoutButton;

    ImageButton backButton;
    ImageButton profileButton;

    TextView userID;
    TextView userName;
    TextView userBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_user_info);
        Log.v(tag, "onCreate() 호출됨");

        initializing();
        setUserInfo();
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeScreen = new Intent(PersonalUserInfo.this, Personal.class);
                startActivity(changeScreen);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getUserInfo.edit();
                editor.putString("Auto Login Check", "x");
                editor.commit();

                Intent changeScreen = new Intent(PersonalUserInfo.this, Login_Page.class);
                startActivity(changeScreen);
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

    // Life Cycle Finish

    // Custom Method

    public void initializing() {
        tag = "사용자 정보 페이지";

        getUserInfo = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);

        backButton = findViewById(R.id.backButton);
        logoutButton = findViewById(R.id.logoutButton);

        profileButton = findViewById(R.id.profileButton);

        userID = findViewById(R.id.userID);
        userName = findViewById(R.id.userName);
        userBirth = findViewById(R.id.userBirth);
    }

    public void setUserInfo() {
        String[] userIDList = getUserInfo.getString("Personal User ID","").split("⊙");
        String[] userNameList = getUserInfo.getString("Personal User Name", "").split("⊙");
        String[] userBirthList = getUserInfo.getString("Personal User Birth","").split("⊙");
        int userIndex = getUserInfo.getInt("Login User Index", 0);

        userID.setText(userIDList[userIndex]);
        userName.setText(userNameList[userIndex]);

        if (Integer.valueOf(userBirthList[userIndex].substring(0,2)) <= 23) {
            userBirth.setText("20" + userBirthList[userIndex].substring(0,2) + "년 " + userBirthList[userIndex].substring(2,4) + "월 " + userBirthList[userIndex].substring(4,6) + "일");
        }
        else {
            userBirth.setText("19" + userBirthList[userIndex].substring(0,2) + "년 " + userBirthList[userIndex].substring(2,4) + "월 " + userBirthList[userIndex].substring(4,6) + "일");
        }
    }

}