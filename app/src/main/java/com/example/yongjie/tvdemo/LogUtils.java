package com.example.yongjie.tvdemo;

import android.os.Process;
import android.util.Log;


/**
 * log 封装类
 *
 * @author liuyongjie create on 2018/9/14.
 */
public class LogUtils {

    public static void v(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.v(tag, createMessage(msg));
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.d(tag, createMessage(msg));
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.i(tag, createMessage(msg));
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.w(tag, createMessage(msg));
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.e(tag, createMessage(msg));
        }
    }

    public static void e(String tag, String msg, Throwable t) {
        if (BuildConfig.LOG_DEBUG) {
            Log.e(tag, createMessage(msg), t);
        }
    }


    /**
     * 根据方法栈帧的入栈规律，第4个栈帧信息就是我们需要的
     */
    private static String createMessage(String msg) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        //这一步当栈帧比较多的时候,可能比较耗时
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        builder.append("[")
                .append(" Pid:").append(Process.myPid())
                .append(" Tid:").append(Thread.currentThread().getId())
                .append(" TName:").append(Thread.currentThread().getName())
                .append(" methodName:").append(stackTrace[4].getMethodName())
                .append("()")
                .append(" methodLine:").append(stackTrace[4].getLineNumber());
        long wasteTime = System.currentTimeMillis() - startTime;
        builder.append(" Log耗时:")
                .append(wasteTime)
                .append("ms")
                .append(" ] ")
                .append(msg);
        return builder.toString();
    }

}