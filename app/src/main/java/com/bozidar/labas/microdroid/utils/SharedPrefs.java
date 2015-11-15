package com.bozidar.labas.microdroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.model.User;
import com.google.gson.Gson;

/**
 * Created by macbook on 15.10.2015..
 */
public class SharedPrefs {

    private static SharedPrefs INSTANCE;

    private SharedPrefs() {
    }

    public static SharedPrefs getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SharedPrefs();
        }
        return INSTANCE;
    }

    public void save(Context context, String key, String text) {
        SharedPreferences settings;
        android.content.SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(context.getResources().getString(R.string.prefs_name), Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(key, text); //3
        editor.commit(); //4
    }

    public String getValue(Context context, String key) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(context.getResources().getString(R.string.prefs_name), Context.MODE_PRIVATE);
        text = settings.getString(key, ""); //2
        return text;
    }

    public void saveObject(Context context, String key, User object){
        SharedPreferences settings;
        android.content.SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(context.getResources().getString(R.string.prefs_name), Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2


        Gson gson = new Gson();
        String json = gson.toJson(object); // myObject - instance of MyObject
        editor.putString(key, json);
        editor.apply();
    }

    public User loadObject(String key, Context context){
        SharedPreferences settings;
        settings = context.getSharedPreferences(context.getResources().getString(R.string.prefs_name), Context.MODE_PRIVATE); //1
        Gson gson = new Gson();
        String json = settings.getString(key, "");
        return gson.fromJson(json, User.class);
    }

    public void removeUser(Context context){
        SharedPreferences settings;
        android.content.SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(context.getResources().getString(R.string.prefs_name), Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(context.getResources().getString(R.string.user_data), "");
        editor.putString(context.getResources().getString(R.string.login), "");
        editor.apply();
    }
}
