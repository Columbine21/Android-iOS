package com.bytedance.videoplayer;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.VideoView;

public class MyVideoView extends VideoView {

    private boolean isHorizonal = false;

    public void setConfig(boolean isHorizonal) {
        this.isHorizonal = isHorizonal;
    }

    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isHorizonal) {
            int width=getDefaultSize(0,widthMeasureSpec);
            int height=getDefaultSize(0,heightMeasureSpec);
            setMeasuredDimension(width,height);
        }
        else {
            int width = getDefaultSize(0, widthMeasureSpec);
            setMeasuredDimension(width, 600);
        }
    }
}
