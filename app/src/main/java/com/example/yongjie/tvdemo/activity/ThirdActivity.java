package com.example.yongjie.tvdemo.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yongjie.tvdemo.LogUtils;
import com.example.yongjie.tvdemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {


    private static final String TAG = "ThirdActivity";
    private ViewPager viewPager;
    private List<ImageView> list = new ArrayList<>();

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        viewPager = findViewById(R.id.viewPager);


        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            imageView.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.mipmap.ic_launcher);
            list.add(imageView);
        }

        viewPager.setAdapter(new MyPageAdapater());
        //缓存页数
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d(TAG, "onPageScrolled() position:" + i);
            }

            @Override
            public void onPageSelected(int i) {
                Log.d(TAG, "onPageSelected() position:" + i);
//                Toast.makeText(ThirdActivity.this, "选中了第" + i + "个", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d(TAG, "onPageScrollStateChanged() state:" + i);
            }
        });

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                viewPager.setCurrentItem(0, true);
//            }
//        }, 2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d(TAG, "MotionEvent:" + ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        LogUtils.d(TAG, "KeyEvent:" + event);
        return super.dispatchKeyEvent(event);
    }


    private class MyPageAdapater extends PagerAdapter implements View.OnFocusChangeListener {

        public Drawable background;

        @Override
        public void onFocusChange(View view, boolean b) {
            if (b) {
                int[] location = new int[2];
                view.getLocationInWindow(location);
                Log.d(TAG, "location:" + Arrays.toString(location));
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            ImageView imageView = list.get(position);
//            container.addView(list.get(position));
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_scorll, null, false);
            View one = view.findViewById(R.id.v_one);
            one.setOnFocusChangeListener(this);
            View two = view.findViewById(R.id.v_two);
            two.setOnFocusChangeListener(this);
            View three = view.findViewById(R.id.v_three);
            three.setOnFocusChangeListener(this);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            ImageView imageView = list.get(position);
//            container.removeView(imageView);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
