package com.softtanck.lb_enduser_android_alpha.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.softtanck.lb_enduser_android_alpha.Bean.AdInfo;

import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/20/2015
 */
public class NewsPagerAdapter extends PagerAdapter {

    private Context context;
    private List<AdInfo> mlist;
    private ImageView imageView;

    public NewsPagerAdapter(Context context, List<AdInfo> list) {

        this.context = context;

        this.mlist = list;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % mlist.size();
        imageView = mlist.get(position).getImageView();
        container.removeView(imageView);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mlist.size();
        imageView = mlist.get(position).getImageView();
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // TODO 从服务器加载数据
        Glide.with(context).load(mlist.get(position).getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        container.addView(imageView);
        return imageView;
    }
}
