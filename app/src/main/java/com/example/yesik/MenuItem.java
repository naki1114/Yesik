package com.example.yesik;

public class MenuItem{

    private String menuName;
    private String menuPrice;

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
        this.menuPrice = menuPrice;
    }

    public MenuItem (String menuName, String menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

}
