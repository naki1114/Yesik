package com.example.yesik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ReserveViewHolder> {

    private ArrayList<ReservationItem> reservationList;

    ReserveViewHolder reserveViewHolder;

    public ReserveAdapter (ArrayList<ReservationItem> reservationList) {
        this.reservationList = reservationList;
    }

    public class ReserveViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        TextView restaurantPlace;
        TextView reserveTime;
        Button statusButton;

        public ReserveViewHolder (Context context, View itemView) {
            super(itemView);

            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantPlace = itemView.findViewById(R.id.restaurantPlace);
            reserveTime = itemView.findViewById(R.id.reserveTime);
            statusButton = itemView.findViewById(R.id.statusButton);
        }
    }

    @NonNull
    @Override
    public ReserveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.reservation_list, parent, false);

        reserveViewHolder = new ReserveAdapter.ReserveViewHolder(context, view);

        return reserveViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull ReserveAdapter.ReserveViewHolder holder, int position) {
        holder.restaurantName.setText(reservationList.get(position).getRestaurantName());
        holder.restaurantPlace.setText(reservationList.get(position).getRestaurantPlace());
        holder.reserveTime.setText(reservationList.get(position).getReserveTime());
        holder.statusButton.setText(reservationList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return (null != reservationList ? reservationList.size() : 0);
    }

    public void addData(ReservationItem reservationItem) {
        reservationList.add(0, reservationItem);
    }

}
