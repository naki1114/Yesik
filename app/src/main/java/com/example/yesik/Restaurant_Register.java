package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Restaurant_Register extends AppCompatActivity {

    String tag;

    EditText nameInput;
    EditText placeInput;

    Button restaurantRegistButton;
    Button imageRegistButton;

    ImageButton getImageButton;

    Spinner imageSelectSpinner;

    RecyclerView restaurantInnerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_register);
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

        imageSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Toast.makeText(getApplicationContext(), "로고", Toast.LENGTH_SHORT).show();
                }
                else if (position == 2) {
                    Toast.makeText(getApplicationContext(), "내부 전경", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "사진 분류를 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        tag = "식당 등록 페이지";

        nameInput = findViewById(R.id.nameInput);
        placeInput = findViewById(R.id.placeInput);

        restaurantRegistButton = findViewById(R.id.restaurantRegistButton);
        imageRegistButton = findViewById(R.id.imageRegistButton);

        getImageButton = findViewById(R.id.getImageButton);

        imageSelectSpinner = findViewById(R.id.imageSelectSpinner);

        restaurantInnerView = findViewById(R.id.restaurantInnerView);
    }

    public void selectSpinner() {

    }

}