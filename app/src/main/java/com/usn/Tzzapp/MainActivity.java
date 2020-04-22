package com.usn.Tzzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String lang = "";

    SharedPreferences sharedPreferences;

    Intent intentSettings;

    SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChanged;

    private Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonForms = findViewById(R.id.buttonFormulas);

        Button buttonEquipment = findViewById(R.id.buttonEquipment);

        Button buttonPriceHelper = findViewById(R.id.buttonPriceHelper);

        Button buttonSettings = findViewById(R.id.buttonSettings);

        final Intent intentForms = new Intent(this, Formulas.class);

        buttonForms.setOnClickListener(v -> startActivity(intentForms));


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

        //changeLang(lang);
       // setNightMode();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }






    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = getSharedPreferences("langSelected", MODE_PRIVATE);
        lang = sharedPreferences.getString("langSelected", "");


    }

    private void changeLang(String lang) {


        Resources res = getResources();

        Configuration configuration = res.getConfiguration();

        configuration.setLocale(new Locale(lang));

        DisplayMetrics dm = res.getDisplayMetrics();

        res.updateConfiguration(configuration, dm);
        getResources().updateConfiguration(configuration,dm);
        getApplicationContext().getResources().updateConfiguration(configuration, dm);

        
    }

    private void setNightMode() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // sharedPreferences = getSharedPreferences("nightmode", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("nightmode", false)) {
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
