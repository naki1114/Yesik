package com.example.yesik;

import android.graphics.Bitmap;

public class RestaurantItem {

    Bitmap logo;

    String restaurantName;
    String restaurantPlace;

    public RestaurantItem(Bitmap logo, String restaurantName, String restaurantPlace) {
        this.logo = logo;
        this.restaurantName = restaurantName;
        this.restaurantPlace = restaurantPlace;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
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

}
