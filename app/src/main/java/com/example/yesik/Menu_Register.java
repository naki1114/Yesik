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
    ImageButton backButton;

    BitmapConverter imageConverter;

    SharedPreferences sharedPreferencesItem;
    SharedPreferences getUserID;

    String userID;

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

        menuImageAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuImageAdd();
            }
        });
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuAdd();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Register.this, Restaurant.class);
                startActivity(intent);
                finish();
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
        backButton = findViewById(R.id.backButton);

        sharedPreferencesItem = getSharedPreferences("MenuItem", MODE_PRIVATE);
        getUserID = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);

        menuItemList = new ArrayList<>();
        imageConverter = new BitmapConverter();
        menuAdapter = new MenuAdapter(menuItemList);
    }

    public void menuAdd() {
        if (inputMenu.getText().toString().equals("") || inputPrice.getText().toString().equals("") || menuBitmapImage == null) {
            Toast.makeText(getApplicationContext(), "사진, 메뉴, 가격을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
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

    public void menuImageAdd() {
        Intent getImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (getImage.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(getImage, REQUEST_IMAGE_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            menuBitmapImage = (Bitmap) extras.get("data");
            menuImageAddButton.setImageBitmap(menuBitmapImage);
        }
    }

    public void saveMenuItem() {
        SharedPreferences.Editor editorSaveItem = sharedPreferencesItem.edit();
        userID = getUserID.getString("Login User ID","");

        editorSaveItem.putString(userID + " Menu Name", sharedPreferencesItem.getString(userID + " Menu Name", "") + "⊙" + inputMenu.getText().toString());
        editorSaveItem.putString(userID + " Menu Price", sharedPreferencesItem.getString(userID + " Menu Price", "") + "⊙" + inputPrice.getText().toString());
        editorSaveItem.putString(userID + " Menu Image", sharedPreferencesItem.getString(userID + " Menu Image", "") + "⊙" + imageConverter.BitmapToString(menuBitmapImage));

        editorSaveItem.commit();
    }

    public void loadMenuItem() {
        userID = getUserID.getString("Login User ID","");

        String[] menuNameList = sharedPreferencesItem.getString(userID + " Menu Name", "").split("⊙");
        String[] priceList = sharedPreferencesItem.getString(userID + " Menu Price", "").split("⊙");
        String[] imageList = sharedPreferencesItem.getString(userID + " Menu Image", "").split("⊙");

        for (int count = 1; count < menuNameList.length; count++) {
            MenuItem loadMenuItem = new MenuItem(imageConverter.StringToBitmap(imageList[count]), menuNameList[count], priceList[count]);

            menuAdapter.addData(loadMenuItem);
        }
        menuList.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }

}