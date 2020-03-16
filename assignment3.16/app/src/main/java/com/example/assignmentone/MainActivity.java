package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Switch GenderSwitch;
    private Button LoginButton;
    private EditText UserNameEdit, PasswordEdit;
    private ProgressBar LoadingBar;
    private ImageView personalImage;
    private TextView LoadingText;
    private int progress = 0;
    Handler handle = new Handler();

    Runnable run = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + "-----run--->"
                    + Thread.currentThread().getName());
            progress = progress + 1;
            LoadingText.setText("Loading " + progress + "% ...");
            if(progress == LoadingBar.getMax()) {
                LoadingText.setVisibility(View.GONE);
                LoadingBar.setVisibility(View.GONE);
                LoginButton.setEnabled(true);
            }
            LoadingBar.setProgress(progress);
            handle.postDelayed(run, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView(){

        // Initialize UI Controls.
        Log.e("MainActivate", "initView: initialization");
        GenderSwitch = (Switch) findViewById(R.id.Gender_switch);
        GenderSwitch.setTextOff(getString(R.string.gender_boy));
        GenderSwitch.setTextOn(getString(R.string.gender_girl));
        personalImage = (ImageView) findViewById(R.id.personalImage);

        LoginButton = (Button) findViewById(R.id.LoginButton);
        UserNameEdit = (EditText) findViewById(R.id.UserNameEdit);
        PasswordEdit = (EditText) findViewById(R.id.PasswardEdit);
        LoadingBar = (ProgressBar) findViewById(R.id.progressBar);
        LoadingBar.setMax(100);
        LoadingBar.setVisibility(View.GONE);
        LoadingText = (TextView) findViewById(R.id.LoadingText);
        LoadingText.setVisibility(View.GONE);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivate", "onClick: showing processbar");
                LoadingBar.setVisibility(View.VISIBLE);
                LoadingText.setVisibility(View.VISIBLE);
                LoginButton.setEnabled(false);
                System.out.println(Thread.currentThread().getId()
                        + "-----Main--->" + Thread.currentThread().getName());
                handle.post(run);
//                LoginButton.setActivated(false);
            }
        });

        GenderSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                Log.e("MainActivate", "onCheckedChanged: change the image");
                if(isChecked){
                    personalImage.setImageResource(R.mipmap.girl);
                } else {
                    personalImage.setImageResource(R.mipmap.boy);
                }
            }
        });
    }
}
