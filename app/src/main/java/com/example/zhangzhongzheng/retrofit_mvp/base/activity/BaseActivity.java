package com.example.zhangzhongzheng.retrofit_mvp.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhangzhongzheng on 2016/9/9.
 * <p/>
 * BaseActivity 主要负责生命周期等一些基本工作
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getContentView());

        initWindows();
        initWidget();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initData() {
    }

    private void initWidget() {
    }

    private void initWindows() {
    }

    protected abstract int getContentView();
}
