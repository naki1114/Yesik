package com.example.yesik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // SDK_INT = 33 / Build.VERSION_CODES.M = 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermissions(PERMISSIONS)) {
                requestPermissions(PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
            else {
                Intent toMain = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(toMain);

                finish();
            }
        }
    }

    static final int PERMISSIONS_REQUEST_CODE = 1000;
    String[] PERMISSIONS = {android.Manifest.permission.CALL_PHONE, android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private boolean hasPermissions(String[] permissions) {
        int result;

        for (String permission : permissions) {

            result = ContextCompat.checkSelfPermission(this, permission);

            if (result == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraPermissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean diskPermissionAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean callPermissionAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (!cameraPermissionAccepted || !diskPermissionAccepted || !callPermissionAccepted) {
                        showDialogForPermission("앱을 실행하려면 권한을 허가해야합니다.");
                    }
                    else {
                        Intent toMain = new Intent(LaunchActivity.this, MainActivity.class);
                        startActivity(toMain);

                        finish();
                    }
                }
                break;
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LaunchActivity.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                requestPermissions(PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        builder.create().show();
    }

}