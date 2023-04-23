package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Manage_Table extends AppCompatActivity {

    String tag;

    ImageButton backButton;

    RecyclerView reservationListView;

    ManageAdapter manageAdapter;

    ArrayList<ManageReservationItem> reservationList;

    SharedPreferences getUserInfo;
    SharedPreferences getReservationInfo;

    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_table);
        Log.v(tag, "onCreate() 호출됨");

        initializing();
        setReservationInfo();
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
                Intent intent = new Intent(Manage_Table.this, Restaurant.class);
                startActivity(intent);
                finish();
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

    public void initializing() {
        tag = "테이블 관리 페이지";

        backButton = findViewById(R.id.backButton);

        reservationListView = (RecyclerView) findViewById(R.id.reservationListView);
        reservationListView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        reservationListView.addItemDecoration(new DividerItemDecoration(this, 1));

        reservationList = new ArrayList<>();
        manageAdapter = new ManageAdapter(reservationList);

        getUserInfo = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);
        getReservationInfo = getSharedPreferences("Reservation", MODE_PRIVATE);
    }

    public void setReservationInfo() {
        int userIndex = getUserInfo.getInt("Login User Index", 0);
        String[] restaurantNameList = getUserInfo.getString("Restaurant Name", "").split("⊙");
        String[] restaurantPlaceList = getUserInfo.getString("Restaurant Place", "").split("⊙");

        String restaurantName = restaurantNameList[userIndex];
        String restaurantPlace = restaurantPlaceList[userIndex];

        String[] reserveRestaurantNameList = getReservationInfo.getString("Reserve Restaurant Name", "").split("⊙");
        String[] reserveRestaurantPlaceList = getReservationInfo.getString("Reserve Restaurant Place", "").split("⊙");
        String[] reserveUserIDList = getReservationInfo.getString("Reserve User", "").split("⊙");
        String[] reserveHourList = getReservationInfo.getString("Reserve Time (hour)", "").split("⊙");
        String[] reserveMinuteList = getReservationInfo.getString("Reserve Time (minute)", "").split("⊙");
        String[] reserveStatusList = getReservationInfo.getString("Reserve Status", "").split("⊙");

        for (int count = 1; count < reserveRestaurantNameList.length; count++) {
            if (reserveRestaurantNameList[count].equals(restaurantName) && reserveRestaurantPlaceList[count].equals(restaurantPlace)) {
                itemCount++;
            }
        }

        for (int count = 1; count < reserveRestaurantNameList.length; count++) {
            if (reserveRestaurantNameList[count].equals(restaurantName) && reserveRestaurantPlaceList[count].equals(restaurantPlace)) {
                String userID = reserveUserIDList[count];
                String reserveHour = reserveHourList[count];
                String reserveMinute = reserveMinuteList[count];
                String status = reserveStatusList[count];

                if (reserveHour.equals("0")) {
                    reserveHour = "00";
                }
                if (reserveMinute.equals("0")) {
                    reserveMinute = "00";
                }

                ManageReservationItem manageReservationItem = new ManageReservationItem (itemCount, userID, reserveHour + " : " + reserveMinute, status);

                manageAdapter.addData(manageReservationItem);

                itemCount--;
            }
        }
        reservationListView.setAdapter(manageAdapter);
        manageAdapter.notifyDataSetChanged();
    }

}