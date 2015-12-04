package com.softtanck.lb_enduser_android_alpha.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * Created by winterfell on 12/3/2015.
 */
public class UIHelper {

    public static void drawPoint(int size, int currentIndex, ImageView imageView) {
        int radius = 5; // 半径
        int spacing = 30; // 点之间间隔
        Bitmap points = Bitmap.createBitmap(radius * 2 + spacing * (size - 1), radius * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(points);
        Paint paint = new Paint();
        paint.setAntiAlias(true); // 设置画笔为无锯齿
        paint.setStyle(Paint.Style.FILL); // 实心
        for (int i = 0; i < size; i++) {
            paint.setColor(Color.GRAY);
            if (currentIndex == i) // 设置选中项为白色
                paint.setColor(Color.WHITE);
            canvas.drawCircle(radius + spacing * i, radius, radius, paint);
        }
        imageView.setImageBitmap(points);
    }
}
