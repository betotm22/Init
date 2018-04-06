package com.sye.base.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sye.base.MyApplication;

public class Pref {

    private static final String NAME = "SHARED_PREFERENCES";

    private static SharedPreferences getShared(Context context) {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor (Context context) {
        return getShared(context).edit();
    }

    private static Context context() {
        return MyApplication.app();
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = getEditor(context());
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor(context());
        editor.putString(key, value);
        editor.commit();
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor(context());
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getSharedInt(String key) {
        return getShared(context()).getInt(key, -1);
    }

    public static String getSharedString(String key) {
        return getShared(context()).getString(key, "");
    }

    public static boolean getSharedBoolean(String key, boolean def) {
        return getShared(context()).getBoolean(key, def);
    }
}
