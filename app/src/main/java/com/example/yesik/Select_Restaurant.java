package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Select_Restaurant extends AppCompatActivity {

    String tag;

    BitmapConverter converter;

    SharedPreferences getRestaurantInfo;
    SharedPreferences getLogoImage;

    RecyclerView restaurantListRecyclerView;
    RestaurantAdapter restaurantAdapter;

    String[] allRestaurantUserIDList;
    String[] allRestaurantNameList;
    String[] allRestaurantPlaceList;
    String[] allRestaurantDivisionList;

    ArrayList<RestaurantItem> restaurantList;

    ArrayList<String> restaurantLogoList;
    ArrayList<String> restaurantNameList;
    ArrayList<String> restaurantPlaceList;
    ArrayList<String> restaurantDivisionList;

    String division;

    int restaurantUserCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_restaurant);
        Log.v(tag, "onCreate() 호출됨");

        initializing();
        divideRestaurant();
        restaurantListView();
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
        tag = "식당 선택 페이지";

        restaurantListRecyclerView = (RecyclerView) findViewById(R.id.restaurantRecyclerView);
        restaurantListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        restaurantListRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        Intent getDivision = getIntent();

        division = getDivision.getStringExtra("division");

        converter = new BitmapConverter();

        restaurantList = new ArrayList<>();

        restaurantLogoList = new ArrayList<>();
        restaurantNameList = new ArrayList<>();
        restaurantPlaceList = new ArrayList<>();
        restaurantDivisionList = new ArrayList<>();

        restaurantAdapter = new RestaurantAdapter(restaurantList);

        getRestaurantInfo = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);
        getLogoImage = getSharedPreferences("RestaurantImage", MODE_PRIVATE);
    }

    public void divideRestaurant() {
        allRestaurantUserIDList = getRestaurantInfo.getString("Restaurant User ID", "").split("⊙");
        allRestaurantNameList = getRestaurantInfo.getString("Restaurant Name", "").split("⊙");
        allRestaurantPlaceList = getRestaurantInfo.getString("Restaurant Place", "").split("⊙");
        allRestaurantDivisionList = getRestaurantInfo.getString("Restaurant Division", "").split("⊙");
        restaurantUserCount = getRestaurantInfo.getInt("Restaurant User Count", 0);

        if (division.equals("ALL")) {
            for (int count = 1; count <= restaurantUserCount; count++) {
                restaurantNameList.add(allRestaurantNameList[count]);
                restaurantPlaceList.add(allRestaurantPlaceList[count]);
                restaurantDivisionList.add(allRestaurantDivisionList[count]);
                restaurantLogoList.add(getLogoImage.getString(allRestaurantUserIDList[count] + " Logo", ""));
            }
        }
        else {
            for (int count = 0; count <= restaurantUserCount; count++) {
                if (allRestaurantDivisionList[count].equals(division)) {
                    restaurantNameList.add(allRestaurantNameList[count]);
                    restaurantPlaceList.add(allRestaurantPlaceList[count]);
                    restaurantDivisionList.add(allRestaurantDivisionList[count]);
                    restaurantLogoList.add(getLogoImage.getString(allRestaurantUserIDList[count] + " Logo", ""));
                }
            }
        }
    }

    public void restaurantListView() {
        for (int count = 0; count < restaurantNameList.size(); count++) {
            RestaurantItem restaurantListItem = new RestaurantItem(converter.StringToBitmap(restaurantLogoList.get(count)), restaurantNameList.get(count), restaurantPlaceList.get(count));

            restaurantAdapter.addData(restaurantListItem);
        }
        restaurantListRecyclerView.setAdapter(restaurantAdapter);
        restaurantAdapter.notifyDataSetChanged();
    }

}