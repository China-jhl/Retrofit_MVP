package com.example.zhangzhongzheng.retrofit_mvp.appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.zhangzhongzheng.retrofit_mvp.R;

/**
 * Created by zhangzhongzheng on 2016/10/5.
 */

public class Activity_APL extends AppCompatActivity {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbarlayout);
        initToolbar();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.abl_tablayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.abl_toolbar);
        setSupportActionBar(mToolbar);
    }
}
