package com.example.yesik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class Menu_Register extends AppCompatActivity {

    DecimalFormat priceFormat;

    String tag;

    RecyclerView menuList;
    MenuAdapter menuAdapter;
    EditText inputMenu;
    EditText inputPrice;
    Button registButton;

    int listPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

        tag = "메뉴 등록 페이지";
        Log.v(tag, "onCreate() 호출됨");

        priceFormat = new DecimalFormat("###,###");

        menuList = (RecyclerView) findViewById(R.id.menuRecyclerView);
        menuList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        menuList.addItemDecoration(new DividerItemDecoration(this, 1));

        inputMenu = findViewById(R.id.menuInput);
        inputPrice = findViewById(R.id.priceInput);
        registButton = findViewById(R.id.registerButton);

        menuAdapter = new MenuAdapter();
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

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menu = inputMenu.getText().toString();
                String price = inputPrice.getText().toString();

                menuAdapter.addData(menu, price);
                menuList.setAdapter(menuAdapter);

                inputMenu.setText("");
                inputPrice.setText("");
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