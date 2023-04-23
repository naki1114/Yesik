package com.example.yesik;

public class ManageReservationItem {

    int number;

    String userID;
    String reserveTime;
    String status;

    public ManageReservationItem (int number, String userID, String reserveTime, String status) {
        this.number = number;
        this.userID = userID;
        this.reserveTime = reserveTime;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
