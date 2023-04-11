package com.example.yesik;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Restaurant_Register extends AppCompatActivity {

    String tag;

    EditText nameInput;
    EditText placeInput;

    Button restaurantRegistButton;
    Button imageRegistButton;

    ImageButton getImageButton;

    ImageView imageLogo;

    Spinner imageSelectSpinner;

    RecyclerView restaurantInnerView;

    Bitmap getImage;

    int spinnerGroup;

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
                    spinnerGroup = 1;
                    Toast.makeText(getApplicationContext(), "로고", Toast.LENGTH_SHORT).show();
                }
                else if (position == 2) {
                    spinnerGroup = 2;
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

        getImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGalleryImage();
            }
        });

        imageRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinnerGroup == 1) {
                    imageLogo.setImageBitmap(getImage);
                }
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

        imageLogo = findViewById(R.id.imageLogo);

        getImageButton = findViewById(R.id.getImageButton);

        imageSelectSpinner = findViewById(R.id.imageSelectSpinner);

        restaurantInnerView = findViewById(R.id.restaurantInnerView);
    }

    public void selectGalleryImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Uri uri = result.getData().getData();
                try {
                    getImage = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    getImageButton.setImageBitmap(getImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (result.getResultCode() == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show();
            }
        }
    });

}