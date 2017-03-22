package com.design.reader.base;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {

    private static Context context;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;
    }

    public static Context getContext() {
        return context;
    }

}
