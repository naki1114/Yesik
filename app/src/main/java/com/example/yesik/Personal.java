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

    String division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
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

        menuKoreanSoup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "koreansoup";
                changeScreen();
            }
        });

        menuJokBo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "jokbo";
                changeScreen();
            }
        });

        menuPorkBeef.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "porkbeef";
                changeScreen();
            }
        });

        menuWestern.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "western";
                changeScreen();
            }
        });

        menuChinese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "chinese";
                changeScreen();
            }
        });

        menuJapanese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "japanese";
                changeScreen();
            }
        });

        menuFlour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "flour";
                changeScreen();
            }
        });

        menuEtc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "etc";
                changeScreen();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                division = "all";
                changeScreen();
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
        tag = "개인회원 페이지";

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

    public void changeScreen() {
        Intent putIntent = new Intent(Personal.this, Select_Restaurant.class);
        putIntent.putExtra("division", division);
        startActivity(putIntent);
    }

}