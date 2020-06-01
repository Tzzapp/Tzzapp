package com.usn.tzzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {


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

    /* When the user presses the back-button on the actionbar,
       the user is taken back to the app's front page(MainActivity)
     */
    @Override
    public void onBackPressed(){
       Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
            finish();
    }
}