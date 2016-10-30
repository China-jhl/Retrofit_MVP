package com.example.zhangzhongzheng.retrofit_mvp.animation.Animator;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Interpolator;

/**
 * Created by 龙 on 2016-10-30.
 * <p/>
 * 设置Reveal动画中心点
 */
public class RevealAnimator {
    public static final int POSITION_CENTER = 0;//中心点是view的中心
    public static final int POSITION_TOP_CENTER = 1;
    public static final int POSITION_BOTTOM_CENTER = 2;
    public static final int POSITION_TOP_LEFT = 3;
    /**
     * 中心点位置
     */
    private int positon;
    /**
     * target view
     */
    View target;
    /**
     * 组合的Animator 或者说封装的Animator
     */
    private Animator mAnimator;

    public RevealAnimator(View target, int position) {
        this.target = target;
        this.positon = position;
        compatAnimator();
    }

    private void compatAnimator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = ViewAnimationUtils.createCircularReveal(target, getCenterX(), getCenterY(), 0, endRadius());
        } else {
        }
    }


    public void start() {
        if (mAnimator != null) {
            mAnimator.start();
        }
    }

    public void setDuration(long duration) {
        if (mAnimator != null) {
            mAnimator.setDuration(duration);
        }
    }

    public void setInterpolator(Interpolator value) {
        if (mAnimator != null) {
            mAnimator.setInterpolator(value);
        }
    }

    private int getCenterX() {
        int x;
        switch (positon) {
            case POSITION_BOTTOM_CENTER:
            case POSITION_CENTER:
            case POSITION_TOP_CENTER:
                x = target.getLeft() + target.getWidth() / 2;
                break;
            case POSITION_TOP_LEFT:
                x = target.getLeft();
                break;
            default:
                x = target.getLeft() + target.getWidth() / 2;
        }
        return x;
    }

    private int getCenterY() {
        int y;
        switch (positon) {
            case POSITION_BOTTOM_CENTER:
                y = target.getBottom();
                break;
            case POSITION_CENTER:
                y = target.getTop() + target.getHeight() / 2;
                break;
            case POSITION_TOP_CENTER:
            case POSITION_TOP_LEFT:
                y = target.getTop();
                break;
            default:
                y = target.getTop() + target.getHeight() / 2;
        }
        return y;
    }

    private float endRadius() {
        float r;
        switch (positon) {
            case POSITION_BOTTOM_CENTER:
            case POSITION_TOP_CENTER:
                r = Math.max(target.getWidth() / 2, target.getHeight());
                break;
            case POSITION_CENTER:
                r = Math.max(target.getWidth() / 2, target.getHeight() / 2);
                break;
            case POSITION_TOP_LEFT:
                r = (float) Math.sqrt(target.getWidth() * target.getWidth()
                        + target.getHeight() * target.getHeight());
                break;
            default:
                r = Math.max(target.getWidth() / 2, target.getHeight() / 2);
        }
        return r;
    }


}
