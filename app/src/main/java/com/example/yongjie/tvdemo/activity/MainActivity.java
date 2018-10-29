package com.example.yongjie.tvdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.yongjie.tvdemo.ImageManager;
import com.example.yongjie.tvdemo.MyView;
import com.example.yongjie.tvdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Hello_MainActivity";

    ListView listView;
    private List<String> items;
    private List<String> activitys;
    TextView textView;
    @BindView(R.id.rl_layout)
    public ViewGroup viewGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        items = getItems();
        activitys = getData();
        listView = findViewById(R.id.lv_content);
        textView = findViewById(R.id.tv_view);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.main_list_item, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "startActivity", Toast.LENGTH_LONG).show();
                startActivity(activitys.get(position));
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });

        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        ViewGroup viewById = decorView.findViewById(Window.ID_ANDROID_CONTENT);

//        int childCount = viewById.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childAt = viewById.getChildAt(i);
//            Log.d(TAG,"N");
//        }


//        new Thread(){`
//            @Override
//            public void run() {
//                super.run();
//                Glide.get(MainActivity.this).clearDiskCache();
//            }
//        }.start();
//
//        Glide.with(this).load("http://192.168.222.167:8080/chances/l.png").diskCacheStrategy(DiskCacheStrategy.RESULT).into(new SimpleTarget<GlideDrawable>() {
//            @Override
//            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                             viewGroup.setBackground(resource);
//            }
//        });

//        Glide.with(this).load("").into()

        Constructor<?>[] constructors = MyView.class.getConstructors();

        try {
            Constructor<MyView> constructor1 = MyView.class.getConstructor(Context.class);
            MyView myView = constructor1.newInstance(this);
            Log.d(TAG, "构建成功");
        } catch (Exception e) {
            Log.e(TAG, "构建失败", e);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "event:" + event);
        return super.dispatchKeyEvent(event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void startActivity(String activityName) {
        try {
            Class<? extends Activity> aClass = (Class<? extends Activity>) Class.forName(activityName);
            Intent intent = new Intent(this, aClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "start error", e);
        }
    }

    public List<String> getItems() {
        String[] stringArray = getResources().getStringArray(R.array.list_content);
        Log.d(TAG, "content:" + Arrays.toString(stringArray));
        return Arrays.asList(stringArray);
    }


    public List<String> getData() {
        String[] stringArray = getResources().getStringArray(R.array.list_activity);
        return Arrays.asList(stringArray);
    }
}
