package com.sye.base.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {

    private static final String NAME = "SHARED_PREFERENCES";

    private static SharedPreferences getShared(Context context) {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor (Context context) {
        return getShared(context).edit();
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        editor.commit();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getSharedInt(Context context, String key) {
        return getShared(context).getInt(key, -1);
    }

    public static String getSharedString(Context context, String key) {
        return getShared(context).getString(key, "");
    }

    public static boolean getSharedBoolean(Context context, String key, boolean def) {
        return getShared(context).getBoolean(key, def);
    }
}
