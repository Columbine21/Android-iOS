package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    private Switch GenderSwitch;
    private Button LoginButton;
    private EditText UserNameEdit, PasswordEdit;
    private ProgressBar LoadingBar;
    private ImageView personalImage;

    Handler handle = new Handler();

    Runnable run = new Runnable() {
        @Override
        public void run() {
            int progress = 0;
            System.out.println(Thread.currentThread().getId() + "-----run--->"
                    + Thread.currentThread().getName());
            progress = progress + 1;
            LoadingBar.setProgress(progress);
            handle.postDelayed(run, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize our layout.
        setContentView(R.layout.activity_main);
        initView();


//        GenderSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
//                if(isChecked){
//                    personalImage.setImageResource(R.mipmap.girl);
////                    ViewGroup.MarginLayoutParams marginSetting = new ViewGroup.MarginLayoutParams(personalImage.getLayoutParams());
////                    marginSetting.height = 255;
////                    marginSetting.width = 255;
////                    personalImage.setLayoutParams(marginSetting);
////                    MarginLayoutParams margin9 = new MarginLayoutParams(
////                            image.getLayoutParams());
////                    margin9.setMargins(400, 10, 0, 0);//在左边距400像素，顶边距10像素的位置显示图片
////                    RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(margin9);
////                    layoutParams9.height = 600;//设置图片的高度
////                    layoutParams9.width = 800; //设置图片的宽度
////                    image.setLayoutParams(layoutParams9);
//                } else {
//                    personalImage.setImageResource(R.mipmap.boy);
////                    ViewGroup.MarginLayoutParams marginSetting = new ViewGroup.MarginLayoutParams(personalImage.getLayoutParams());
////                    marginSetting.height = 255;
////                    marginSetting.width = 255;
////                    personalImage.setLayoutParams(marginSetting);
//                }
//            }
//        });
    }
    private void initView(){

        // Initialize UI Controls.

        GenderSwitch = (Switch) findViewById(R.id.Gender_switch);
        GenderSwitch.setTextOff(getString(R.string.gender_boy));
        GenderSwitch.setTextOn(getString(R.string.gender_girl));
        personalImage = (ImageView) findViewById(R.id.personalImage);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        UserNameEdit = (EditText) findViewById(R.id.UserNameEdit);
        PasswordEdit = (EditText) findViewById(R.id.PasswardEdit);
        LoadingBar = (ProgressBar) findViewById(R.id.progressBar);
        LoadingBar.setVisibility(View.GONE);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingBar.setVisibility(View.VISIBLE);
                System.out.println(Thread.currentThread().getId()
                        + "-----Main--->" + Thread.currentThread().getName());
                handle.post(run);
            }
        });
    }
}
