package com.example.yongjie.tvdemo.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyongjie create on 2018/9/18.
 * 被观察者
 */
public class Observeable {

    private List<Observer> list = new ArrayList<>();


    public void registerObserver(Observer observer){
        list.add(observer);
    }

    public void unregisterObserver(Observer observer){
        list.remove(observer);
    }

    public void update(){
        for (int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update();
        }
    }
}
