package com.example.yesik;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private ArrayList<MenuItem> menuList;

    MenuViewHolder menuViewHolder;

    MenuItem menuItem;

    public MenuAdapter (ArrayList<MenuItem> menuItem) {
        menuList = menuItem;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView menuImageView;
        TextView menuNameView;
        TextView menuPriceView;
        EditText menuNameEdit;
        EditText menuPriceEdit;
        Button modifyButton;
        Button removeButton;
        Bitmap menuImage;

        private static final int REQUEST_IMAGE_CODE = 101;

        int visibleCheck = 0;

        public MenuViewHolder (Context context, View itemView) {
            super(itemView);

            menuImageView = itemView.findViewById(R.id.menuImageView);
            menuNameView = itemView.findViewById(R.id.menuNameView);
            menuPriceView = itemView.findViewById(R.id.menuPriceView);
            menuNameEdit = itemView.findViewById(R.id.menuNameEdit);
            menuPriceEdit = itemView.findViewById(R.id.menuPriceEdit);
            modifyButton = itemView.findViewById(R.id.modifyMenu);
            removeButton = itemView.findViewById(R.id.removeMenu);

            modifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifyData();
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeData();
                }
            });

        }

        // ViewHolder Custom Method
        public void modifyData() {
            if (visibleCheck % 2 == 0) {
                menuNameEdit.setText(menuNameView.getText().toString());
                menuPriceEdit.setText(menuPriceView.getText().toString().substring(2,menuPriceView.getText().length()));

                menuNameView.setVisibility(View.GONE);
                menuPriceView.setVisibility(View.GONE);
                menuNameEdit.setVisibility(View.VISIBLE);
                menuPriceEdit.setVisibility(View.VISIBLE);

                menuPriceEdit.requestFocus();
                menuPriceEdit.setSelection(menuPriceView.getText().toString().length() - 2);

                modifyButton.setText("완 료");
                removeButton.setText("취 소");

                visibleCheck++;
            }
            else {
                menuNameView.setText(menuNameEdit.getText().toString());
                menuPriceView.setText("₩ " + menuPriceEdit.getText().toString());

                menuItem = new MenuItem(menuList.get(getBindingAdapterPosition()).getMenuImage(), menuNameEdit.getText().toString(), menuPriceEdit.getText().toString());

                menuList.set(getBindingAdapterPosition(), menuItem);

                notifyItemChanged(getBindingAdapterPosition());

                menuNameView.setVisibility(View.VISIBLE);
                menuPriceView.setVisibility(View.VISIBLE);
                menuNameEdit.setVisibility(View.GONE);
                menuPriceEdit.setVisibility(View.GONE);

                modifyButton.setText("수 정");
                removeButton.setText("삭 제");

                visibleCheck--;
            }
        }

        public void removeData() {
            if (visibleCheck % 2 == 0) {
                menuList.remove(getBindingAdapterPosition());

                notifyItemRemoved(getBindingAdapterPosition());
                notifyItemRangeChanged(getBindingAdapterPosition(), menuList.size());
            }
            else {
                menuNameView.setVisibility(View.VISIBLE);
                menuPriceView.setVisibility(View.VISIBLE);
                menuNameEdit.setVisibility(View.GONE);
                menuPriceEdit.setVisibility(View.GONE);

                modifyButton.setText("수 정");
                removeButton.setText("삭 제");

                visibleCheck--;
            }
        }

    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.restaurant_menu_list, parent, false);

        menuViewHolder = new MenuViewHolder(context, view);

        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull MenuViewHolder holder, int position) {
        holder.menuImageView.setImageBitmap(menuList.get(position).getMenuImage());
        holder.menuNameView.setText(menuList.get(position).getMenuName());
        holder.menuPriceView.setText(menuList.get(position).getMenuPrice());
        holder.menuNameEdit.setText(menuList.get(position).getMenuName());
        holder.menuPriceEdit.setText(menuList.get(position).getMenuPrice());
    }

    @Override
    public int getItemCount() {
        return (null != menuList ? menuList.size() : 0);
    }

    public void addData(MenuItem menuItem) {
        menuList.add(0, menuItem);
    }

}
