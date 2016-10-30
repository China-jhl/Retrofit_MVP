package com.example.zhangzhongzheng.retrofit_mvp.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.zhangzhongzheng.retrofit_mvp.R;
import com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.BezierAnimator;
import com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.BezierAnimator.Point;

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
        BezierAnimator animator = new BezierAnimator(iv, new Point(0, 0), new Point(800, 70),
                new Point(100, 140), new Point(600, 700));
        // animator.setRepeatCount(10);
        animator.setDuration(1500);
        animator.start();
    }
}
