package com.example.yesik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private ArrayList<String> menuNameList;
    private ArrayList<String> menuPriceList;

    MenuViewHolder menuViewHolder;

    int menuPosition;

    public MenuAdapter () {
        menuNameList = new ArrayList<>();
        menuPriceList = new ArrayList<>();
    }

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

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.restaurant_menu_list, parent, false);

        menuViewHolder = new MenuViewHolder(context, view);

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

    public int getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(int position) {
        this.menuPosition = position;
    }

    public void addData(String menu, String price) {
        menuNameList.add(0, menu);
        menuPriceList.add(0, price);
    }

    public void changeData(String menu, String price) {
        menuPosition = 0;
    }

    public void removeData(int position) {
        menuNameList.remove(position);
        menuPriceList.remove(position);

        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}
