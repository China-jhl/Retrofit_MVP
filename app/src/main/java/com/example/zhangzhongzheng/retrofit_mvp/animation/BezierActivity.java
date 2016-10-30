package com.example.zhangzhongzheng.retrofit_mvp.animation;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.zhangzhongzheng.retrofit_mvp.R;
import com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.BezierAnimator;
import com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.BezierAnimator.Point;
import com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.RevealAnimator;

/**
 * Created by 龙 on 2016-10-28.
 */
public class BezierActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        iv = (ImageView) findViewById(R.id.iv);
//        BezierAnimator animator = new BezierAnimator(iv, new Point(0, 0), new Point(800, 70),
//                new Point(100, 140), new Point(600, 700));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    iv.removeOnLayoutChangeListener(this);
                    RevealAnimator animator = new RevealAnimator(iv, RevealAnimator.POSITION_TOP_LEFT);
                    // animator.setRepeatCount(10);
                    animator.setDuration(1500);
                    animator.start();
                }
            });
        }

    }
}
