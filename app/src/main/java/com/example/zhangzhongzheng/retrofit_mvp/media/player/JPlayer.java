package com.example.zhangzhongzheng.retrofit_mvp.media.player;


import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

/**
 * Created by zhangzhongzheng on 2016/10/15.
 */

public class JPlayer {

    private final String DEBUG_TAG = "JPlayer";

    private Builder builder;
    private SimpleExoPlayer player;

    public JPlayer(Builder builder) {
        this.builder = builder;
        this.player = builder.player;
    }

    public SimpleExoPlayer getPlayer() {
        return player;
    }


    public String getPlayerStateString() {
        String text = "";
        switch (player.getPlaybackState()) {
            case ExoPlayer.STATE_BUFFERING:
                text += "buffering";
                break;
            case ExoPlayer.STATE_ENDED:
                text += "ended";
                break;
            case ExoPlayer.STATE_IDLE:
                text += "idle";
                break;
            case ExoPlayer.STATE_READY:
                text += "ready";
                break;
            default:
                text += "unknown";
                break;
        }
        return text;
    }

    public void release() {
        try {
            builder.player.release();
            builder.player = null;
            player = null;
            builder.trackSelector = null;
        } catch (Exception e) {
            Log.i(DEBUG_TAG, "资源释放错误");
        }
    }


    public static final class Builder {
        Context mContext;
        Handler mainHandler;
        private Uri uri;
        static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

        TrackSelection.Factory videoTrackSelectionFactory;
        /**
         * Selects tracks to be consumed by available renderers.
         * <p>
         * Base class for TrackSelectors that first establish
         * a mapping between TrackGroups and renderers, and then from that mapping create a TrackSelection for each renderer.
         * <p/>
         * <p>
         * A MappingTrackSelector that allows configuration of common parameters
         */
        TrackSelector trackSelector;

        LoadControl loadControl;
        /**
         * ExoPlayerFactory 生产实例
         */
        SimpleExoPlayer player;
        /**
         * A DataSource.Factory that produces DefaultDataSource instances
         * that delegate to DefaultHttpDataSources for non-file/asset/content URIs.
         * <p>
         * DataSource:A component from which streams of data can be read.
         * </p>
         */
        DataSource.Factory dataSourceFactory;
        /**
         * 产生Extractor(提取器)的实例
         * <p>
         * DefaultExtractorsFactory:
         * An ExtractorsFactory that provides an array of extractors for the following formats:
         * MP4, including M4A (Mp4Extractor)
         * fMP4 (FragmentedMp4Extractor)
         * Matroska and WebM (MatroskaExtractor)
         * Ogg Vorbis/FLAC (OggExtractor
         * MP3 (Mp3Extractor)
         * AAC (AdtsExtractor)
         * MPEG TS (TsExtractor)
         * MPEG PS (PsExtractor)
         * FLV (FlvExtractor)
         * WAV (WavExtractor)
         * FLAC (only available if the FLAC extension is built and included)
         * <p/>
         */
        ExtractorsFactory extractorsFactory;
        /**
         * <p>
         * ExtractorMediaSource:
         * Provide one period that loads data from uri and extracted using extractor
         * 其中，loads data是DataSource的工作；extract 是extractor的工作。
         * <p/>
         */
        MediaSource mediaSource;

        //一些列listener
        ExoPlayer.EventListener eventListener;
        AudioRendererEventListener audioListener;
        VideoRendererEventListener videoListener;
        //
        private boolean shouldAutoPlay = false;


        public Builder(Context mContext) {
            this.mContext = mContext;
            this.mainHandler = new Handler(mContext.getMainLooper());
            videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(BANDWIDTH_METER);
            trackSelector = new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
            loadControl = new DefaultLoadControl();
            setPlayer();

            dataSourceFactory = buildDataSourceFactory(BANDWIDTH_METER);
            extractorsFactory = new DefaultExtractorsFactory();
        }

        DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
            return new DefaultDataSourceFactory(mContext, bandwidthMeter,
                    buildHttpDataSourceFactory(bandwidthMeter));
        }

        HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
            return new DefaultHttpDataSourceFactory(Util.getUserAgent(mContext, "Retrofit_MVP"), bandwidthMeter);
        }

        /**
         * 使用定制的DataSourceFactory
         *
         * @param dataSourceFactory
         * @return
         */
        public Builder dataSourceFactory(DataSource.Factory dataSourceFactory) {
            this.dataSourceFactory = dataSourceFactory;
            return this;
        }

        /**
         * 使用定制的trackSelector
         *
         * @param trackSelector
         * @return
         */
        public Builder trackSelector(TrackSelector trackSelector) {
            this.trackSelector = trackSelector;
            setPlayer();//更新Player
            return this;
        }

        //这里肯定可以扩展，ExoPlayerFactory实例化ExoPlayer有好几种方法
        private void setPlayer() {
            player = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, loadControl);
        }

        public Builder SourceUri(Uri uri) {
            this.uri = uri;
            this.mediaSource = buildMediaSource(uri, "");
            return this;
        }

        private MediaSource buildMediaSource(Uri uri, String overrideExtension) {
            int type = Util.inferContentType(!TextUtils.isEmpty(overrideExtension) ? "." + overrideExtension
                    : uri.getLastPathSegment());
            switch (type) {
                case C.TYPE_SS:
                    return new SsMediaSource(uri, dataSourceFactory,
                            new DefaultSsChunkSource.Factory(dataSourceFactory), mainHandler, null);
                case C.TYPE_DASH:
                    return new DashMediaSource(uri, dataSourceFactory,
                            new DefaultDashChunkSource.Factory(dataSourceFactory), mainHandler, null);
                case C.TYPE_HLS:
                    return new HlsMediaSource(uri, dataSourceFactory, mainHandler, null);
                case C.TYPE_OTHER:
                    return new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory,
                            mainHandler, null);
                default: {
                    throw new IllegalStateException("Unsupported type: " + type);
                }
            }
        }

        public Builder addListener(ExoPlayer.EventListener listener) {
            this.eventListener = listener;
            if (player != null) {
                player.addListener(eventListener);
            }
            return this;
        }

        public Builder setAudioDebugListener(AudioRendererEventListener listener) {
            this.audioListener = listener;
            if (player != null) {
                player.setAudioDebugListener(audioListener);
            }
            return this;
        }

        public Builder setVideoDebugListener(VideoRendererEventListener listener) {
            this.videoListener = listener;
            if (player != null) {
                player.setVideoDebugListener(videoListener);
            }
            return this;
        }

        public Builder shouldAutoPlay(boolean auto) {
            this.shouldAutoPlay = auto;
            return this;
        }

        /**
         * 使用SimpleExoPlayerView关联player的build方法
         *
         * @param playerView
         * @return
         */
        public JPlayer build(SimpleExoPlayerView playerView) {
            playerView.setPlayer(player);
            player.prepare(mediaSource);
            player.setPlayWhenReady(shouldAutoPlay);
            return new JPlayer(this);
        }

    }

}
