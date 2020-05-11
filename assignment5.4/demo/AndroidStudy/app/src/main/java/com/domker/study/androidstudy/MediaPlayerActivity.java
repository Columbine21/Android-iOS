package com.domker.study.androidstudy;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private MediaPlayer player;
    private SurfaceHolder holder;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MediaPlayer");

        setContentView(R.layout.layout_media_player);
        surfaceView = findViewById(R.id.surfaceView);
        player = new MediaPlayer();
        try {
            player.setDataSource(getResources().openRawResourceFd(R.raw.bytedance));
            holder = surfaceView.getHolder();
            holder.addCallback(new PlayerCallBack());
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 自动播放
//                    player.start();
                    player.setLooping(true);
                }
            });
            player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    changeVideoSize(mp);
                }
            });
            player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    System.out.println(percent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.buttonPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
            }
        });

        findViewById(R.id.buttonPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });
    }

    public void changeVideoSize(MediaPlayer mediaPlayer) {
        int surfaceWidth = surfaceView.getWidth();
        int surfaceHeight = surfaceView.getHeight();

        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        float max;
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            max = Math.max((float) videoWidth / (float) surfaceWidth, (float) videoHeight / (float) surfaceHeight);
        } else {
            //横屏模式下按视频高度计算放大倍数值
            max = Math.max(((float) videoWidth / (float) surfaceHeight), (float) videoHeight / (float) surfaceWidth);
        }

        //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
        videoWidth = (int) Math.ceil((float) videoWidth / max);
        videoHeight = (int) Math.ceil((float) videoHeight / max);

        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        surfaceView.setLayoutParams(new LinearLayout.LayoutParams(videoWidth, videoHeight));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    private class PlayerCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
