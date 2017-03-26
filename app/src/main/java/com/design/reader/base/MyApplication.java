package com.design.reader.base;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {

    private static Context context;
    private static MyApplication instance;

    public static final String MOBAppKey = "1c700ab6fa2ba";

    public static final String MOBAppSecrete = "d70bea18541e8b952d8bec250ec83b18";

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
