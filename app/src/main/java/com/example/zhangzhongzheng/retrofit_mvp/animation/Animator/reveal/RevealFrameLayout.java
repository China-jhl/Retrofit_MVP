package com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.reveal;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by 龙 on 2016-10-30.
 * 用于包裹target view
 * 在draw child 阶段绘制Circle
 */
public class RevealFrameLayout extends FrameLayout implements AnimatorInterface, Animator.AnimatorListener {
    /**
     * targetView 区域
     */
    private Rect mTargetBounds = new Rect();
    /**
     * 当前动画显示的圆的半径
     */
    private float mRadius;
    /**
     * 包括中心点，target view，开始半径，结束半径等信息
     */
    private RevealInfo info;
    private Path mRevealPath;
    /**
     * 动画是否正在播放
     */
    private boolean isRunning = false;

    public RevealFrameLayout(Context context) {
        super(context);
        mRevealPath = new Path();
    }

    public RevealFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRevealPath = new Path();
    }

    public RevealFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRevealPath = new Path();
    }


    @Override
    public float getRadiusValue() {
        return mRadius;
    }


    @Override
    public void setRadiusValue(float value) {
        mRadius = value;
        invalidate(mTargetBounds);
    }

    @Override
    public void init(RevealInfo info) {
        info.getTarget().getHitRect(mTargetBounds);
        this.info = info;
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (isRunning && child == info.getTarget()) {
            final int state = canvas.save();

            mRevealPath.reset();
            mRevealPath.addCircle(info.centerX, info.centerY, mRadius, Path.Direction.CW);

            canvas.clipPath(mRevealPath);

            boolean isInvalided = super.drawChild(canvas, child, drawingTime);

            canvas.restoreToCount(state);

            return isInvalided;
        }

        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    public void onAnimationStart(Animator animation) {
        isRunning = true;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        isRunning = false;
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        isRunning = false;
    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
