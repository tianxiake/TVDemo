package com.example.yongjie.tvdemo.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yongjie.tvdemo.MessageEvent;
import com.example.yongjie.tvdemo.R;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new MessageEvent("EventBus3.1.1"));
                finish();
            }
        }, 2000);
    }
}
