package com.example.zhangzhongzheng.retrofit_mvp.media.player;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zhangzhongzheng.retrofit_mvp.R;
import com.example.zhangzhongzheng.retrofit_mvp.utils.MyLogger;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

/**
 * Created by zhangzhongzheng on 2016/10/15.
 */

public class PlayerActivity extends AppCompatActivity implements ExoPlayer.EventListener {
    private SimpleExoPlayerView playerView;

    private JPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        playerView = (SimpleExoPlayerView) findViewById(R.id.player_view);

        player = new JPlayer.Builder(this)
                .addListener(this)
                .SourceUri(Uri.parse("http://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv"))
                .shouldAutoPlay(true)
                .build(playerView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {
        MyLogger.log("player", "manifest");
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
}
