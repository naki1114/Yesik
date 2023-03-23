package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Personal extends AppCompatActivity {

    String tag;

    ImageButton menuKoreanSoup;
    ImageButton menuJokBo;
    ImageButton menuPorkBeef;
    ImageButton menuWestern;
    ImageButton menuChinese;
    ImageButton menuJapanese;
    ImageButton menuFlour;
    ImageButton menuEtc;

    Button searchButton;
    Button reserveCheckButton;
    Button myInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Log.v(tag, "onCreate() 호출됨");

        tag = "개인회원 페이지";
        Log.v(tag, "onCreate() 호출됨");

        menuKoreanSoup = findViewById(R.id.menuKoreanSoup);
        menuJokBo = findViewById(R.id.menuJokBo);
        menuPorkBeef = findViewById(R.id.menuPorkBeef);
        menuWestern = findViewById(R.id.menuWestern);
        menuChinese = findViewById(R.id.menuChinese);
        menuJapanese = findViewById(R.id.menuJapanese);
        menuFlour = findViewById(R.id.menuFlour);
        menuEtc = findViewById(R.id.menuEtc);

        searchButton = findViewById(R.id.searchButton);
        reserveCheckButton = findViewById(R.id.reserveCheckButton);
        myInfoButton = findViewById(R.id.myInfoButton);

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

        menuKoreanSoup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        menuJokBo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        menuPorkBeef.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        menuWestern.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        menuChinese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        menuJapanese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        menuFlour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        menuEtc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Personal.this, Select_Restaurant.class);
                startActivity(intent);
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

}