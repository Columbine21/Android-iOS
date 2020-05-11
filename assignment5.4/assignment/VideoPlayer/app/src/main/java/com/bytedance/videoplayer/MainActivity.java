package com.bytedance.videoplayer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private MyVideoView videoView;
    private MediaController mController;
    private static final String TAG = "yuanziqi";
    private static boolean isHorizonal = false;
    private static int progressRecorder;
    private static boolean isPlaying;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoview);
        videoView.setConfig(isHorizonal);
        mController = new MediaController(this);

        videoView.setVideoPath(getVideoPath(R.raw.bytedance));
        videoView.setMediaController(mController);
        mController.setMediaPlayer(videoView);


        if (savedInstanceState == null)
        {
            progressRecorder = 0;
            isPlaying = false;
        }
        else
        {
            Log.d(TAG, "Recover from: " + progressRecorder);
            videoView.seekTo(progressRecorder);
            if (isPlaying)
                videoView.start();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isHorizonal = !isHorizonal;
        videoView.setConfig(isHorizonal);
        Log.d(TAG, "Configuration changes. isHorizonal: " + String.valueOf(isHorizonal));
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        progressRecorder = this.videoView.getCurrentPosition();
        isPlaying = this.videoView.isPlaying();
        Log.d(TAG, String.valueOf(progressRecorder) + String.valueOf(isPlaying));
    }

    private String getVideoPath(int resId)
    {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
}
