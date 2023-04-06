package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Join_Page extends AppCompatActivity {

    String tag;

    EditText idInput;
    EditText pwInput;
    EditText pwReInput;
    EditText userNameInput;
    EditText userBirthInput;

    RadioGroup memberCheck;
    RadioButton restaurantButton;
    RadioButton personButton;

    Button finishButton;
    Button cancelButton;
    Button duplicateCheck;

    int duplicate_check;
    int restaurantUserCount = 0;
    int personalUserCount = 0;

    String getID;
    String getPW;
    String getRePW;
    String getName;
    String getBirth;

    SharedPreferences userInfoSplit;
    SharedPreferences userInfoJson;

    ArrayList<String> userIDList;
    ArrayList<String> userPWList;
    ArrayList<String> userNameList;
    ArrayList<String> userBirthList;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page);

        initializing();
        Log.v(tag, "onCreate() 호출됨");

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
                getName = userNameInput.getText().toString();
                getBirth = userBirthInput.getText().toString();

                memberJoin();

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

        memberCheck = findViewById(R.id.memberCheck);
        restaurantButton = findViewById(R.id.restaurantUser);
        personButton = findViewById(R.id.personalUser);

        duplicateCheck = findViewById(R.id.duplicateCheck);
        finishButton = findViewById(R.id.finishButton);
        cancelButton = findViewById(R.id.cancelButton);

        idInput = findViewById(R.id.idInput);
        pwInput = findViewById(R.id.passwordInput);
        pwReInput = findViewById(R.id.passwordReInput);
        userNameInput = findViewById(R.id.nameInput);
        userBirthInput = findViewById(R.id.birthInput);

        userIDList = new ArrayList<>();
        userPWList = new ArrayList<>();
        userNameList = new ArrayList<>();
        userBirthList = new ArrayList<>();

        userInfoSplit = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);
        userInfoJson = getSharedPreferences("UserInfoJson", MODE_PRIVATE);
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

        if (restaurantButton.isChecked()) {
            restaurantUserCount++;

            saveUserInfoSplit();
            getUserInfoSplit();

            saveUserInfoJson();
            getUserInfoJson();

            intent = new Intent(Join_Page.this, Login_Page.class);
            startActivity(intent);
        }
        else if (personButton.isChecked()) {
            personalUserCount++;

            saveUserInfoSplit();
            getUserInfoSplit();

            saveUserInfoJson();
            getUserInfoJson();

            intent = new Intent(Join_Page.this, Login_Page.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "회원 종류를 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveUserInfoSplit() {
        SharedPreferences.Editor editorSplit = userInfoSplit.edit();

        editorSplit.putString("UserId", userInfoSplit.getString("UserId", "") + "⊙" + getID);
        editorSplit.putString("UserPassword", userInfoSplit.getString("UserPassword", "") + "⊙" + getPW);
        editorSplit.putString("UserName", userInfoSplit.getString("UserName", "") + "⊙" + getName);
        editorSplit.putString("UserBirth", userInfoSplit.getString("UserBirth", "") + "⊙" + getBirth);

        editorSplit.commit();
    }

    public void getUserInfoSplit() {
        String[] userID = userInfoSplit.getString("UserId", "").split("⊙");
        String[] userPW = userInfoSplit.getString("UserPassword", "").split("⊙");
        String[] userName = userInfoSplit.getString("UserName", "").split("⊙");
        String[] userBirth = userInfoSplit.getString("UserBirth", "").split("⊙");

        for (int userCount = 1; userCount < userID.length; userCount++) {
            Log.v(userCount + "번째 User ID", userID[userCount]);
            Log.v(userCount + "번째 User PW", userPW[userCount]);
            Log.v(userCount + "번째 User Name", userName[userCount]);
            Log.v(userCount + "번째 User Birth", userBirth[userCount]);
        }
    }

    public void saveUserInfoJson() {
        SharedPreferences.Editor editorJson = userInfoJson.edit();

        JSONObject saveInfoJson = new JSONObject();

        try {
            saveInfoJson.put("UserId", getID);
            saveInfoJson.put("UserPassword", getPW);
            saveInfoJson.put("UserName", getName);
            saveInfoJson.put("UserBirth", getBirth);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        editorJson.putString("UserInformation", saveInfoJson.toString());
        editorJson.commit();
    }

    public void getUserInfoJson() {
        String inform = userInfoJson.getString("UserInformation", "");

        try {
            JSONObject getInfoJson = new JSONObject(inform);
            Log.v("ID", getInfoJson.get("UserId").toString());
            Log.v("PW", getInfoJson.get("UserPassword").toString());
            Log.v("Name", getInfoJson.get("UserName").toString());
            Log.v("Birth", getInfoJson.get("UserBirth").toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

}