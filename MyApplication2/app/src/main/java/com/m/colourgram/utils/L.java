package com.m.colourgram.utils;

import android.util.Log;

/**
 * Created by Minnie on 7/1/16.
 */
public class L {
    private static String TAG = "ColorGram";
    public static void d(String msg){
        if(Common.IS_DEBUG) {
            Log.d(TAG, msg);
        }
    }
    public static void e(String msg){
        if(Common.IS_DEBUG) {
            Log.e(TAG, msg);
        }
    }
    public static void i(String msg){
        if(Common.IS_DEBUG) {
            Log.i(TAG, msg);
        }
    }
}
