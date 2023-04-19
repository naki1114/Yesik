package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ReserveCheck extends AppCompatActivity {

    String tag;

    RecyclerView reservationListView;
    ReserveAdapter reserveAdapter;

    SharedPreferences getReservationHistory;
    SharedPreferences getUserInfo;

    ArrayList<ReservationItem> reservationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_check);
        Log.v(tag, "onCreate() 호출됨");

        initializing();
        setReservationHistory();
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
        tag = "예약 내역 확인 페이지";

        reservationListView = findViewById(R.id.reservationList);

        reservationListView = (RecyclerView) findViewById(R.id.reservationList);
        reservationListView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        reservationListView.addItemDecoration(new DividerItemDecoration(this, 1));

        reservationList = new ArrayList<>();
        reserveAdapter = new ReserveAdapter(reservationList);

        getReservationHistory = getSharedPreferences("Reservation", MODE_PRIVATE);
        getUserInfo = getSharedPreferences("UserInfoSplit",MODE_PRIVATE);
    }

    public void setReservationHistory() {
        String[] reservationUserList = getReservationHistory.getString("Reserve User", "").split("⊙");
        String[] reserveRestaurantNameList = getReservationHistory.getString("Reserve Restaurant Name", "").split("⊙");
        String[] reserveRestaurantPlaceList = getReservationHistory.getString("Reserve Restaurant Place", "").split("⊙");
        String[] reserveTimeHourList = getReservationHistory.getString("Reserve Time (hour)", "").split("⊙");
        String[] reserveTimeMinuteList = getReservationHistory.getString("Reserve Time (minute)", "").split("⊙");
        String[] reserveStatusList = getReservationHistory.getString("Reserve Status", "").split("⊙");
        String loginUser = getUserInfo.getString("Login User ID", "");

        for (int count = 1; count < reservationUserList.length; count++) {
            if (loginUser.equals(reservationUserList[count])) {
                String name = reserveRestaurantNameList[count];
                String place = reserveRestaurantPlaceList[count];

                if (reserveTimeHourList[count].equals("0")) {
                    reserveTimeHourList[count] = "00";
                }
                if (reserveTimeMinuteList[count].equals("0")) {
                    reserveTimeMinuteList[count] = "00";
                }
                String time = reserveTimeHourList[count] + " : " + reserveTimeMinuteList[count];
                String status = reserveStatusList[count];

                ReservationItem reservationItem = new ReservationItem(name, place, time, status);

                reserveAdapter.addData(reservationItem);
            }
        }
        reservationListView.setAdapter(reserveAdapter);
        reserveAdapter.notifyDataSetChanged();
    }

}