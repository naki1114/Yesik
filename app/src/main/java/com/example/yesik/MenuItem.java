package com.example.yesik;

import android.widget.ImageView;

public class MenuItem{

    private int menuImageId;
    private String menuName;
    private String menuPrice;

    public MenuItem (int id, String menuName, String menuPrice) {
        menuImageId = id;
        this.menuName = menuName;
        this.menuPrice = "₩ " + menuPrice;
    }

    public int getMenuImage() {
        return menuImageId;
    }

    public void setMenuImage(int id) {
        menuImageId = id;
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
