package com.example.yesik;

import android.graphics.Bitmap;

public class InnerViewItem {

    Bitmap innerView;

    public InnerViewItem (Bitmap image) {
        this.innerView = image;
    }

    public Bitmap getInnerView() {
        return innerView;
    }

    public void setInnerView(Bitmap innerView) {
        this.innerView = innerView;
    }
}
