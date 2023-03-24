package com.example.yesik;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    EditText menuName;
    EditText menuPrice;

    MenuViewHolder (Context context, View itemView) {
        super(itemView);

        menuName = itemView.findViewById(R.id.menuName);
        menuPrice = itemView.findViewById(R.id.menuPrice);

    }

}
