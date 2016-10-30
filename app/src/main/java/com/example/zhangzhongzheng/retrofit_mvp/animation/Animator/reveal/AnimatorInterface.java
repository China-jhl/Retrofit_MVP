package com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.reveal;

import android.util.Property;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by 龙 on 2016-10-30.
 * <p/>
 * 用于ObjectAnimator 产生变化的半径
 */
public interface AnimatorInterface {
    float getRadiusValue();

    /**
     * attribute that objectAnimator animated
     */
    RevealRadius radius = new RevealRadius(Float.class, "radius");

    void setRadiusValue(float value);

    /**
     * 设置target 在父容器中的rectangle
     */
    void init(RevealInfo info);

    class RevealRadius extends Property<AnimatorInterface, Float> {
        /**
         * A constructor that takes an identifying name and {@link #getType() type} for the property.
         *
         * @param type
         * @param name
         */
        public RevealRadius(Class<Float> type, String name) {
            super(type, name);
        }

        @Override
        public Float get(AnimatorInterface object) {
            return object.getRadiusValue();
        }

        @Override
        public void set(AnimatorInterface object, Float value) {
            object.setRadiusValue(value);
        }
    }

    class RevealInfo {
        public final int centerX;
        public final int centerY;
        public final float startRadius;
        public final float endRadius;
        public final WeakReference<View> target;

        public RevealInfo(int centerX, int centerY, float startRadius, float endRadius,
                          WeakReference<View> target) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.startRadius = startRadius;
            this.endRadius = endRadius;
            this.target = target;
        }

        public View getTarget() {
            return target.get();
        }

        public boolean hasTarget() {
            return getTarget() != null;
        }
    }
}
