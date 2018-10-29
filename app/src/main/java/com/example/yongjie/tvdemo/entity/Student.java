package com.example.yongjie.tvdemo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyongjie create on 2018/9/29.
 */
public class Student {

    private List<Course> courses = new ArrayList<>();

    private String name;

    public Student(String name) {
        this.name = name;
        for (int i = 0; i < 5; i++) {
            courses.add(new Course());
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "courses=" + courses +
                ", name='" + name + '\'' +
                '}';
    }
}
