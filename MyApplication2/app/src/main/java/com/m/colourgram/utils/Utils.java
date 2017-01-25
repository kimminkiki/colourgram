package com.m.colourgram.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Minnie on 8/25/16.
 */
public final class Utils {

    public static final String KEY_IS_BEGINNER = "KEY_IS_BEGINNER";


    public static String getString(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(key, null);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor prefsEditor = PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit();

        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }
}
