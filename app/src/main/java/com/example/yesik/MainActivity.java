package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    SharedPreferences autoLogin;

    static final String USER_INFO_SPLIT = "UserInfoSplit";
    static final String AUTO_LOGIN_CHECK = "Auto Login Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoLogin = getSharedPreferences(USER_INFO_SPLIT, MODE_PRIVATE);
        getHashKey();

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

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}