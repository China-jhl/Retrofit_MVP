package com.example.zhangzhongzheng.retrofit_mvp.tabhost.fragment_zhi_viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhangzhongzheng on 2016/9/18.
 * <p/>
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private final CharSequence[] pageTitles = new CharSequence[]{"f1", "f2", "f3"};

    private ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();
    private Map<String, Fragment> mFragments = new ArrayMap<>();

    private ViewPager mViewPager;

    public ViewPagerAdapter(FragmentManager fm, ViewPager pager) {
        super(fm);
        this.mContext = pager.getContext();
        this.mViewPager = pager;
        mViewPager.setAdapter(this);
    }

    //模仿的android官方的 TabHost addTab  动态添加
    public void addTab(String tab, Class<?> clz, Bundle args) {
        ViewPageInfo info = new ViewPageInfo(tab, clz, args);
        addFragment(info);
    }

    private void addFragment(ViewPageInfo info) {
        if (info == null) {
            return;
        }
        mTabs.add(info);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);
        Fragment fragment = mFragments.get(info.tag);
        if (fragment == null) {
            fragment = Fragment.instantiate(mContext, info.clss.getName(), info.args);
            mFragments.put(info.tag, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
