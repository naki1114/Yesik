package com.example.yesik;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Restaurant_Register extends AppCompatActivity {

    String tag;

    TextView corporationNumber;
    TextView restaurantName;
    TextView restaurantPlace;
    TextView restaurantDivision;

    Button imageRegistButton;

    ImageButton getImageButton;
    ImageButton backButton;

    ImageView imageLogo;

    Spinner imageSelectSpinner;

    RecyclerView restaurantInnerView;

    ArrayList<InnerViewItem> innerViewImageList;

    InnerViewAdapter innerViewAdapter;

    SharedPreferences getUserInfo;
    SharedPreferences saveRestaurantImage;

    Uri uri;

    int spinnerGroup;
    int modifyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_register);
        Log.v(tag, "onCreate() 호출됨");

        initializing();
        loadUserInfo();
        setInnerView();
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
                Intent intent = new Intent(Restaurant_Register.this, Restaurant.class);
                startActivity(intent);
                finish();
            }
        });

        imageSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    spinnerGroup = 1;
                }
                else if (position == 2) {
                    spinnerGroup = 2;
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
                    registLogo();
                }
                else if (spinnerGroup == 2) {
                    registInnerView();
                }
                else {
                    Toast.makeText(getApplicationContext(), "사진 분류를 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        innerViewAdapter.setOnImageClickListener(new InnerViewAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(View v, int position) {
                android.app.AlertDialog.Builder dlg = new AlertDialog.Builder(Restaurant_Register.this);
                dlg.setTitle("선택");

                String[] selectDivision = new String[] {"수정", "삭제"};

                dlg.setSingleChoiceItems(selectDivision, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selectDivision[which].equals("수정")) {
                            modifyData = 1;
                        }
                        else {
                            modifyData = 2;
                        }
                    }
                });

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (modifyData == 1) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            launcher.launch(intent);
                        }
                        else if (modifyData == 2) {
                            // 삭제
                        }
                    }
                });

                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        modifyData = 1;
                    }
                });
                dlg.show();

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

        restaurantInnerView = (RecyclerView) findViewById(R.id.restaurantInnerView);
        restaurantInnerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        restaurantInnerView.addItemDecoration(new DividerItemDecoration(this, 1));

        corporationNumber = findViewById(R.id.corporationNumber);
        restaurantName = findViewById(R.id.restaurantName);
        restaurantPlace = findViewById(R.id.restaurantPlace);
        restaurantDivision = findViewById(R.id.restaurantDivision);

        imageRegistButton = findViewById(R.id.imageRegistButton);
        backButton = findViewById(R.id.backButton);

        imageLogo = findViewById(R.id.imageLogo);

        getImageButton = findViewById(R.id.getImageButton);

        imageSelectSpinner = findViewById(R.id.imageSelectSpinner);

        restaurantInnerView = findViewById(R.id.restaurantInnerView);

        getUserInfo = getSharedPreferences("UserInfoSplit", MODE_PRIVATE);
        saveRestaurantImage = getSharedPreferences("RestaurantImage", MODE_PRIVATE);

        innerViewImageList = new ArrayList<>();
        innerViewAdapter = new InnerViewAdapter(innerViewImageList);
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
                uri = result.getData().getData();
                Glide.with(Restaurant_Register.this)
                        .load(uri)
                        .into(getImageButton);
            }
            else if (result.getResultCode() == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show();
            }
        }
    });

    public void registInnerView() {
        if (uri == null) {
            Toast.makeText(getApplicationContext(), "사진을 선택하세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            saveInnerView();

            InnerViewItem innerViewItem = new InnerViewItem(uri);

            innerViewAdapter.addData(innerViewItem);
            restaurantInnerView.setAdapter(innerViewAdapter);

            innerViewAdapter.notifyDataSetChanged();
            getImageButton.setImageResource(R.drawable.no_image);
            uri = null;
        }
    }

    public void registLogo() {
        if (uri == null) {
            Toast.makeText(getApplicationContext(), "사진을 선택하세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            saveLogo();

            Glide.with(Restaurant_Register.this)
                    .load(uri)
                    .into(imageLogo);
            uri = null;
            getImageButton.setImageResource(R.drawable.no_image);
        }
    }

    public void loadUserInfo() {
        String[] corporationNumberList = getUserInfo.getString("Restaurant Corporation Number", "").split("⊙");
        String[] restaurantNameList = getUserInfo.getString("Restaurant Name", "").split("⊙");
        String[] restaurantPlaceList = getUserInfo.getString("Restaurant Place", "").split("⊙");
        String[] restaurantDivisionList = getUserInfo.getString("Restaurant Division", "").split("⊙");
        int userIndex = getUserInfo.getInt("Login User Index", 0);
        String userId = getUserInfo.getString("Login User ID", "");

        if (!saveRestaurantImage.getString(userId + " Logo", "").equals("")) {
            Glide.with(Restaurant_Register.this)
                    .load(Uri.parse(saveRestaurantImage.getString(userId + " Logo", "")))
                    .into(imageLogo);
        }

        corporationNumber.setText(corporationNumberList[userIndex].substring(0,3) + " - " + corporationNumberList[userIndex].substring(3,5) + " - " + corporationNumberList[userIndex].substring(5,10));
        restaurantName.setText(restaurantNameList[userIndex]);
        restaurantPlace.setText(restaurantPlaceList[userIndex]);
        restaurantDivision.setText(restaurantDivisionList[userIndex]);
    }

    public void saveLogo() {
        SharedPreferences.Editor saveImage = saveRestaurantImage.edit();
        String userId = getUserInfo.getString("Login User ID", "");

        saveImage.putString(userId + " Logo", uri.toString());
        saveImage.commit();
    }

    public void saveInnerView() {
        SharedPreferences.Editor saveImage = saveRestaurantImage.edit();
        String userId = getUserInfo.getString("Login User ID", "");

        saveImage.putString(userId + " Inner View", saveRestaurantImage.getString(userId + " Inner View", "") + "⊙" + uri.toString());
        saveImage.commit();
    }

    public void setInnerView() {
        String userId = getUserInfo.getString("Login User ID", "");
        String[] innerViewList = saveRestaurantImage.getString(userId + " Inner View", "").split("⊙");

        for (int count = 1; count < innerViewList.length; count++) {
            InnerViewItem innerViewItem = new InnerViewItem (Uri.parse(innerViewList[count]));

            innerViewAdapter.addData(innerViewItem);
        }
        restaurantInnerView.setAdapter(innerViewAdapter);
        innerViewAdapter.notifyDataSetChanged();
    }

}