package com.study.android.booklog;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BooklogApplication extends Application {
    private static BooklogApplication instance;
    private static Context appContext;
    private static Context activitContext;

    public static BooklogApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static Context getActivitContext() {
        return activitContext;
    }
    public void setAppContext(Context mAppContext) {
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void setActivitContext(Context activitContext) {
        this.activitContext = activitContext;
    }
}
