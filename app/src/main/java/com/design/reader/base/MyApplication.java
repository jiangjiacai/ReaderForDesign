package com.design.reader.base;

import android.content.Context;
import android.os.StrictMode;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;


public class MyApplication extends LitePalApplication {

    private static Context context;
    private static MyApplication instance;

    public static final String MOBAppKey = "1c700ab6fa2ba";

    public static final String MOBAppSecrete = "d70bea18541e8b952d8bec250ec83b18";

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        LitePal.getDatabase();
        context = getApplicationContext();
        instance = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    public static Context getContext() {
        return context;
    }

}
