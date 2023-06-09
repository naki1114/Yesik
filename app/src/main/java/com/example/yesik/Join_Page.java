package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.util.Log;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Join_Page extends AppCompatActivity {

    String tag;

    LinearLayout corporationNumberLayout;
    LinearLayout restaurantNameLayout;
    LinearLayout divisionLayout;
    LinearLayout addressLayout;

    EditText idInput;
    EditText pwInput;
    EditText pwReInput;
    EditText userNameInput;
    EditText userBirthInput;
    EditText corporationNumberInput;
    EditText restaurantNameInput;
    EditText restaurantPlaceInput;
    EditText restaurantAddressInput;

    Spinner divisionSpinner;

    RadioGroup memberCheck;
    RadioButton restaurantButton;
    RadioButton personButton;

    Button finishButton;
    Button cancelButton;
    Button duplicateCheckButton;

    int duplicateCheck;
    int restaurantUserCount = 0;
    int personalUserCount = 0;

    int spinnerGroup;

    String getID;
    String getPW;
    String getRePW;
    String getName;
    String getBirth;
    String getCorporationNumber;
    String getRestaurantName;
    String getRestaurantPlace;
    String getRestaurantAddress;
    String getDivision;


    SharedPreferences userInfoSplit;
//    SharedPreferences userInfoJson;

    String[] restaurantUserIDList;
    String[] restaurantUserPWList;
    String[] restaurantUserNameList;
    String[] restaurantUserBirthList;
    String[] restaurantCorporationNumberList;
    String[] restaurantNameList;
    String[] restaurantPlaceList;
    String[] restaurantAddressList;
    String[] restaurantDivisionList;

    String[] personalUserIDList;
    String[] personalUserPWList;
    String[] personalUserNameList;
    String[] personalUserBirthList;

    Intent intent;

    long finishTime = 1000;
    long pressTime = 0;

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

        memberCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.restaurantUser) {
                    corporationNumberLayout.setVisibility(View.VISIBLE);
                    restaurantNameLayout.setVisibility(View.VISIBLE);
                    divisionLayout.setVisibility(View.VISIBLE);
                    addressLayout.setVisibility(View.VISIBLE);
                }
                else {
                    corporationNumberLayout.setVisibility(View.GONE);
                    restaurantNameLayout.setVisibility(View.GONE);
                    divisionLayout.setVisibility(View.GONE);
                    addressLayout.setVisibility(View.GONE);
                }
            }
        });

        idInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                duplicateCheck = 0;
                idInput.setTextColor(Color.parseColor("#000000"));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPW = pwInput.getText().toString();
                getRePW = pwReInput.getText().toString();

                if (getPW.length() >= 8 && getPW.length() <= 20) {
                    pwInput.setTextColor(Color.parseColor("#00FF00"));
                }
                else {
                    pwInput.setTextColor(Color.parseColor("#000000"));
                }

                if (getPW.equals(getRePW)) {
                    pwReInput.setTextColor(Color.parseColor("#00FF00"));
                }
                else {
                    pwReInput.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pwReInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPW = pwInput.getText().toString();
                getRePW = pwReInput.getText().toString();

                if (getRePW.equals(getPW)) {
                    pwReInput.setTextColor(Color.parseColor("#00FF00"));
                }
                else {
                    pwReInput.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        userBirthInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getBirth = userBirthInput.getText().toString();

                if (getBirth.length() == 6) {
                    userBirthInput.setTextColor(Color.parseColor("#00FF00"));
                }
                else {
                    userBirthInput.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        corporationNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getCorporationNumber = corporationNumberInput.getText().toString();

                if (getCorporationNumber.length() == 10) {
                    corporationNumberInput.setTextColor(Color.parseColor("#00FF00"));
                }
                else {
                    corporationNumberInput.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        duplicateCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getID = idInput.getText().toString();

                userDuplicateCheck();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Join_Page.this, Login_Page.class);
                startActivity(intent);
                finish();
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
                getCorporationNumber = corporationNumberInput.getText().toString();
                getRestaurantName = restaurantNameInput.getText().toString();
                getRestaurantPlace = restaurantPlaceInput.getText().toString();
                getRestaurantAddress = restaurantAddressInput.getText().toString();
                getDivision = divisionSpinner.getSelectedItem().toString();

                memberJoin();
            }

        });

        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    spinnerGroup = 1;
                }
                else if (position == 2) {
                    spinnerGroup = 2;
                }
                else if (position == 3) {
                    spinnerGroup = 3;
                }
                else if (position == 4) {
                    spinnerGroup = 4;
                }
                else if (position == 5) {
                    spinnerGroup = 5;
                }
                else if (position == 6) {
                    spinnerGroup = 6;
                }
                else if (position == 7) {
                    spinnerGroup = 7;
                }
                else if (position == 8) {
                    spinnerGroup = 8;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        corporationNumberLayout = findViewById(R.id.corporationNumberLayout);
        restaurantNameLayout = findViewById(R.id.restauranNameLayout);
        divisionLayout = findViewById(R.id.divisionLayout);
        addressLayout = findViewById(R.id.addressLayout);

        memberCheck = findViewById(R.id.memberCheck);
        restaurantButton = findViewById(R.id.restaurantUser);
        personButton = findViewById(R.id.personalUser);

        duplicateCheckButton = findViewById(R.id.duplicateCheck);
        finishButton = findViewById(R.id.finishButton);
        cancelButton = findViewById(R.id.cancelButton);

        idInput = findViewById(R.id.idInput);
        pwInput = findViewById(R.id.passwordInput);
        pwReInput = findViewById(R.id.passwordReInput);
        userNameInput = findViewById(R.id.nameInput);
        userBirthInput = findViewById(R.id.birthInput);
        corporationNumberInput = findViewById(R.id.corporationNumberInput);
        restaurantNameInput = findViewById(R.id.restaurantNameInput);
        restaurantPlaceInput = findViewById(R.id.restaurantPlaceInput);
        restaurantAddressInput = findViewById(R.id.restaurantAddressInput);

        divisionSpinner = findViewById(R.id.divisionSpinner);

        userInfoSplit = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);
