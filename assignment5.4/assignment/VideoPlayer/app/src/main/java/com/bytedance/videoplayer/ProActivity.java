package com.bytedance.videoplayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;

public class ProActivity extends AppCompatActivity {
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

        videoView.setMediaController(mController);
        mController.setMediaPlayer(videoView);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        Log.d(TAG, uri.toString());
        videoView.setVideoURI(uri);

        if (savedInstanceState == null)
        {
            progressRecorder = 0;
            isPlaying = true;
            videoView.start();
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

}
