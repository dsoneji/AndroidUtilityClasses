package com.android.practice.library;

import android.app.Application;

public class CoreApp extends Application {

    private static CoreApp instance;

    public static synchronized CoreApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}