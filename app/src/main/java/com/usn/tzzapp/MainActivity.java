package com.usn.tzzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.usn.tzzapp.equipment.Equipment;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    Intent intentSettings;

    LangUtil langUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        langUtil = new LangUtil(getResources(), this);

        /* This will first load in PreferenceManager, then it will get the getDefaultSharedPreferences
           and then get the string and boolean using sharedPreferences.getString and sharedPreferences.getBoolean
           to set the language and app color theme
         */
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        langUtil.changeLang(sharedPreferences.getString("lang", ""));
        setNightMode(sharedPreferences.getBoolean("nightmode", false));

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Button buttonTools = findViewById(R.id.buttonFormulas);

        Button buttonEquipment = findViewById(R.id.buttonEquipment);

        Button buttonPriceHelper = findViewById(R.id.buttonPriceHelper);

        Button buttonSettings = findViewById(R.id.buttonSettings);

        final Intent intentTools = new Intent(this, Tools.class);

        buttonTools.setOnClickListener(v -> startActivity(intentTools));


        final Intent intentEquipment = new Intent(this, Equipment.class);


        buttonEquipment.setOnClickListener(v -> startActivity(intentEquipment));


        final Intent intentPrice = new Intent(this, PriceHelper.class);

        buttonPriceHelper.setOnClickListener((v) -> {
            startActivity(intentPrice);
        });

        intentSettings = new Intent(this, SettingsActivity.class);

        buttonSettings.setOnClickListener((v) -> {

            startActivity(intentSettings);


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





    /*
       Fetches the string value and boolean value that was used before the app was closed,
       so the user doesn't have to change language or color theme to what it previously
       was every time they open the app
     */
    @Override
    protected void onResume() {
        super.onResume();
        langUtil.changeLang(sharedPreferences.getString("lang", ""));
        setNightMode(sharedPreferences.getBoolean("nightmode", false));

    }

    /*
       The sharedPreferences on line 37 fetches and sets the boolean value from
       the SwitchPreference in SettingsFragment into the setNightMode method
     */
    private void setNightMode(boolean state) {
        if(state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
