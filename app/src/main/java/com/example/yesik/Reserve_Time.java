package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Reserve_Time extends AppCompatActivity {

    String tag;

    Button reserveButton;
    Button backButton;

    TextView restaurantName;

    Dialog timeDialog;

    SharedPreferences reserveTime;
    SharedPreferences getUserInfo;

    int hour;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_time);
        Log.v(tag, "onCreate() 호출됨");

        initializing();
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
                changeScreen();
            }
        });

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSelectDialog();
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
        tag = "시간 예약 페이지";

        reserveButton = findViewById(R.id.reserveButton);
        backButton = findViewById(R.id.backButton);

        restaurantName = findViewById(R.id.restaurantName);

        timeDialog = new Dialog(Reserve_Time.this);
        timeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timeDialog.setContentView(R.layout.time_dialog);

        reserveTime = getSharedPreferences("Reservation", MODE_PRIVATE);
        getUserInfo = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);

        restaurantName.setText(reserveTime.getString("Selected Restaurant Name", "") + " " + reserveTime.getString("Selected Restaurant Place", ""));
    }

    public void changeScreen() {
        Intent change = new Intent(Reserve_Time.this, Select_Restaurant.class);
        startActivity(change);
    }

    public void timeSelectDialog() {
        timeDialog.show();

        Button dialogReserveButton = timeDialog.findViewById(R.id.dialogReserveButton);
        Button dialogCancelButton = timeDialog.findViewById(R.id.dialogCancelButton);
        Spinner hourSpinner = timeDialog.findViewById(R.id.hourSelectSpinner);
        Spinner minuteSpinner = timeDialog.findViewById(R.id.minuteSeletcSpinner);

        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    hour = position - 1;
                }
                else {
                    hour = 100;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    minute = (position - 1) * 10;
                }
                else {
                    minute = 100;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dialogReserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hour == 100 || minute == 100) {
                    Toast.makeText(Reserve_Time.this, "시간을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = reserveTime.edit();
                    editor.putString("Reserve Time (hour)", reserveTime.getString("Reserve Time (hour)", "") + "⊙" + hour);
                    editor.putString("Reserve Time (minute)", reserveTime.getString("Reserve Time (minute)", "") + "⊙" + minute);
                    editor.putString("Reserve User", reserveTime.getString("Reserve User", "") + "⊙" + getUserInfo.getString("Login User ID", ""));
                    editor.putString("Reserve Restaurant Name", reserveTime.getString("Reserve Restaurant Name", "") + "⊙" + reserveTime.getString("Selected Restaurant Name", ""));
                    editor.putString("Reserve Restaurant Place", reserveTime.getString("Reserve Restaurant Place", "") + "⊙" + reserveTime.getString("Selected Restaurant Place", ""));
                    editor.commit();

                    Toast.makeText(Reserve_Time.this, hour + "시 " + minute + "분으로 예약 요청을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    timeDialog.dismiss();

                    Intent intent = new Intent(Reserve_Time.this, Personal.class);
                    startActivity(intent);
                }
            }
        });

        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeDialog.dismiss();
            }
        });
    }

}