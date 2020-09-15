package com.example.heroes_vs_villains;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MYSP {

    public interface KEYS {
        public final static String SP_USER = "SP_USER";
        public final static String SP_LAST_GAME = "SP_LAST_GAME";
    }


    private static MYSP instance;
    private SharedPreferences prefs;

    public static MYSP getInstance() {
        return instance;
    }

    private MYSP(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("APP_SP_DB", Context.MODE_PRIVATE);
    }

    private MYSP(Context context, String sharePreferencesName) {
        prefs = context.getApplicationContext().getSharedPreferences(sharePreferencesName, Context.MODE_PRIVATE);
    }

    public static MYSP initHelper(Context context, String sharePreferencesName) {
        if (instance == null)
            instance = new MYSP(context, sharePreferencesName);
        return instance;
    }

    public void putBoolean(String KEY, boolean value) {
        prefs.edit().putBoolean(KEY, value).apply();
    }

    public void putString(String KEY, String value) {
        prefs.edit().putString(KEY, value).apply();
    }


    public void putInt(String KEY, int value) {
        prefs.edit().putInt(KEY, value).apply();
    }


    public String getString(String KEY, String defvalue) {
        return prefs.getString(KEY, defvalue);
    }


}
