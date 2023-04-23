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

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.ManageViewHolder> {

    private ArrayList<ManageReservationItem> manageReservationList;

    ManageViewHolder manageViewHolder;

    public ManageAdapter (ArrayList<ManageReservationItem> manageReservation) {
        manageReservationList = manageReservation;
    }

    public class ManageViewHolder extends RecyclerView.ViewHolder {

        TextView number;
        TextView userID;
        TextView reserveTime;
        Button status;

        public ManageViewHolder(Context context, View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            userID = itemView.findViewById(R.id.userID);
            reserveTime = itemView.findViewById(R.id.reserveTime);
            status = itemView.findViewById(R.id.status);
        }
    }

    @NonNull
    @Override
    public ManageAdapter.ManageViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.manage_reserve_list, parent, false);

        manageViewHolder = new ManageAdapter.ManageViewHolder(context, view);

        return manageViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull ManageAdapter.ManageViewHolder holder, int position) {
        holder.number.setText(String.valueOf(manageReservationList.get(position).getNumber()));
        holder.userID.setText(manageReservationList.get(position).getUserID());
        holder.reserveTime.setText(manageReservationList.get(position).getReserveTime());
        holder.status.setText(manageReservationList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return (null != manageReservationList ? manageReservationList.size() : 0);
    }

    public void addData(ManageReservationItem manageReservationItem) {
        manageReservationList.add(0, manageReservationItem);
    }

}
