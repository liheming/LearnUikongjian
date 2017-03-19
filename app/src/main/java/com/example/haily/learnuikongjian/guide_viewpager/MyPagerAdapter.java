package com.example.haily.learnuikongjian.guide_viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by haily on 2016/11/22.
 */

public class MyPagerAdapter extends android.support.v4.view.PagerAdapter {
    Context context;
    List<View> views;


    MyPagerAdapter(Context context, List<View> views) {
        this.context = context;
        this.views = views;

    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
//    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
