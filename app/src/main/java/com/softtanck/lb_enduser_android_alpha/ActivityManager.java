package com.softtanck.lb_enduser_android_alpha;

import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

/**
 * Created by winterfell on 11/27/2015.
 */
public class ActivityManager {


    private SparseArray<AppCompatActivity> sparseArray = new SparseArray<>();


    private static ActivityManager manager;

    private ActivityManager() {
    }

    /**
     * DCL 模式
     *
     * @return
     */
    public static ActivityManager getManager() {
        if (null == manager) {
            synchronized (ActivityManager.class) {
                if (null == manager)
                    manager = new ActivityManager();
            }
        }
        return manager;
    }


    /**
     * 添加一个ac
     *
     * @param activity
     */
    public void addAc(AppCompatActivity activity) {

        if (0 > sparseArray.indexOfValue(activity)) {
            sparseArray.put(activity.getClass().getSimpleName().hashCode(), activity);
        }
    }


    /**
     * 删除一个ac
     *
     * @param activity
     */
    public void rmAc(AppCompatActivity activity) {
        if (0 <= sparseArray.indexOfValue(activity)) {
            sparseArray.remove(activity.getClass().getSimpleName().hashCode());
        }
    }


    /**
     * 根据名字查找
     *
     * @param name
     * @return
     */
    public AppCompatActivity findAc(String name) {
        if (0 >= sparseArray.size())
            throw new RuntimeException("this list size is 0");
        if (null == name)
            throw new IllegalArgumentException("this name is null");
        return sparseArray.get(name.hashCode());
    }


    /**
     * 结束某个Activity
     *
     * @param name
     */
    public void destoryAc(String name) {
        AppCompatActivity activity = findAc(name);
        if (null != activity)
            activity.finish();
    }


    /**
     * 结束所有的
     */
    public void destroyAll() {
        int temp = sparseArray.size();
        for (int i = 0; i < temp; i++) {
            AppCompatActivity obj = sparseArray.valueAt(i);
            destoryAc(obj.getClass().getSimpleName());
        }
    }

}
