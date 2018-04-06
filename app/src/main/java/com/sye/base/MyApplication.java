package com.sye.base;

import android.app.Application;

import com.sye.base.util.Pref;
import com.sye.base.util.Set;

public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static MyApplication app() {
        return application;
    }

    public static void auth(String auth) {
        Pref.putString(Set.PREF_AUTH, auth);
    }

    public static String auth() {
        return Pref.getSharedString(Set.PREF_AUTH);
    }
}
