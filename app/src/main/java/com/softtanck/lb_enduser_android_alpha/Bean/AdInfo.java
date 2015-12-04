package com.softtanck.lb_enduser_android_alpha.Bean;

import android.widget.ImageView;

/**
 * Created by Tanck on 12/3/2015.
 */
public class AdInfo {


    private String url;//广告的地址[远程地址]

    private String dec;//广告描述

    private ImageView imageView;// 图片

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
