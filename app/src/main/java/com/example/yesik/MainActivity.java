package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    SharedPreferences autoLogin;

    static final String USER_INFO_SPLIT = "UserInfoSplit";
    static final String AUTO_LOGIN_CHECK = "Auto Login Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoLogin = getSharedPreferences(USER_INFO_SPLIT, MODE_PRIVATE);

        moveLogin(1);
    }

    private void moveLogin(int sec) {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                String autoLoginCheck = autoLogin.getString(AUTO_LOGIN_CHECK, "");

                if (autoLoginCheck.equals("Restaurant")) {
                    Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                    startActivity(intent);
                }
                else if (autoLoginCheck.equals("Person")) {
                    Intent intent = new Intent(getApplicationContext(), Personal.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), Login_Page.class);
                    startActivity(intent);
                }

                finish();
            }
        }, 1000 * sec);
    }

}