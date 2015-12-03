package com.softtanck.lb_enduser_android_alpha.impl;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by Tanck on 12/3/2015.
 */
public class BaseImpl {


    protected Activity context;

    public <T extends Context> BaseImpl(T t) {
        this.context = (Activity) t;
        if (null == context)
            throw new IllegalArgumentException("this context is null");
    }


    /**
     * 根据ID寻找View
     *
     * @param id
     * @return
     */
    protected View getView(int id) {
        return this.context.findViewById(id);
    }
}