//        userInfoJson = getSharedPreferences("UserInfoJson", MODE_PRIVATE);

        getRestaurantUserInfoSplit();
        getPersonalUserInfoSplit();
    }

    public void memberJoin() {
        if (getID.equals("")) {
            Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            idInput.requestFocus();
        }
        else if (getID.length() < 6 || getID.length() > 12) {
            Toast.makeText(getApplicationContext(), "아이디는 6 ~ 12자리 입력해주세요.", Toast.LENGTH_SHORT).show();
            idInput.requestFocus();
            idInput.setSelection(getID.length());
        }
        else if (duplicateCheck == 0) {
            Toast.makeText(getApplicationContext(), "아이디 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if (getPW.equals("")) {
            Toast.makeText(getApplicationContext(), "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show();
            pwInput.requestFocus();
        }
        else if (getPW.length() < 8 || getPW.length() > 20) {
            Toast.makeText(getApplicationContext(), "패스워드는 8 ~ 20자리 입력해주세요.", Toast.LENGTH_SHORT).show();
            pwInput.requestFocus();
            pwInput.setSelection(getPW.length());
        }
        else if (getRePW.equals("")) {
            Toast.makeText(getApplicationContext(), "패스워드를 한번 더 확인해주세요.", Toast.LENGTH_SHORT).show();
            pwReInput.requestFocus();
        }
        else if (!getPW.equals(getRePW)) {
            Toast.makeText(getApplicationContext(), "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            pwReInput.requestFocus();
            pwReInput.setSelection(getRePW.length());
        }
        else if (getName.equals("")) {
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
            userNameInput.requestFocus();
        }
        else if (getBirth.equals("")) {
            Toast.makeText(getApplicationContext(), "생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            userBirthInput.requestFocus();
        }
        else if (getBirth.length() != 6) {
            Toast.makeText(getApplicationContext(), "생년월일 형식은 YYMMDD 입니다.", Toast.LENGTH_SHORT).show();
            userBirthInput.requestFocus();
            userBirthInput.setSelection(getBirth.length());
        }
        else if (restaurantButton.isChecked() && getCorporationNumber.equals("")) {
            Toast.makeText(getApplicationContext(), "사업자 등록번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            corporationNumberInput.requestFocus();
        }
        else if (restaurantButton.isChecked() && getCorporationNumber.length() != 10) {
            Toast.makeText(getApplicationContext(), "사업자 등록번호 형식에 맞게 입력해주세요.", Toast.LENGTH_SHORT).show();
            corporationNumberInput.requestFocus();
            corporationNumberInput.setSelection(getCorporationNumber.length());
        }
        else if (restaurantButton.isChecked() && getRestaurantName.equals("")) {
            Toast.makeText(getApplicationContext(), "상호명을 입력해주세요.", Toast.LENGTH_SHORT).show();
            restaurantNameInput.requestFocus();
        }
        else if (restaurantButton.isChecked() && getRestaurantPlace.equals("")) {
            Toast.makeText(getApplicationContext(), "지점을 입력해주세요.", Toast.LENGTH_SHORT).show();
            restaurantPlaceInput.requestFocus();
        }
        else if (restaurantButton.isChecked() && (spinnerGroup < 1 || spinnerGroup > 8)) {
            Toast.makeText(getApplicationContext(), "식당 분류를 선택해주세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (restaurantButton.isChecked()) {
                restaurantUserCount++;

                saveRestaurantUserInfoSplit();

//            saveUserInfoJson();
//            getUserInfoJson();

                Toast.makeText(getApplicationContext(), "회원 가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                intent = new Intent(Join_Page.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
            else if (personButton.isChecked()) {
                personalUserCount++;

                savePersonalUserInfoSplit();

//            saveUserInfoJson();
//            getUserInfoJson();

                Toast.makeText(getApplicationContext(), "회원 가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                intent = new Intent(Join_Page.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "회원 종류를 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void saveRestaurantUserInfoSplit() {
        SharedPreferences.Editor editorSplit = userInfoSplit.edit();

        editorSplit.putString("Restaurant User ID", userInfoSplit.getString("Restaurant User ID", "") + "⊙" + getID);
        editorSplit.putString("Restaurant User Password", userInfoSplit.getString("Restaurant User Password", "") + "⊙" + getPW);
        editorSplit.putString("Restaurant User Name", userInfoSplit.getString("Restaurant User Name", "") + "⊙" + getName);
        editorSplit.putString("Restaurant User Birth", userInfoSplit.getString("Restaurant User Birth", "") + "⊙" + getBirth);
        editorSplit.putString("Restaurant Corporation Number", userInfoSplit.getString("Restaurant Corporation Number", "") + "⊙" + getCorporationNumber);
        editorSplit.putString("Restaurant Name", userInfoSplit.getString("Restaurant Name", "") + "⊙" + getRestaurantName);
        editorSplit.putString("Restaurant Place", userInfoSplit.getString("Restaurant Place", "") + "⊙" + getRestaurantPlace);
        editorSplit.putString("Restaurant Division", userInfoSplit.getString("Restaurant Division", "") + "⊙" + getDivision);
        editorSplit.putString("Restaurant Address", userInfoSplit.getString("Restaurant Address", "") + "⊙" + getRestaurantAddress);
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
        restaurantCorporationNumberList = userInfoSplit.getString("Restaurant Corporation Number", "").split("⊙");
        restaurantNameList = userInfoSplit.getString("Restaurant Name", "").split("⊙");
        restaurantPlaceList = userInfoSplit.getString("Restaurant Place", "").split("⊙");
        restaurantAddressList = userInfoSplit.getString("Restaurant Address", "").split("⊙");
        restaurantDivisionList = userInfoSplit.getString("Restaurant Division", "").split("⊙");
        restaurantUserCount = userInfoSplit.getInt("Restaurant User Count",0);
    }

    public void getPersonalUserInfoSplit() {
        personalUserIDList = userInfoSplit.getString("Personal User ID", "").split("⊙");
        personalUserPWList = userInfoSplit.getString("Personal User Password", "").split("⊙");
        personalUserNameList = userInfoSplit.getString("Personal User Name", "").split("⊙");
        personalUserBirthList = userInfoSplit.getString("Personal User Birth", "").split("⊙");
        personalUserCount = userInfoSplit.getInt("Personal User Count", 0);
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

    public void userDuplicateCheck() {

        if (getID.equals("")) {
            Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            Log.v("중복확인", "아이디를 입력해주세요.");
        }
        else {
            if (restaurantUserCount == 0) {
                duplicateCheck = 1;
                idInput.setTextColor(Color.parseColor("#00FF00"));
            }
            else {
                for (int count = 1; count <= restaurantUserCount; count++) {
                    if (restaurantUserIDList[count].equals(getID)) {
                        Toast.makeText(getApplicationContext(), "이미 등록된 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        duplicateCheck = 0;
                        idInput.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    }
                    else {
                        duplicateCheck = 1;
                        idInput.setTextColor(Color.parseColor("#00FF00"));
                    }
                }
            }
            if (duplicateCheck == 1 && personalUserCount == 0) {
                duplicateCheck = 1;
                idInput.setTextColor(Color.parseColor("#00FF00"));
            }
            else if (duplicateCheck == 1 & personalUserCount != 0){
                for (int count = 1; count <= personalUserCount; count++) {
                    if (personalUserIDList[count].equals(getID)) {
                        Toast.makeText(getApplicationContext(), "이미 등록된 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        duplicateCheck = 0;
                        idInput.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    }
                    else {
                        duplicateCheck = 1;
                        idInput.setTextColor(Color.parseColor("#00FF00"));
                    }
                }
            }

            if (duplicateCheck == 1) {
                Toast.makeText(getApplicationContext(), "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - pressTime;

        if (0 <= intervalTime && finishTime >= intervalTime)
        {
            finish();
        }
        else
        {
            pressTime = tempTime;
            intent = new Intent(Join_Page.this, Login_Page.class);
            startActivity(intent);
            finish();
        }
    }

}