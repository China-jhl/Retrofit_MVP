package com.example.zhangzhongzheng.retrofit_mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhangzhongzheng.retrofit_mvp.animation.Animator.BezierAnimator;
import com.example.zhangzhongzheng.retrofit_mvp.animation.BezierActivity;
import com.example.zhangzhongzheng.retrofit_mvp.appbarlayout.Activity_APL;
import com.example.zhangzhongzheng.retrofit_mvp.media.TestMediaPActivity;
import com.example.zhangzhongzheng.retrofit_mvp.media.player.PlayerActivity;
import com.example.zhangzhongzheng.retrofit_mvp.tabhost.TabHostActivity;
import com.example.zhangzhongzheng.retrofit_mvp.widget.RecyclerRefreshLayout;

import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;

public class MainActivity extends AppCompatActivity implements RecyclerRefreshLayout.SuperRefreshLayoutListener {
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private RecyclerRefreshLayout refresh;

    private Button add;
    private Button remove;

    //一些列测试button  开启测试activity
    private Button bt1;
    private Button bt2;
    private Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, RecyclerViewAdapter.ONLY_FOOTER);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refresh = (RecyclerRefreshLayout) findViewById(R.id.refresh);
        refresh.setSuperRefreshLayoutListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(recyclerView));

        
        View footer = View.inflate(this, R.layout.layout_list_view_footer, null);

        add = (Button) findViewById(R.id.bt_add);
        remove = (Button) findViewById(R.id.bt_remove);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                adapter.addItem();
                Intent intent = new Intent(MainActivity.this, BezierActivity.class);
                startActivity(intent);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removeItem();
            }
        });

        initButtons();//测试button
    }

    private void initButtons() {
        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_APL.class);
                startActivity(intent);
            }
        });

        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestMediaPActivity.class);
                startActivity(intent);
            }
        });

        bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefreshing() {
        Toast.makeText(this, "下拉刷新", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(this, "上拉加载", Toast.LENGTH_SHORT).show();
    }
}
