package com.example.zhangzhongzheng.retrofit_mvp.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;

import com.example.zhangzhongzheng.retrofit_mvp.utils.MyLogger;

/**
 * Created by 龙 on 2016-10-11.
 */
public class TestMP1 implements AudioManager.OnAudioFocusChangeListener {
    private Context mContext;
    private int which_mp;

    private MediaPlayer mp;

    TestMP1(Context context, int num) {
        this.mContext = context;
        this.which_mp = num;
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        try {
            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            if (which_mp == 1) {
                mp.setDataSource(Environment.getExternalStorageDirectory() + "/" + "1.mp3");
            } else if (which_mp == 2) {
                mp.setDataSource(Environment.getExternalStorageDirectory() + "/" + "2.mp3");
            }
            mp.prepare();
        } catch (Exception e) {
            MyLogger.log("MP 错误信息：", e.getMessage());
        }
    }


    public void start() {
        if (mp != null && !mp.isPlaying()) {
            mp.start();
        }
        MyLogger.log("tag", which_mp + "play");
    }

    public void pause() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
    }

    public void release() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    public void startOrPause() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        } else if (mp != null && !mp.isPlaying()) {
            registerFoucusListener(); 
            mp.start();
        }

        MyLogger.log("tag", which_mp + "状态？" + mp.isPlaying() + "");
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // resume playback
//                if (mp == null) initMediaPlayer();
//                else start();
//                mp.setVolume(1.0f, 1.0f);
                MyLogger.log("tag", which_mp + "gain");
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                // Lost focus for an unbounded amount of time: stop playback and release media player
//                if (mp.isPlaying()) mp.stop();
//                release();
                MyLogger.log("tag", which_mp + "loss");
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // Lost focus for a short time, but we have to stop
                // playback. We don't release the media player because playback
                // is likely to resume
//                if (mp.isPlaying()) mp.pause();
                MyLogger.log("tag", which_mp + "loss_transient");
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // Lost focus for a short time, but it's ok to keep playing
                // at an attenuated level
//                if (mp.isPlaying()) mp.setVolume(0.1f, 0.1f);
                MyLogger.log("tag", which_mp + "loss_transient_can_duck");
                break;
        }
    }

    public void registerFoucusListener() {
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
    }
}
