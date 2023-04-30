package com.example.yesik;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

        Spinner status;

        SharedPreferences getReserveStatus;
        SharedPreferences.Editor editor;

        String manageStatus;

        public ManageViewHolder(Context context, View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            userID = itemView.findViewById(R.id.userID);
            reserveTime = itemView.findViewById(R.id.reserveTime);
            status = itemView.findViewById(R.id.status);

            getReserveStatus = context.getSharedPreferences("Reservation", Context.MODE_PRIVATE);
            editor = getReserveStatus.edit();

            status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (status.getSelectedItem().toString().equals("대기")) {
                        int itemNumber = manageReservationList.get(getBindingAdapterPosition()).getNumber();
                        String itemUser = manageReservationList.get(getBindingAdapterPosition()).getUserID();
                        String itemTime = manageReservationList.get(getBindingAdapterPosition()).getReserveTime();
                        String itemStatus = "대기";
                        ManageReservationItem manageItem = new ManageReservationItem(itemNumber,itemUser,itemTime,itemStatus);
                        manageReservationList.set(getBindingAdapterPosition(), manageItem);

                        String[] statusList = getReserveStatus.getString("Reserve Status", "").split("⊙");
                        String[] reserveUserList = getReserveStatus.getString("Reserve User", "").split("⊙");
                        String[] reserveHourList = getReserveStatus.getString("Reserve Time (hour)", "").split("⊙");
                        String[] reserveMinuteList = getReserveStatus.getString("Reserve Time (minute)", "").split("⊙");
                        String statusRe = "";

                        for (int count1 = 1; count1 < statusList.length; count1++) {
                            for (int count2 = 0; count2 < manageReservationList.size(); count2++) {
                                if (reserveHourList[count1].equals("0")) {
                                    reserveHourList[count1] = "00";
                                }
                                if (reserveMinuteList[count1].equals("0")) {
                                    reserveMinuteList[count1] = "00";
                                }
                                if (reserveUserList[count1].equals(manageReservationList.get(count2).getUserID())
                                        && (reserveHourList[count1] + " : " + reserveMinuteList[count1]).equals(manageReservationList.get(count2).getReserveTime())) {
                                    statusList[count1] = manageReservationList.get(count2).getStatus();
                                }
                            }
                            statusRe += "⊙" + statusList[count1];
                        }

                        editor.putString("Reserve Status", statusRe);
                        editor.commit();
                    }
                    else if (status.getSelectedItem().toString().equals("승인")) {
                        int itemNumber = manageReservationList.get(getBindingAdapterPosition()).getNumber();
                        String itemUser = manageReservationList.get(getBindingAdapterPosition()).getUserID();
                        String itemTime = manageReservationList.get(getBindingAdapterPosition()).getReserveTime();
                        String itemStatus = "승인";
                        ManageReservationItem manageItem = new ManageReservationItem(itemNumber,itemUser,itemTime,itemStatus);
                        manageReservationList.set(getBindingAdapterPosition(), manageItem);

                        String[] statusList = getReserveStatus.getString("Reserve Status", "").split("⊙");
                        String[] reserveUserList = getReserveStatus.getString("Reserve User", "").split("⊙");
                        String[] reserveHourList = getReserveStatus.getString("Reserve Time (hour)", "").split("⊙");
                        String[] reserveMinuteList = getReserveStatus.getString("Reserve Time (minute)", "").split("⊙");
                        String statusRe = "";

                        for (int count1 = 1; count1 < statusList.length; count1++) {
                            for (int count2 = 0; count2 < manageReservationList.size(); count2++) {
                                if (reserveHourList[count1].equals("0")) {
                                    reserveHourList[count1] = "00";
                                }
                                if (reserveMinuteList[count1].equals("0")) {
                                    reserveMinuteList[count1] = "00";
                                }
                                if (reserveUserList[count1].equals(manageReservationList.get(count2).getUserID())
                                        && (reserveHourList[count1] + " : " + reserveMinuteList[count1]).equals(manageReservationList.get(count2).getReserveTime())) {
                                    statusList[count1] = manageReservationList.get(count2).getStatus();
                                }
                            }
                            statusRe += "⊙" + statusList[count1];
                        }

                        editor.putString("Reserve Status", statusRe);
                        editor.commit();
                    }
                    else {
                        int itemNumber = manageReservationList.get(getBindingAdapterPosition()).getNumber();
                        String itemUser = manageReservationList.get(getBindingAdapterPosition()).getUserID();
                        String itemTime = manageReservationList.get(getBindingAdapterPosition()).getReserveTime();
                        String itemStatus = "거절";
                        ManageReservationItem manageItem = new ManageReservationItem(itemNumber,itemUser,itemTime,itemStatus);
                        manageReservationList.set(getBindingAdapterPosition(), manageItem);

                        String[] statusList = getReserveStatus.getString("Reserve Status", "").split("⊙");
                        String[] reserveUserList = getReserveStatus.getString("Reserve User", "").split("⊙");
                        String[] reserveHourList = getReserveStatus.getString("Reserve Time (hour)", "").split("⊙");
                        String[] reserveMinuteList = getReserveStatus.getString("Reserve Time (minute)", "").split("⊙");
                        String statusRe = "";

                        for (int count1 = 1; count1 < statusList.length; count1++) {
                            for (int count2 = 0; count2 < manageReservationList.size(); count2++) {
                                if (reserveHourList[count1].equals("0")) {
                                    reserveHourList[count1] = "00";
                                }
                                if (reserveMinuteList[count1].equals("0")) {
                                    reserveMinuteList[count1] = "00";
                                }
                                if (reserveUserList[count1].equals(manageReservationList.get(count2).getUserID())
                                        && (reserveHourList[count1] + " : " + reserveMinuteList[count1]).equals(manageReservationList.get(count2).getReserveTime())) {
                                    statusList[count1] = manageReservationList.get(count2).getStatus();
                                }
                            }
                            statusRe += "⊙" + statusList[count1];
                        }

                        editor.putString("Reserve Status", statusRe);
                        editor.commit();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

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
        if (manageReservationList.get(position).getStatus().equals("대기")) {
            holder.status.setSelection(0);
        }
        else if (manageReservationList.get(position).getStatus().equals("승인")) {
            holder.status.setSelection(1);
        }
        else {
            holder.status.setSelection(2);
        }

    }

    @Override
    public int getItemCount() {
        return (null != manageReservationList ? manageReservationList.size() : 0);
    }

    public void addData(ManageReservationItem manageReservationItem) {
        manageReservationList.add(0, manageReservationItem);
    }

}
