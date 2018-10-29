package com.example.yongjie.tvdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

/**
 * @author liuyongjie create on 2018/9/17.
 */
public class MyRelativeLayout extends RelativeLayout {
    private static final String TAG = "Hello_MyRelativeLayout";

    public MyRelativeLayout(Context context) {
        super(context);
        init();
    }


    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "event:" + event);
        return super.dispatchKeyEvent(event);
    }
}
