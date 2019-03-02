package com.android.guillaume.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private Context context;
    private static String PREF_NAME = "USER_PREF";
    private SharedPreferences sharedPref;


    public PreferencesHelper(Context context) {
        this.context = context;
        this.sharedPref = this.context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }


    //************************ FOR SAVE DATA ***********************************//

    public void saveInPreference(String key, boolean value){
        //SharedPreferences sharedPref = this.context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public void saveInPreference(String key, String value){
        //SharedPreferences sharedPref = this.context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,value);
        editor.apply();
    }

    //TODO  save methods : int, float, long type


    //************************ FOR RETRIEVE DATA ***********************************//

    public boolean getFromPreference(String key, boolean defValue){
       // SharedPreferences sharedPref = this.context.g;
        return sharedPref.getBoolean(key,defValue);
    }

    public String getFromPreference(String key, String defValue){
        //SharedPreferences sharedPref = this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key,defValue);
    }

    //TODO  Retrieve methods : int, float, long type
}
