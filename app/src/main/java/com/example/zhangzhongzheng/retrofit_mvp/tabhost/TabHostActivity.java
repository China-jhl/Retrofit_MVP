package com.example.zhangzhongzheng.retrofit_mvp.tabhost;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.zhangzhongzheng.retrofit_mvp.R;

/**
 * Created by zhangzhongzheng on 2016/9/18.
 */
public class TabHostActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
        initView();
    }

    private void initView() {
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);//完成tabhost的初始化工作
        initTabs();//给tabhost添加tab
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(this);
    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = tabHost.newTabSpec(getString(mainTab.getResName()) + this.toString());
            View indicator = View.inflate(this, R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView icon = (ImageView) indicator.findViewById(R.id.iv_icon);

            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            icon.setImageDrawable(drawable);
            tab.setIndicator(indicator);
            tabHost.addTab(tab, mainTab.getClz(), null);
        }
    }

    @Override
    public void onTabChanged(String s) {
        final int size = tabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = tabHost.getTabWidget().getChildAt(i);
            if (i == tabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
    }
}
