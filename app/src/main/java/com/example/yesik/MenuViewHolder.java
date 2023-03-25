package com.example.yesik;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    EditText menuName;
    EditText menuPrice;
    Button changeButton;
    Button removeButton;

    int menuPosition;

    MenuViewHolder (Context context, View itemView) {
        super(itemView);

        menuName = itemView.findViewById(R.id.menuName);
        menuPrice = itemView.findViewById(R.id.menuPrice);
        changeButton = itemView.findViewById(R.id.changeMenu);
        removeButton = itemView.findViewById(R.id.removeMenu);

    }

}
