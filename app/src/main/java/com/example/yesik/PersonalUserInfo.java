package com.example.yesik;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PersonalUserInfo extends AppCompatActivity {

    String tag;

    SharedPreferences getUserInfo;
    SharedPreferences.Editor editor;

    Button logoutButton;

    ImageButton backButton;
    ImageButton profileButton;

    TextView userID;
    TextView userName;
    TextView userBirth;

    int selectImage;

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

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registProfileImage();
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
        editor = getUserInfo.edit();

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

        String userProfile = getUserInfo.getString(userID.getText().toString() + " Profile", "");

        if (!userProfile.equals(null)) {
            Glide.with(PersonalUserInfo.this).load(Uri.parse(userProfile)).into(profileButton);
        }

        if (Integer.valueOf(userBirthList[userIndex].substring(0,2)) <= 23) {
            userBirth.setText("20" + userBirthList[userIndex].substring(0,2) + "년 " + userBirthList[userIndex].substring(2,4) + "월 " + userBirthList[userIndex].substring(4,6) + "일");
        }
        else {
            userBirth.setText("19" + userBirthList[userIndex].substring(0,2) + "년 " + userBirthList[userIndex].substring(2,4) + "월 " + userBirthList[userIndex].substring(4,6) + "일");
        }
    }

    public void registProfileImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        toGallery.launch(intent);
    }

    ActivityResultLauncher<Intent> toGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Uri uri = result.getData().getData();
                Glide.with(PersonalUserInfo.this).load(uri).into(profileButton);

                editor.putString(userID.getText().toString() + " Profile", uri.toString());
                editor.commit();
            }
            else if (result.getResultCode() == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show();
            }
        }
    });

}