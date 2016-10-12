package com.example.zhangzhongzheng.retrofit_mvp.tabhost;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangzhongzheng.retrofit_mvp.R;
import com.example.zhangzhongzheng.retrofit_mvp.utils.MyLogger;

/**
 * Created by zhangzhongzheng on 2016/9/18.
 */
public class Fragment2 extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLogger.log("fragment", "fragment2 onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ((TextView) view.findViewById(R.id.hello)).setText("Hello Fragment2");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        MyLogger.log("fragment", "fragment2 onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.log("fragment", "fragment2 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        MyLogger.log("fragment", "fragment2 onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLogger.log("fragment", "fragment2 onDestroy");
    }
}
