package com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.reveal;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by 龙 on 2016-10-30.
 */
public class ViewAnimationUtilsCompat {
    /**
     * @param target      The View will be clipped to the animating circle.
     * @param centerX     The x coordinate of the center of the animating circle, relative to
     *                    <code>view</code>.
     * @param centerY     The y coordinate of the center of the animating circle, relative to
     *                    <code>view</code>.
     * @param startRadius The starting radius of the animating circle.
     * @param endRadius   The ending radius of the animating circle.
     * @return Animator
     */
    public static Animator createCircularReveal(View target,
                                                int centerX, int centerY,
                                                float startRadius, float endRadius) {
        if (!(target.getParent() instanceof RevealFrameLayout)) {
            throw new IllegalArgumentException("The target view must be a child of RevealFrameLayout");
        }

        if (((RevealFrameLayout) target.getParent()).getChildCount() > 1) {
            throw new IllegalArgumentException("RevealFrameLayout must has one child");
        }

        AnimatorInterface animatorInterface = (AnimatorInterface) target.getParent();
        //animatorInterface的设置
        animatorInterface.init(new AnimatorInterface.RevealInfo(centerX, centerY,
                startRadius, endRadius, new WeakReference<>(target)));
        ObjectAnimator animator = ObjectAnimator.ofFloat(animatorInterface,
                AnimatorInterface.radius, startRadius, endRadius);
        animator.addListener((Animator.AnimatorListener) target.getParent());

        return animator;
    }
}
