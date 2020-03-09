package dev.iamfoodie.chamsaccesstest.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        this.sharedPreferences = context.getSharedPreferences("CAS", Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken() {
        return this.sharedPreferences.getString("token", "");
    }

}
