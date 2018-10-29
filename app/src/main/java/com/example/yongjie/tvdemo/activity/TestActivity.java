package com.example.yongjie.tvdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yongjie.tvdemo.LogUtils;
import com.example.yongjie.tvdemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        try {
            JSONObject[] jsonObjects = new JSONObject[10];
            for (int i = 0; i < 10; i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("dataLink", "HELLO");
                jsonObjects[i] = jsonObject;
            }

            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    LogUtils.d(TAG, "emitter");
                    emitter.onNext("HELLO");
                }
            }).observeOn(Schedulers.io()).doOnNext(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    LogUtils.d(TAG, "doOnNext()");
                }
            }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    LogUtils.d(TAG, "accept");
                }
            });
//
//            Observable.fromArray(jsonObjects).observeOn(Schedulers.io()).map(new Function<JSONObject, String>() {
//                @Override
//                public String apply(JSONObject jsonObject) throws Exception {
//                    LogUtils.d(TAG, "JSONObject -> String");
//                    return jsonObject.optString("dataLink");
//                }
//            }).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<String, ObservableSource<String>>() {
//                @Override
//                public ObservableSource<String> apply(String s) throws Exception {
//                    LogUtils.d(TAG, "String -> String");
//                    return Observable.just(s + "WORLD");
//                }
//            }).observeOn(Schedulers.io()).subscribe(new Consumer<String>() {
//                @Override
//                public void accept(String s) throws Exception {
//                    LogUtils.d(TAG, "消费:" + s);
//                }
//            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
