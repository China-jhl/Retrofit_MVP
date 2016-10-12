package com.example.zhangzhongzheng.retrofit_mvp.tabhost;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangzhongzheng.retrofit_mvp.R;

/**
 * Created by zhangzhongzheng on 2016/9/18.
 */
public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ((TextView) view.findViewById(R.id.hello)).setText("Hello Fragment3");
        return view;
    }
}
