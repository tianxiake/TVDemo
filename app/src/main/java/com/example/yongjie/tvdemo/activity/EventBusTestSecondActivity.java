package com.example.yongjie.tvdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yongjie.tvdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBusTestSecondActivity extends AppCompatActivity {


    private static final String TAG = "EventBusTestSecond";
    @BindView(R.id.tv_event_bus_test)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test_second);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true,priority = 1)
    public void handleMessage(String message) {
        Log.d(TAG, "message:" + message);
        textView.setText(message);
    }

    @Subscribe(sticky = true,priority = 3)
    public void handleMessage2(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().removeStickyEvent(String.class);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
