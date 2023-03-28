package com.example.yesik;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        TextView menuNameView;
        TextView menuPriceView;
        EditText menuNameEdit;
        EditText menuPriceEdit;
        Button modifyButton;
        Button removeButton;

        int menuPosition;
        int visibleCheck = 0;

        MenuViewHolder (Context context, View itemView) {
            super(itemView);

            menuNameView = itemView.findViewById(R.id.menuNameView);
            menuPriceView = itemView.findViewById(R.id.menuPriceView);
            menuNameEdit = itemView.findViewById(R.id.menuNameEdit);
            menuPriceEdit = itemView.findViewById(R.id.menuPriceEdit);
            modifyButton = itemView.findViewById(R.id.modifyMenu);
            removeButton = itemView.findViewById(R.id.removeMenu);

            modifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (visibleCheck % 2 == 0) {
                        menuNameEdit.setText(menuNameView.getText().toString());
                        menuPriceEdit.setText(menuPriceView.getText().toString().substring(2,menuPriceView.getText().length()));

                        menuNameEdit.requestFocus();
                        menuNameEdit.setSelection(menuNameEdit.getText().length());

                        menuNameView.setVisibility(View.GONE);
                        menuPriceView.setVisibility(View.GONE);
                        menuNameEdit.setVisibility(View.VISIBLE);
                        menuPriceEdit.setVisibility(View.VISIBLE);

                        modifyButton.setText("완 료");
                        removeButton.setText("취 소");

                        visibleCheck++;
                    }
                    else {
                        menuNameView.setText(menuNameEdit.getText().toString());
                        menuPriceView.setText("₩ " + menuPriceEdit.getText().toString());

                        menuNameView.setVisibility(View.VISIBLE);
                        menuPriceView.setVisibility(View.VISIBLE);
                        menuNameEdit.setVisibility(View.GONE);
                        menuPriceEdit.setVisibility(View.GONE);

                        modifyButton.setText("수 정");
                        removeButton.setText("삭 제");

                        visibleCheck++;
                    }
                }
            });

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

        holder.menuNameView.setText(name);
        holder.menuPriceView.setText(price);
        holder.menuNameEdit.setText(name);
        holder.menuPriceEdit.setText(price);
    }

    @Override
    public int getItemCount() {
        return menuNameList.size();
    }

    public void addData(String menu, String price) {
        menuNameList.add(0, menu);
        menuPriceList.add(0, "₩ " + price);
        for (int i = 0; i < menuNameList.size(); i++) {
            Log.v("메뉴", menuNameList.get(i));
            Log.v("가격", menuPriceList.get(i));
        }
    }

    public void modifyData(String menu, String price) {
        menuPosition = 0;
    }

    public void removeData(int position) {
        menuNameList.remove(position);
        menuPriceList.remove(position);

        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}
