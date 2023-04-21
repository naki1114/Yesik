package com.example.yesik;

import static net.daum.mf.map.api.MapPOIItem.MarkerType.RedPin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Reserve_Time extends AppCompatActivity {

//    private static final MapPoint MARKER_POINT = ;
    String tag;

    LinearLayout restaurantInfoLayout;
    LinearLayout mapViewLayout;
    LinearLayout mapViewFrame;

    Button reserveButton;
    Button backButton;
    Button mapViewButton;

    ImageButton mapViewBackButton;

    TextView restaurantName;

    MapView mapView;

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
        setMapView();
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

        mapViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantInfoLayout.setVisibility(view.GONE);
                mapViewFrame.setVisibility(view.VISIBLE);
            }
        });

        mapViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantInfoLayout.setVisibility(view.VISIBLE);
                mapViewFrame.setVisibility(view.GONE);
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

        restaurantInfoLayout = findViewById(R.id.restaurantInfoLayout);
        mapViewLayout = findViewById(R.id.mapViewLayout);
        mapViewFrame = findViewById(R.id.mapViewFrame);

        reserveButton = findViewById(R.id.reserveButton);
        backButton = findViewById(R.id.backButton);
        mapViewButton = findViewById(R.id.mapViewButton);
        mapViewBackButton = findViewById(R.id.mapViewBackButton);

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
                    editor.putString("Reserve Status", reserveTime.getString("Reserve Status", "") + "⊙" + "대기");
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

    public void setMapView() {
        mapView = new MapView(this);

        mapViewLayout = findViewById(R.id.mapViewLayout);
        mapViewLayout.addView(mapView);

        double restaurantLatitude = findGeoPoint(getApplicationContext(), "경기도 수원시 영통구 권광로 260번길 36").getLatitude();
        double restaurantLongitude = findGeoPoint(getApplicationContext(), "경기도 수원시 영통구 권광로 260번길 36").getLongitude();

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(restaurantLatitude, restaurantLongitude), true);
        // 줌 레벨 변경
        mapView.setZoomLevel(1,true);
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(restaurantLatitude, restaurantLongitude);
        MapPOIItem marker = new MapPOIItem();

        // 클릭 시 나오는 값
        marker.setItemName("응급실 떡볶이");
        // 왜 있는지 모르겠음
        marker.setTag(0);
        // 좌표를 입력받아 현 위치로 출력
        marker.setMapPoint(MARKER_POINT);
        // (클릭 전) 기본으로 제공하는 BluPin 마커 모양의 색
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        // (클릭 후) 마커를 클릭했을 때, 기본으로 제공하는 RedPin 마커 모양의 색
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        // 지도화면 위에 추가되는 아이콘을 추가하기 위한 호출 (말풍선 모양)
        mapView.addPOIItem(marker);
    }

    public static Location findGeoPoint(Context mcontext, String address) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정

        try {
            addr = coder.getFromLocationName(address, 5);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
            }
        }
        return loc;
    }

}