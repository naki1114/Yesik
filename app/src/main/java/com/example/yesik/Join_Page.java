package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    int count;

    int duplicate_check;
    int member_check = 0;

    String getID;
    String getPW;
    String getRePW;
    String getName;
    String getBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page);

        tag = "회원가입 페이지";
        Log.v(tag, "onCreate() 호출됨");

        duplicateCheck = findViewById(R.id.duplicateCheck);
        finishButton = findViewById(R.id.finishButton);
        cancelButton = findViewById(R.id.cancelButton);

        idInput = findViewById(R.id.idInput);
        pwInput = findViewById(R.id.passwordInput);
        pwReInput = findViewById(R.id.passwordReInput);
        userName = findViewById(R.id.nameInput);
        birth = findViewById(R.id.birthInput);

        Intent intent = getIntent();

        id_res = intent.getStringArrayListExtra("ID_Res_List");
        pw_res = intent.getStringArrayListExtra("PW_Res_List");
        id_per = intent.getStringArrayListExtra("ID_Per_List");
        pw_per = intent.getStringArrayListExtra("PW_Per_List");
        member_res = intent.getIntExtra("Member_Res",0);
        member_per = intent.getIntExtra("Member_Per",0);

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
                getID = idInput.getText().toString();
                for (count = 0; count < (member_res + 1); count++) {
                    if (id_res.size() == 0) {
                        duplicate_check = 0;
                    }
                    else if (id_res.get(count).equals(getID)) {
                        duplicate_check = count;
                        break;
                    }
                    else {
                        duplicate_check = 0;
                    }
                }
                for (count = 0; count < (member_per + 1); count++) {
                    if (id_per.size() == 0) {
                        duplicate_check = 0;
                    }
                    else if (id_per.get(count).equals(getID)) {
                        duplicate_check = count;
                        break;
                    }
                    else {
                        duplicate_check = 0;
                    }
                }
                if (duplicate_check == 0) {
                    Toast.makeText(getApplicationContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "이미 사용 중인 아이디입니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Join_Page.this, Login_Page.class);
                intent.putStringArrayListExtra("ID_Res_New",id_res);
                intent.putStringArrayListExtra("PW_Res_New",pw_res);
                intent.putStringArrayListExtra("ID_Per_New",id_per);
                intent.putStringArrayListExtra("PW_Per_New",pw_per);
                intent.putExtra("Member_Res",member_res);
                intent.putExtra("Member_Per",member_per);
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

                if (getID == null | getPW == null | getRePW == null | getName == null | getBirth == null) {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력하세요.", Toast.LENGTH_LONG).show();
                }
                else if (getPW != getRePW) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
                else if (duplicate_check == 0) {
                    Toast.makeText(getApplicationContext(), "ID 중복 확인을 해주세요.", Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(getApplicationContext(), "회원 가입이 완료되었습니다.", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Join_Page.this, Login_Page.class);
                    intent.putStringArrayListExtra("ID_Res_New",id_res);
                    intent.putStringArrayListExtra("PW_Res_New",pw_res);
                    intent.putStringArrayListExtra("ID_Per_New",id_per);
                    intent.putStringArrayListExtra("PW_Per_New",pw_per);
                    intent.putExtra("Member_Res",member_res);
                    intent.putExtra("Member_Per",member_per);
                    startActivity(intent);

                }

//                for (count = 0; count < member_res + 1; count++) {
//
//                }
//                for (count = 0; count < member_per + 1; count++) {
//
//                }
//                id.add(getID);
//                pw.add(getPW);
//
//                member++;
                // ArrayList를 클래스 변수로 선언해서 초기화했는데 .add 메서드를 사용할 때 nullPointException 에러가 발생
                // (.add 위치는 onCreate인지 onResume인지 기억안남)
                // add는 get intent로 받아온 ArrayList

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

}