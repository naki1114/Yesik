package com.example.yesik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private ArrayList<String> menuNameList;
    private ArrayList<String> menuPriceList;

    public MenuAdapter () {
        menuNameList = new ArrayList<>();
        menuPriceList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.restaurant_menu_list, parent, false);

        MenuViewHolder menuViewHolder = new MenuViewHolder(context, view);

        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull MenuViewHolder holder, int position) {
        String name = menuNameList.get(position);
        String price = menuPriceList.get(position);

        holder.menuName.setText(name);
        holder.menuPrice.setText(price);
    }

    @Override
    public int getItemCount() {
        return menuNameList.size();
    }

    public void addData(String menu, String price) {
        menuNameList.add(0,menu);
        menuPriceList.add(0,price);
    }

    public void changeData(String menu, String price) {

    }

    public void removeData(String menu, String price) {

    }

}
