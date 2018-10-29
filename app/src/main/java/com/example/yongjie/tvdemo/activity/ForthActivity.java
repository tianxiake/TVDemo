package com.example.yongjie.tvdemo.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yongjie.tvdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForthActivity extends AppCompatActivity {

    private static final String TAG = "ForthActivity";
    @BindView(R.id.vp_content)
    ViewPager viewPager;


    private List<RecyclerView> views = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        colors.add(Color.parseColor("#FF0000"));
        colors.add(Color.parseColor("#00FF00"));
        colors.add(Color.parseColor("#0000FF"));
        colors.add(Color.parseColor("#FF0000"));
        colors.add(Color.parseColor("#00FF00"));
        colors.add(Color.parseColor("#0000FF"));
        for (int i = 0; i < 6; i++) {
            RecyclerView recyclerView = new RecyclerView(this);
            recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6, LinearLayoutManager.HORIZONTAL, false);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 6;
                }
            });
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new MyRecyclerViewAdapter());
            views.add(recyclerView);
        }

        ButterKnife.bind(this);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MyPageAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled:" + position);
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setBackgroundColor(colors.get(position));
                Log.d(TAG, "onPageSelected:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged:" + state);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        if (action == KeyEvent.ACTION_DOWN) {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                int position = viewPager.getCurrentItem();
                RecyclerView recyclerView = views.get(position);
                int itemCount = recyclerView.getAdapter().getItemCount();
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastCompletelyVisibleItemPosition == itemCount - 1) {
                    Log.d(TAG, "到达页面的最后一页了");
                } else {
                    recyclerView.smoothScrollBy(recyclerView.getWidth(), 0);
                    Log.d(TAG, "右键事件拦截");
                    return true;
                }
            }

            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                int position = viewPager.getCurrentItem();
                RecyclerView recyclerView = views.get(position);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                if (firstCompletelyVisibleItemPosition == 0) {
                    Log.d(TAG, "到达页面的首页了");
                } else {

                    recyclerView.smoothScrollBy(-recyclerView.getWidth(), 0);
                    Log.d(TAG, "左键事件拦截");
                    return true;
                }
            }
        }
        return super.dispatchKeyEvent(event);

    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.VH> {


        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_item, parent, false);
            return new VH(inflate);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class VH extends RecyclerView.ViewHolder {

            public VH(View itemView) {
                super(itemView);
            }
        }
    }

    class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            RecyclerView recyclerView = views.get(position);
            container.addView(recyclerView);
            return recyclerView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
