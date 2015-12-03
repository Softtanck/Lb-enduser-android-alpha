package com.softtanck.lb_enduser_android_alpha.impl;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.softtanck.lb_enduser_android_alpha.Bean.AdInfo;
import com.softtanck.lb_enduser_android_alpha.R;
import com.softtanck.lb_enduser_android_alpha.adapter.NewsPagerAdapter;
import com.softtanck.lb_enduser_android_alpha.utils.UIHelper;
import com.softtanck.lb_enduser_android_alpha.view.ViewpagerScroll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanck on 12/3/2015.
 * <p>
 * TODO Impl ad.
 */
public class PagerImpl extends BaseImpl implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;

    private ImageView iamgePoint;

    private List<AdInfo> list;

    public <T extends Context> PagerImpl(T t) {
        super(t);
        initView();
    }


    public void initView() {
        viewPager = (ViewPager) getView(R.id.vp_news);
        iamgePoint = (ImageView) getView(R.id.iv_point);
        list = new ArrayList<>();

        // TODO need get server info .
        for (int i = 0; i < 5; i++) {
            AdInfo adInfo = new AdInfo();
            adInfo.setDec("------" + i);
            adInfo.setUrl("我是从服务器获取的地址");
            list.add(adInfo);
        }
        pageadapter = new NewsPagerAdapter(context, ivlist);
        viewPager.setAdapter(pageadapter);
        currentItem = Integer.MAX_VALUE / 2;
        currentItem = currentItem - ((Integer.MAX_VALUE / 2) % ivlist.size());
        viewPager.setCurrentItem(currentItem);
        viewPager.addOnPageChangeListener(new PagerImpl(ivlist.size(), currentItem, imageView));
        UIHelper.drawPoint(ivlist.size(), 0, imageView);
        new ViewpagerScroll(context, viewPager, 1500);
        handler.sendEmptyMessageDelayed(currentItem, 3500);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        currentIndex = position % size;
        UIHelper.drawPoint(size, currentIndex, imageView);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
