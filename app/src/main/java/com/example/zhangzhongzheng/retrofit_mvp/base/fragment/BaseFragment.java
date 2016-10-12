package com.example.zhangzhongzheng.retrofit_mvp.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangzhongzheng on 2016/9/9.
 * <p/>
 * BaseActiity 主要负责生命周期等一些基本工作
 */
public abstract class BaseFragment extends Fragment {
    protected View mRoot;
    protected Bundle mBundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
            initWidget(mRoot);
            initData();
        }
        return mRoot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRoot = null;
        mBundle = null;
    }

    protected void initWidget(View mRoot) {
    }


    private void onRestartInstance(Bundle savedInstanceState) {
    }

    private void initBundle(Bundle mBundle) {
    }

    protected abstract int getLayoutId();

    protected void initData() {
    }
}
