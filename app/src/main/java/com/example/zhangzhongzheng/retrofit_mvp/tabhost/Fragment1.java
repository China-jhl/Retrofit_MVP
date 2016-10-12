package com.example.zhangzhongzheng.retrofit_mvp.tabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangzhongzheng.retrofit_mvp.R;
import com.example.zhangzhongzheng.retrofit_mvp.tabhost.fragment_zhi_viewpager.PagerFragment1;
import com.example.zhangzhongzheng.retrofit_mvp.tabhost.fragment_zhi_viewpager.PagerFragment2;
import com.example.zhangzhongzheng.retrofit_mvp.tabhost.fragment_zhi_viewpager.PagerFragment3;
import com.example.zhangzhongzheng.retrofit_mvp.tabhost.fragment_zhi_viewpager.ViewPagerAdapter;
import com.example.zhangzhongzheng.retrofit_mvp.utils.MyLogger;

/**
 * Created by zhangzhongzheng on 2016/9/18.
 */
public class Fragment1 extends Fragment {

    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLogger.log("fragment", "fragment1 onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_layout, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mViewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        onSetupTabAdapter(viewPagerAdapter);//填充adapter
        tabLayout.setupWithViewPager(mViewPager);
//        tabLayout.setTabTextColors(getResources().getColor(R.color.black), getResources().getColor(R.color.app_theme_color));
//        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        MyLogger.log("fragment", "fragment1 onCreateView");
        return view;
    }

    private void onSetupTabAdapter(ViewPagerAdapter viewPagerAdapter) {
        viewPagerAdapter.addTab("f1", PagerFragment1.class, null);
        viewPagerAdapter.addTab("f2", PagerFragment2.class, null);
        viewPagerAdapter.addTab("f3", PagerFragment3.class, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        MyLogger.log("fragment", "fragment1 onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.log("fragment", "fragment1 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        MyLogger.log("fragment", "fragment1 onPause");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MyLogger.log("fragment", "fragment1 onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLogger.log("fragment", "fragment1 onDestroy");
    }
}
