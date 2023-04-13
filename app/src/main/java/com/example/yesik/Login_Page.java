package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity {

    String tag;

    Button joinButton;
    Button loginButton;

    EditText idInput;
    EditText pwInput;

    RadioGroup memberCheck;
    RadioButton restaurantButton;
    RadioButton personButton;

    CheckBox autoLoginCheck;

    Intent putIntent;

    SharedPreferences getUserInfoSplit;

    String[] restaurantUserIDList;
    String[] restaurantUserPWList;
    String[] personalUserIDList;
    String[] personalUserPWList;

    String userID;
    int userIndex;

    int restaurantUserCount;
    int personalUserCount;

    static final String USER_INFO_SPLIT = "UserInfoSplit";
    static final String AUTO_LOGIN_CHECK = "Auto Login Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Log.v(tag, "onCreate() 호출됨");

        initializing();
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
                userLogin();
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

    // Life Cycle Finish

    // Custom Method

    public void initializing() {
        tag = "로그인 페이지";

        joinButton = findViewById(R.id.joinButton);
        loginButton = findViewById(R.id.loginButton);

        idInput = findViewById(R.id.idInput);
        pwInput = findViewById(R.id.passwordInput);

        memberCheck = findViewById(R.id.memberCheck);

        restaurantButton = findViewById(R.id.restaurantMember);
        personButton = findViewById(R.id.personalMember);

        autoLoginCheck = findViewById(R.id.autoLoginCheckBox);

        memberCheck.check(-1);

        getUserInfoSplit = getSharedPreferences(USER_INFO_SPLIT, MODE_PRIVATE);
    }

    public void userLogin() {
        String getID = idInput.getText().toString();
        String getPW = pwInput.getText().toString();

        if (getID.equals("")) {
            Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if (getPW.equals("")) {
            Toast.makeText(getApplicationContext(), "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (restaurantButton.isChecked()) {
                restaurantUserIDList = getUserInfoSplit.getString("Restaurant User ID", "").split("⊙");
                restaurantUserPWList = getUserInfoSplit.getString("Restaurant User Password", "").split("⊙");
                restaurantUserCount = getUserInfoSplit.getInt("Restaurant User Count", 0);

                for (int count = 1; count <= restaurantUserCount; count++) {
                    if (restaurantUserIDList[count].equals(getID)) {
                        userIndex = count;
                        userID = restaurantUserIDList[count];
                        break;
                    }
                    else {
                        userIndex = 0;
                    }
                }

                if (userIndex == 0) {
                    Toast.makeText(getApplicationContext(), "아이디 혹은 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (restaurantUserPWList[userIndex].equals(getPW)) {
                        SharedPreferences.Editor putID = getUserInfoSplit.edit();

                        putID.putString("Login User ID", userID);
                        putID.putInt("Login User Index", userIndex);

                        if (autoLoginCheck.isChecked()) {
                            putID.putString(AUTO_LOGIN_CHECK, "Restaurant");
                        }
                        else {
                            putID.putString(AUTO_LOGIN_CHECK, "x");
                        }

                        putID.commit();

                        putIntent = new Intent(Login_Page.this, Restaurant.class);
                        startActivity(putIntent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "아이디 혹은 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else if (personButton.isChecked()) {
                personalUserIDList = getUserInfoSplit.getString("Personal User ID", "").split("⊙");
                personalUserPWList = getUserInfoSplit.getString("Personal User Password", "").split("⊙");
                personalUserCount = getUserInfoSplit.getInt("Personal User Count", 0);

                for (int count = 1; count <= personalUserCount; count++) {
                    if (personalUserIDList[count].equals(getID)) {
                        userIndex = count;
                        userID = personalUserIDList[count];
                        break;
                    }
                    else {
                        userIndex = 0;
                    }
                }

                if (userIndex == 0) {
                    Toast.makeText(getApplicationContext(), "아이디 혹은 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (personalUserPWList[userIndex].equals(getPW)) {
                        SharedPreferences.Editor putID = getUserInfoSplit.edit();

                        putID.putString("Login User ID", userID);
                        putID.putInt("Login User Index", userIndex);

//                        if (autoLoginCheck.isChecked()) {
//                            putID.putString(AUTO_LOGIN_CHECK, "Person");
//                        }
//                        else {
//                            putID.putString(AUTO_LOGIN_CHECK, "x");
//                        }

                        putID.commit();

                        putIntent = new Intent(Login_Page.this, Personal.class);
                        startActivity(putIntent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "아이디 혹은 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "회원 종류를 선택하세요.", Toast.LENGTH_LONG).show();
            }
        }

    }

}