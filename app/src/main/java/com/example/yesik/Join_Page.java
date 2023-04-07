package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import org.json.JSONArray;
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
    int join_check = 0;
    int restaurantUserCount = 0;
    int personalUserCount = 0;

    String getID;
    String getPW;
    String getRePW;
    String getName;
    String getBirth;

    SharedPreferences userInfoSplit;
    SharedPreferences userInfoJson;

    String[] restaurantUserIDList;
    String[] restaurantUserPWList;
    String[] restaurantUserNameList;
    String[] restaurantUserBirthList;

    String[] personalUserIDList;
    String[] personalUserPWList;
    String[] personalUserNameList;
    String[] personalUserBirthList;

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

        userInfoSplit = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);
//        userInfoJson = getSharedPreferences("UserInfoJson", MODE_PRIVATE);

        getRestaurantUserInfoSplit();
        getPersonalUserInfoSplit();
    }

    public void memberJoin() {


        if (restaurantButton.isChecked()) {
            restaurantUserCount++;

            saveRestaurantUserInfoSplit();

//            saveUserInfoJson();
//            getUserInfoJson();

            intent = new Intent(Join_Page.this, Login_Page.class);
            startActivity(intent);
        }
        else if (personButton.isChecked()) {
            personalUserCount++;

            savePersonalUserInfoSplit();

//            saveUserInfoJson();
//            getUserInfoJson();

            intent = new Intent(Join_Page.this, Login_Page.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "회원 종류를 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveRestaurantUserInfoSplit() {
        SharedPreferences.Editor editorSplit = userInfoSplit.edit();

        editorSplit.putString("Restaurant User ID", userInfoSplit.getString("Restaurant User ID", "") + "⊙" + getID);
        editorSplit.putString("Restaurant User Password", userInfoSplit.getString("Restaurant User Password", "") + "⊙" + getPW);
        editorSplit.putString("Restaurant User Name", userInfoSplit.getString("Restaurant User Name", "") + "⊙" + getName);
        editorSplit.putString("Restaurant User Birth", userInfoSplit.getString("Restaurant User Birth", "") + "⊙" + getBirth);
        editorSplit.putInt("Restaurant User Count", restaurantUserCount);

        editorSplit.commit();
    }

    public void savePersonalUserInfoSplit() {
        SharedPreferences.Editor editorSplit = userInfoSplit.edit();

        editorSplit.putString("Personal User ID", userInfoSplit.getString("Personal User ID", "") + "⊙" + getID);
        editorSplit.putString("Personal User Password", userInfoSplit.getString("Personal User Password", "") + "⊙" + getPW);
        editorSplit.putString("Personal User Name", userInfoSplit.getString("Personal User Name", "") + "⊙" + getName);
        editorSplit.putString("Personal User Birth", userInfoSplit.getString("Personal User Birth", "") + "⊙" + getBirth);
        editorSplit.putInt("Personal User Count", personalUserCount);

        editorSplit.commit();
    }

    public void getRestaurantUserInfoSplit() {
        restaurantUserIDList = userInfoSplit.getString("Restaurant User ID", "").split("⊙");
        restaurantUserPWList = userInfoSplit.getString("Restaurant User Password", "").split("⊙");
        restaurantUserNameList = userInfoSplit.getString("Restaurant User Name", "").split("⊙");
        restaurantUserBirthList = userInfoSplit.getString("Restaurant User Birth", "").split("⊙");
        restaurantUserCount = userInfoSplit.getInt("Restaurant User Count",0);

        for (int count = 1; count <= restaurantUserCount; count++) {
            Log.v(count + "번째 Restaurant User ID", restaurantUserIDList[count]);
            Log.v(count + "번째 Restaurant User PW", restaurantUserPWList[count]);
            Log.v(count + "번째 Restaurant User Name", restaurantUserNameList[count]);
            Log.v(count + "번째 Restaurant User Birth", restaurantUserBirthList[count]);
        }
    }

    public void getPersonalUserInfoSplit() {
        personalUserIDList = userInfoSplit.getString("Personal User ID", "").split("⊙");
        personalUserPWList = userInfoSplit.getString("Personal User Password", "").split("⊙");
        personalUserNameList = userInfoSplit.getString("Personal User Name", "").split("⊙");
        personalUserBirthList = userInfoSplit.getString("Personal User Birth", "").split("⊙");
        personalUserCount = userInfoSplit.getInt("Personal User Count", 0);

        for (int count = 1; count <= personalUserCount; count++) {
            Log.v(count + "번째 Personal User ID", personalUserIDList[count]);
            Log.v(count + "번째 Personal User PW", personalUserPWList[count]);
            Log.v(count + "번째 Personal User Name", personalUserNameList[count]);
            Log.v(count + "번째 Personal User Birth", personalUserBirthList[count]);
        }
    }

    /**
     * Json Object
     */
//    public void saveUserInfoJson() {
//        SharedPreferences.Editor editorJson = userInfoJson.edit();
//
//        JSONObject saveInfoJson = new JSONObject();
//
//        try {
//            saveInfoJson.put("UserId", getID);
//            saveInfoJson.put("UserPassword", getPW);
//            saveInfoJson.put("UserName", getName);
//            saveInfoJson.put("UserBirth", getBirth);
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        editorJson.putString("UserInformation", saveInfoJson.toString());
//        editorJson.commit();
//    }
//
//    public void getUserInfoJson() {
//        String inform = userInfoJson.getString("UserInformation", "");
//
//        try {
//            JSONObject getInfoJson = new JSONObject(inform);
//            Log.v("ID", getInfoJson.get("UserId").toString());
//            Log.v("PW", getInfoJson.get("UserPassword").toString());
//            Log.v("Name", getInfoJson.get("UserName").toString());
//            Log.v("Birth", getInfoJson.get("UserBirth").toString());
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Json Array
     */
//    public void saveUserInfoJson() {
//        userIDList.add(getID);
//        userPWList.add(getPW);
//        userNameList.add(getName);
//        userBirthList.add(getBirth);
//
//        SharedPreferences.Editor editorJson = userInfoJson.edit();
//
//        if (!userIDList.isEmpty()) {
//            editorJson.putString("UserId", userIDList.toString());
//            editorJson.putString("UserPassword", userPWList.toString());
//            editorJson.putString("UserName", userNameList.toString());
//            editorJson.putString("UserBirth", userBirthList.toString());
//            editorJson.
//        }
//        else {
//            editorJson.putString("UserId", null);
//            editorJson.putString("UserPassword", null);
//            editorJson.putString("UserName", null);
//            editorJson.putString("UserBirth", null);
//        }
//        editorJson.commit();
//    }
//
//    public void getUserInfoJson() {
//
//    }

}