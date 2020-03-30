package com.example.chapter3.demo.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chapter3.demo.R;

public class LifecycleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_fragment);
    }
}
