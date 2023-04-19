package com.example.yesik;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private ArrayList<RestaurantItem> restaurantList;

    RestaurantViewHolder restaurantViewHolder;

    public RestaurantAdapter (ArrayList<RestaurantItem> restaurantItems) {
        restaurantList = restaurantItems;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        ImageView logoView;
        TextView restaurantName;

        SharedPreferences saveRestaurantInfo;

        public RestaurantViewHolder(Context context, View itemView) {
            super(itemView);

            logoView = itemView.findViewById(R.id.logoView);
            restaurantName = itemView.findViewById(R.id.restaurantName);

            saveRestaurantInfo = context.getSharedPreferences("Reservation", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = saveRestaurantInfo.edit();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editor.putString("Selected Restaurant Name", restaurantList.get(getBindingAdapterPosition()).getRestaurantName());
                    editor.putString("Selected Restaurant Place", restaurantList.get(getBindingAdapterPosition()).getRestaurantPlace());
                    editor.commit();

                    Intent putInfo = new Intent(context, Reserve_Time.class);
                    context.startActivity(putInfo);
                }
            });
        }
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.restaurant_list, parent, false);

        restaurantViewHolder = new RestaurantViewHolder(context, view);

        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull RestaurantViewHolder holder, int position) {
        holder.logoView.setImageBitmap(restaurantList.get(position).getLogo());
        holder.restaurantName.setText(restaurantList.get(position).getRestaurantName() + " " + restaurantList.get(position).getRestaurantPlace());
    }

    @Override
    public int getItemCount() {
        return (null != restaurantList ? restaurantList.size() : 0);
    }

    public void addData(RestaurantItem restaurantItem) {
        restaurantList.add(0, restaurantItem);
    }

}
