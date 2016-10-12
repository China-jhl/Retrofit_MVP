package com.example.zhangzhongzheng.retrofit_mvp.tabhost.fragment_zhi_viewpager;

import android.os.Bundle;

public final class ViewPageInfo {

    public final String tag;
    public final Class<?> clss;
    public final Bundle args;

    public ViewPageInfo(String _tag, Class<?> _class, Bundle _args) {
        tag = _tag;
        clss = _class;
        args = _args;
    }
}