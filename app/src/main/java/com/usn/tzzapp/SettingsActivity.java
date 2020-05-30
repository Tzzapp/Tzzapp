package com.usn.tzzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


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