package com.design.reader.tools;


import android.content.SharedPreferences;

import com.design.reader.base.MyApplication;

public class SharedPreferenceUtils {

    private static volatile SharedPreferenceUtils instance;
    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;
    public static final String SHARED_PHONE = "shared_phone";
    public static final String SHARED_PASSWORD = "shared_password";

    public static final String AUTO_LOGIN = "auto_login";

    private SharedPreferenceUtils() {
        sharedPreferences = MyApplication.getContext().getSharedPreferences("readerForDesign", 0);
        editor = sharedPreferences.edit();
    }

    public static SharedPreferenceUtils getInstance() {
        if (instance == null) {
            synchronized (SharedPreferenceUtils.class) {
                if (instance == null) {
                    instance = new SharedPreferenceUtils();
                }
            }
        }
        return instance;
    }

    public SharedPreferenceUtils putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferenceUtils putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferenceUtils putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
        return this;
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

}
