package com.example.yesik;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuViewAdapter extends RecyclerView.Adapter<MenuViewAdapter.MenuViewViewHolder> {

    private ArrayList<MenuItem> menuList;

    MenuViewViewHolder menuViewViewHolder;

    public MenuViewAdapter (ArrayList<MenuItem> menuItem) {
        menuList = menuItem;
    }

    public class MenuViewViewHolder extends RecyclerView.ViewHolder {

        ImageView menuImageView;
        TextView menuNameView;
        TextView menuPriceView;

        public MenuViewViewHolder (Context context, View itemView) {
            super(itemView);

            menuImageView = itemView.findViewById(R.id.menuImageView);
            menuNameView = itemView.findViewById(R.id.menuNameView);
            menuPriceView = itemView.findViewById(R.id.menuPriceView);
        }
    }

    @NonNull
    @Override
    public MenuViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_view, parent, false);

        menuViewViewHolder = new MenuViewViewHolder(context, view);

        return menuViewViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull MenuViewViewHolder holder, int position) {
        holder.menuImageView.setImageBitmap(menuList.get(position).getMenuImage());
        holder.menuNameView.setText(menuList.get(position).getMenuName());
        holder.menuPriceView.setText(menuList.get(position).getMenuPrice());
    }

    @Override
    public int getItemCount() {
        return (null != menuList ? menuList.size() : 0);
    }

    public void addData(MenuItem menuItem) {
        menuList.add(0, menuItem);
    }

}
