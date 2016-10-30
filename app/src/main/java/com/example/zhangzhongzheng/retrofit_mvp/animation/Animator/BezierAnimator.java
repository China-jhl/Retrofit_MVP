package com.example.zhangzhongzheng.retrofit_mvp.animation.Animator;

import android.animation.IntEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.zhangzhongzheng.retrofit_mvp.utils.MyLogger;

/**
 * Created by 龙 on 2016-10-28.
 * <p/>
 * bezier 曲线动画
 */
public class BezierAnimator implements ValueAnimator.AnimatorUpdateListener {
    /**
     * 执行动画的view
     */
    private View view;
    /**
     * 用插值器映射到的fraction 求出实际需求值。
     */
    private TypeEvaluator<Point> mTypeEvaluator;
    /**
     * 记录贝塞尔曲线的四个点（简单的贝塞尔曲线，没有实现设置多个点的，那个公式有点复杂）
     */
    private Point[] points = new Point[4];
    /**
     * 组合形式，实现BezierAnimator 通过 ValueAnimator;
     */
    private ValueAnimator valueAnimator;

    public BezierAnimator(View view, Point p0, Point p1, Point p2, Point p3) {
        if (view == null) {
            throw new RuntimeException("view can not be null");
        }
        this.view = view;
        points[0] = p0;
        points[1] = p1;
        points[2] = p2;
        points[3] = p3;

        this.mTypeEvaluator = createTypeEvaluator();
        this.valueAnimator = ValueAnimator.ofObject(mTypeEvaluator, p0, p3);
        //参数为null也是设置线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(mTypeEvaluator);
        valueAnimator.addUpdateListener(this);
        valueAnimator.setTarget(view);
    }

    private TypeEvaluator<Point> createTypeEvaluator() {
        return new TypeEvaluator<Point>() {
            @Override
            public Point evaluate(float fraction, Point startValue, Point endValue) {
                float x = points[0].getX() * (1 - fraction) * (1 - fraction) * (1 - fraction)
                        + 3 * points[1].getX() * fraction * (1 - fraction) * (1 - fraction)
                        + 3 * points[2].getX() * fraction * fraction * (1 - fraction) +
                        points[3].getX() * fraction * fraction * fraction;
                float y = points[0].getY() * (1 - fraction) * (1 - fraction) * (1 - fraction)
                        + 3 * points[1].getY() * fraction * (1 - fraction) * (1 - fraction)
                        + 3 * points[2].getY() * fraction * fraction * (1 - fraction) +
                        points[3].getY() * fraction * fraction * fraction;
                return new Point(x, y);
            }
        };
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Point result = (Point) animation.getAnimatedValue();
        view.setX(result.getX());
        view.setY(result.getY());
        MyLogger.log("info", "x:" + result.getX() + "  y:" + result.getY());
    }

    public void start() {
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    public void setRepeatCount(int count) {
        if (valueAnimator != null) {
            valueAnimator.setRepeatCount(count);
        }
    }

    public void setDuration(long time) {
        if (valueAnimator != null) {
            valueAnimator.setDuration(time);
        }
    }

    /**
     * 点
     */
    public static class Point {
        private float x;
        private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }


}
