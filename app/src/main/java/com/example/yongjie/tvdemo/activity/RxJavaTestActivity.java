package com.example.yongjie.tvdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yongjie.tvdemo.LogUtils;
import com.example.yongjie.tvdemo.R;
import com.example.yongjie.tvdemo.entity.Course;
import com.example.yongjie.tvdemo.entity.Student;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaTestActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);

        Student[] students = new Student[3];
        students[0] = new Student("张三");
        students[1] = new Student("李四");
        students[2] = new Student("王五");

        Observable.fromArray(students).flatMap(new Function<Student, ObservableSource<Course>>() {
            @Override
            public ObservableSource<Course> apply(Student student) throws Exception {
                LogUtils.d(TAG, "apply");
                List<Course> courses = student.getCourses();
                Course[] array = new Course[courses.size()];
                courses.toArray(array);
                return Observable.fromArray(array);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Course>() {
                    @Override
                    public void accept(Course course) throws Exception {
                        String courseName = course.getCourseName();
                        Log.d(TAG, courseName);
                    }
                });
    }
}
