package com.example.yongjie.tvdemo.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yongjie.tvdemo.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Test2Activity extends AppCompatActivity {

    private static final String TAG = "Test2Activity";
    @BindView(R.id.rv_content)
    RecyclerView recyclerView;

    @BindView(R.id.tv_title)
    TextView textView;

    @BindView(R.id.tv_title_2)
    TextView textView2;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 5;
            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
//                outRect.bottom = 40;
                outRect.right = 6;
            }
        });
        myAdapter = new MyAdapter();
        myAdapter.setData(50);
        recyclerView.setAdapter(myAdapter);
        textView.setFocusable(true);
        textView.setOnFocusChangeListener((view, b) -> {
            if (b) {
                myAdapter.setData(new Random().nextInt(100));
                myAdapter.notifyDataSetChanged();
                Toast.makeText(Test2Activity.this, "DataChange", Toast.LENGTH_SHORT).show();
                textView.setBackgroundColor(Color.RED);
            } else {
                textView.setBackgroundColor(Color.parseColor("#00FF00"));
            }
        });
        textView.setOnFocusChangeListener((view,b)->{});

        textView2.setFocusable(true);
        textView2.setOnFocusChangeListener((view, b) -> {
            if (b) {
                textView2.setBackgroundColor(Color.RED);
            } else {
                textView2.setBackgroundColor(Color.parseColor("#00FF00"));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

        private int size;

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.test2_item, parent, false);
            inflate.setFocusable(true);
            return new VH(inflate);
        }

        public void setData(int size) {
            this.size = size;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
//                        int[] cordi = new int[2];
//                        view.getLocationInWindow(cordi);
//                        Log.d(TAG, "x:" + cordi[0] + ",y:" + cordi[1]);
//                        Rect rect = new Rect();
//                        view.getFocusedRect(rect);
//                        Log.d(TAG, rect.toShortString());
                        int top = view.getTop();
                        int left = view.getLeft();
                        Log.d(TAG, "top:" + top + ",left:" + left);


                        view.setBackgroundColor(Color.RED);
                    } else {
                        view.setBackgroundColor(Color.WHITE);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return size;
        }

        class VH extends RecyclerView.ViewHolder {


            public VH(View itemView) {
                super(itemView);
            }
        }
    }
}
