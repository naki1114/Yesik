package com.example.yesik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu_Register extends AppCompatActivity {

    ArrayList<MenuItem> menuItemList;

    String tag;

    Bitmap menuBitmapImage;

    private static final int REQUEST_IMAGE_CODE = 101;

    RecyclerView menuList;
    MenuAdapter menuAdapter;
    EditText inputMenu;
    EditText inputPrice;
    Button registButton;
    ImageButton menuImageAddButton;
    BitmapConverter imageConverter;
    SharedPreferences sharedPreferencesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

        tag = "메뉴 등록 페이지";
        Log.v(tag, "onCreate() 호출됨");

        initializing();
        loadMenuItem();
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

    // Custom method

    public void initializing() {
        menuList = (RecyclerView) findViewById(R.id.menuRecyclerView);
        menuList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        menuList.addItemDecoration(new DividerItemDecoration(this, 1));

        inputMenu = findViewById(R.id.menuInput);
        inputPrice = findViewById(R.id.priceInput);
        registButton = findViewById(R.id.registerButton);
        menuImageAddButton = findViewById(R.id.menuImageButton);

        sharedPreferencesItem = getSharedPreferences("MenuItem", MODE_PRIVATE);

        menuItemList = new ArrayList<>();
        imageConverter = new BitmapConverter();
        menuAdapter = new MenuAdapter(menuItemList);
    }

    public void menuAdd() {
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputMenu.getText().toString().equals("") || inputPrice.getText().toString().equals("") || menuBitmapImage == null) {
                    Toast.makeText(getApplicationContext(), "사진, 메뉴, 가격을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    MenuItem menuItem = new MenuItem(menuBitmapImage, inputMenu.getText().toString(), inputPrice.getText().toString());

                    menuAdapter.addData(menuItem);
                    menuList.setAdapter(menuAdapter);

                    saveMenuItem();

                    menuAdapter.notifyDataSetChanged();

                    inputMenu.setText("");
                    inputPrice.setText("");
                    menuImageAddButton.setImageResource(R.drawable.no_image);
                    menuBitmapImage = null;

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
            menuBitmapImage = (Bitmap) extras.get("data");
            menuImageAddButton.setImageBitmap(menuBitmapImage);
        }
    }

    public void saveMenuItem () {
        SharedPreferences.Editor editorSaveItem = sharedPreferencesItem.edit();

        editorSaveItem.putString("Menu Name", sharedPreferencesItem.getString("Menu Name", "") + "⊙" + inputMenu.getText().toString());
        editorSaveItem.putString("Menu Price", sharedPreferencesItem.getString("Menu Price", "") + "⊙" + inputPrice.getText().toString());
        editorSaveItem.putString("Menu Image", sharedPreferencesItem.getString("Menu Image", "") + "⊙" + imageConverter.BitmapToString(menuBitmapImage));

        editorSaveItem.commit();
    }

    public void loadMenuItem () {
        String[] menuNameList = sharedPreferencesItem.getString("Menu Name", "").split("⊙");
        String[] priceList = sharedPreferencesItem.getString("Menu Price", "").split("⊙");
        String[] imageList = sharedPreferencesItem.getString("Menu Image", "").split("⊙");

        for (int count = 1; count < menuNameList.length; count++) {
            MenuItem loadMenuItem = new MenuItem(imageConverter.StringToBitmap(imageList[count]), menuNameList[count], priceList[count]);

            menuAdapter.addData(loadMenuItem);
        }
        menuList.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }

}