package com.usn.tzzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Locale;


public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        setTitle(R.string.title_activity_settings);

    }

/*
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("Boing", newConfig.locale+"kake");
    }
*/

    @Override
    protected void onNightModeChanged(int mode) {
        super.onNightModeChanged(mode);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        new LangUtil(getResources(), this).changeLang(sharedPreferences.getString("lang", ""));
    }

    @Override
    public void onBackPressed(){
       Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
            finish();
    }
}