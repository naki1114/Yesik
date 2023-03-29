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

import java.util.ArrayList;

public class Menu_Register extends AppCompatActivity {

    ArrayList<MenuItem> menuItemList;

    String tag;

    RecyclerView menuList;
    MenuAdapter menuAdapter;
    EditText inputMenu;
    EditText inputPrice;
    Button registButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

        tag = "메뉴 등록 페이지";
        Log.v(tag, "onCreate() 호출됨");

        menuList = (RecyclerView) findViewById(R.id.menuRecyclerView);
        menuList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        menuList.addItemDecoration(new DividerItemDecoration(this, 1));

        inputMenu = findViewById(R.id.menuInput);
        inputPrice = findViewById(R.id.priceInput);
        registButton = findViewById(R.id.registerButton);

        menuItemList = new ArrayList<>();

        menuAdapter = new MenuAdapter(menuItemList);
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
                MenuItem menuItem = new MenuItem(inputMenu.getText().toString(), inputPrice.getText().toString());

                menuAdapter.addData(menuItem);
                menuList.setAdapter(menuAdapter);

                menuAdapter.notifyDataSetChanged();

                inputMenu.setText("");
                inputPrice.setText("");

                inputMenu.requestFocus();
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