package com.example.yesik;

import android.content.Context;
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

    RestaurantItem restaurantItem;

    public RestaurantAdapter (ArrayList<RestaurantItem> restaurantItems) {
        restaurantList = restaurantItems;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView logoView;
        TextView restaurantName;

        public RestaurantViewHolder(Context context, View itemView) {
            super(itemView);

            logoView = itemView.findViewById(R.id.logoView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
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
