package com.softtanck.lb_enduser_android_alpha.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.softtanck.lb_enduser_android_alpha.Bean.AdInfo;
import com.softtanck.lb_enduser_android_alpha.R;
import com.softtanck.lb_enduser_android_alpha.adapter.NewsPagerAdapter;
import com.softtanck.lb_enduser_android_alpha.utils.UIHelper;
import com.softtanck.lb_enduser_android_alpha.view.KJScrollView;
import com.softtanck.lb_enduser_android_alpha.view.ViewpagerScroll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanck on 12/3/2015.
 * <p>
 * TODO Impl ad.
 */
public class PagerImpl extends BaseImpl implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private ViewPager viewPager;

    private ImageView imagePoint;

    private List<AdInfo> list;

    private NewsPagerAdapter adapter;

    private int currentItem;

    private int tempSize;

    private int currentIndex;

    private boolean isTouch;

    private boolean toggle;

    private ViewpagerScroll scroll;

    private KJScrollView scrollView;

    private String[] urls = {
            "http://10.50.20.73:8081/lb-admin-console/upload/advertisement/src_e611cd00-e57a-46b7-a566-9a5e501e6433.png",
            "http://10.50.20.73:8081/lb-admin-console/upload/advertisement/src_a4eace41-af54-4a22-bc94-67026857a5c5.png",
            "http://10.50.20.73:8081/lb-admin-console/upload/advertisement/src_a4eace41-af54-4a22-bc94-67026857a5c5.png",
            "http://10.50.20.73:8081/lb-admin-console/upload/advertisement/src_49bb060d-332e-4675-84a4-793f6f14e77d.png"};

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!toggle) {
                if (!isTouch && !scrollView.getisMoved())
                    viewPager.setCurrentItem(currentItem++);
                handler.sendEmptyMessageDelayed(currentItem, 3000);
            }
        }
    };


    public <T extends Context> PagerImpl(T t) {
        super(t);
        initView();
    }


    public void initView() {
        viewPager = (ViewPager) getView(R.id.vp_news);
        imagePoint = (ImageView) getView(R.id.iv_point);
        list = new ArrayList<>();

        // TODO need get server info .
        for (int i = 0; i < 4; i++) {
            AdInfo adInfo = new AdInfo();
            adInfo.setDec("------" + i);
            adInfo.setUrl(urls[i]);
            adInfo.setImageView(new ImageView(context));
            list.add(adInfo);
        }

        tempSize = list.size();
        adapter = new NewsPagerAdapter(context, list);
        viewPager.setAdapter(adapter);
        currentItem = Integer.MAX_VALUE / 2;
        currentItem = currentItem - ((Integer.MAX_VALUE / 2) % tempSize);
        viewPager.setCurrentItem(currentItem);
        viewPager.addOnPageChangeListener(this);
        UIHelper.drawPoint(tempSize, 0, imagePoint);
        scroll = new ViewpagerScroll(context, viewPager, 1500);
        handler.sendEmptyMessageDelayed(currentItem, 3500);

        viewPager.setOnTouchListener(this);
        //兼容性
        scrollView = (KJScrollView) getView(R.id.scroll_home);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        currentIndex = position % tempSize;
        UIHelper.drawPoint(tempSize, currentIndex, imagePoint);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:// 按下
                isTouch = true;
                scroll.setScrollSpeed(250);
                scrollView.setIsCanMove(isTouch);
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                scroll.setScrollSpeed(1500);
                scrollView.setIsCanMove(false);
                scrollView.requestDisallowInterceptTouchEvent(false);
                break;
            default:
                scrollView.requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }

    public void stop() {
        toggle = true;
    }

    public void start() {
        if (toggle) { // 保证该开关是被开启过
            toggle = false;
            handler.sendEmptyMessageDelayed(currentItem, 3500);
        }
    }
}
