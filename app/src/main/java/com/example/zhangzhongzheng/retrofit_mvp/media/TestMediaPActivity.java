package com.example.zhangzhongzheng.retrofit_mvp.media;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zhangzhongzheng.retrofit_mvp.R;

/**
 * Created by 龙 on 2016-10-11.
 */
public class TestMediaPActivity extends AppCompatActivity implements View.OnClickListener {
    private Button play1;
    private Button stop1;
    private Button play2;
    private Button stop2;

    private TestMP1 mp1;
    private TestMP1 mp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mediaplayer);
        initMediaPlayer();
        initButtons();
    }

    private void initMediaPlayer() {
        mp1 = new TestMP1(this, 1);
        mp2 = new TestMP1(this, 2);
    }

    private void initButtons() {
        play1 = (Button) findViewById(R.id.bt_play1);
        play1.setOnClickListener(this);
        stop1 = (Button) findViewById(R.id.bt_stop1);
        stop1.setOnClickListener(this);

        play2 = (Button) findViewById(R.id.bt_play2);
        play2.setOnClickListener(this);
        stop2 = (Button) findViewById(R.id.bt_stop2);
        stop2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_play1:
                mp1.startOrPause();
                break;
            case R.id.bt_play2:
                mp2.startOrPause();
                break;
            case R.id.bt_stop1:
                //   mp1.pause();
                break;
            case R.id.bt_stop2:
                //  mp2.pause();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp1.release();
        mp2.release();
    }
}
