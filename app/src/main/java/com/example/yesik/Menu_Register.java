package com.example.yesik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu_Register extends AppCompatActivity {

    ArrayList<MenuItem> menuItemList;

    String tag;

    private static final int REQUEST_IMAGE_CODE = 101;

    RecyclerView menuList;
    MenuAdapter menuAdapter;
    EditText inputMenu;
    EditText inputPrice;
    Button registButton;
    ImageButton menuImageAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

        tag = "메뉴 등록 페이지";
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

        menuImageAdd();
        menuAdd();

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

    // 생명주기 끝

    // My method

    public void initializing() {
        menuList = (RecyclerView) findViewById(R.id.menuRecyclerView);
        menuList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        menuList.addItemDecoration(new DividerItemDecoration(this, 1));

        inputMenu = findViewById(R.id.menuInput);
        inputPrice = findViewById(R.id.priceInput);
        registButton = findViewById(R.id.registerButton);
        menuImageAddButton = findViewById(R.id.menuImageButton);

        menuItemList = new ArrayList<>();

        menuAdapter = new MenuAdapter(menuItemList);
    }

    public void menuAdd() {
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputMenu.getText().toString().equals("") || inputPrice.getText().toString().equals("") || menuImageAddButton.getResources() == null) {
                    if (menuImageAddButton.getResources() == null) {
                        Toast.makeText(getApplicationContext(), "사진을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "메뉴와 가격을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    MenuItem menuItem = new MenuItem(R.id.menuImageButton, inputMenu.getText().toString(), inputPrice.getText().toString());

                    menuItem.setMenuImage(R.id.menuImageButton);

                    Toast.makeText(getApplicationContext(), "" + menuImageAddButton.getResources(), Toast.LENGTH_SHORT).show();

                    menuAdapter.addData(menuItem);
                    menuList.setAdapter(menuAdapter);

                    menuAdapter.notifyDataSetChanged();

                    inputMenu.setText("");
                    inputPrice.setText("");

                    inputMenu.requestFocus();
                }
            }
        });
    }

    public void menuImageAdd() {
        menuImageAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (getImage.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(getImage, REQUEST_IMAGE_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap menuBitmapImage = (Bitmap) extras.get("data");
            menuImageAddButton.setImageBitmap(menuBitmapImage);
        }
    }

}