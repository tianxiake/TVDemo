package com.example.yongjie.tvdemo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.yongjie.tvdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTestMainActivity extends AppCompatActivity {

    private static final String TAG = "EventBusTest";
    @BindView(R.id.btn_message_one)
    Button btnMessageOne;
    @BindView(R.id.btn_message_two)
    Button btnMessageTwo;
    @BindView(R.id.btn_cancel_message_one)
    Button btnCancelMessageOne;
    @BindView(R.id.btn_cancel_message_two)
    Button btnCancelMessageTwo;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void handleMessageOne(MessageOne messageOne) {
        Log.d(TAG, "handleMessageOne");
    }

    @Subscribe
    public void handleMessageTwo(MessageTwo messageTwo) {
        Log.d(TAG, "handleMessageTwo");
    }


    @OnClick(R.id.btn_message_one)
    public void oneOnClick() {
        Log.d(TAG, "oneOnClick");
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 10000; i++) {
                    try {
                        EventBus.getDefault().post(new MessageOne("MessageOne"));
                        Thread.sleep(500);
                    } catch (Exception e) {
                        Log.e(TAG, "", e);
                    }
                }
            }
        }.start();
    }

    @OnClick(R.id.btn_message_two)
    public void twoOnClick() {
        Log.d(TAG, "twoOnClick");
        EventBus.getDefault().post(new MessageTwo("MessageTwo"));
    }

    @OnClick(R.id.btn_cancel_message_one)
    public void cancelMessageOne() {
        EventBus.getDefault().cancelEventDelivery(new MessageOne("HELLO"));
    }

    @OnClick(R.id.btn_cancel_message_two)
    public void cancelMessageTwo() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    class MessageOne {
        String message;

        public MessageOne(String message) {
            this.message = message;
        }
    }

    class MessageTwo {
        String message;

        public MessageTwo(String message) {
            this.message = message;
        }
    }
}
