package com.example.zhangzhongzheng.retrofit_mvp.tabhost;

import com.example.zhangzhongzheng.retrofit_mvp.R;

/**
 * Created by zhangzhongzheng on 2016/9/18.
 */
public enum MainTab {
    ONE(0, R.string.fragment1, R.drawable.tab_icon_new, Fragment1.class),
    TWO(1, R.string.fragment2, R.drawable.tab_icon_new, Fragment2.class),
    THREE(2, R.string.fragment3, R.drawable.tab_icon_new, Fragment3.class),
    FOUR(3, R.string.fragment4, R.drawable.tab_icon_new, Fragment4.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
