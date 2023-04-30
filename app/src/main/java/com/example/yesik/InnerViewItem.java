package com.example.yesik;

import android.net.Uri;

public class InnerViewItem {

    Uri innerView;

    public InnerViewItem (Uri image) {
        this.innerView = image;
    }

    public Uri getInnerView() {
        return innerView;
    }

    public void setInnerView(Uri innerView) {
        this.innerView = innerView;
    }
}
