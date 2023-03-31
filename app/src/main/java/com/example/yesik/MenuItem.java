package com.example.yesik;

import android.graphics.Bitmap;

public class MenuItem{

    private Bitmap menuImage;
    private String menuName;
    private String menuPrice;

    public MenuItem (Bitmap menuImage, String menuName, String menuPrice) {
        this.menuImage = menuImage;
        this.menuName = menuName;
        this.menuPrice = "₩ " + menuPrice;
    }

    public Bitmap getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(Bitmap image) {
        this.menuImage = menuImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = "₩ " + menuPrice;
    }

}
