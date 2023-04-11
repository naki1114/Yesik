package com.example.yesik;

import android.graphics.Bitmap;

public class InnerViewItem {

    Bitmap image;

    public InnerViewItem (Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
