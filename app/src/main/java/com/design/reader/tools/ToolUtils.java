package com.design.reader.tools;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.design.reader.base.MyApplication;

public class ToolUtils {

    private static volatile ToolUtils instance;

    private ToolUtils() {

    }

    public static ToolUtils getInstance() {
        if (instance == null) {
            synchronized (ToolUtils.class) {
                if (instance == null) {
                    instance = new ToolUtils();
                }
            }
        }
        return instance;
    }

    public static String getVersionName() {
        PackageManager packageManager = MyApplication.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

}
