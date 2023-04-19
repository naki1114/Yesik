package com.example.yesik;

public class ReservationItem {

    String restaurantName;
    String restaurantPlace;
    String reserveTime;
    String status;

    public ReservationItem(String restaurantName, String restaurantPlace, String reserveTime, String status) {
        this.restaurantName = restaurantName;
        this.restaurantPlace = restaurantPlace;
        this.reserveTime = reserveTime;
        this.status = status;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPlace() {
        return restaurantPlace;
    }

    public void setRestaurantPlace(String restaurantPlace) {
        this.restaurantPlace = restaurantPlace;
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
