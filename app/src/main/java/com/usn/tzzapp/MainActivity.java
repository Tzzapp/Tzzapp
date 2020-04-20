package com.usn.tzzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.usn.tzzapp.equiment.Equipment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String lang = "";

    SharedPreferences sharedPreferences;

    Intent intentSettings;

    SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChanged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This will first load in PreferenceManager and then it will get the getDefaultSharedPreferences
        // and then it will get the string using sharedPreferences.getString and the key langSelected
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeLang(sharedPreferences.getString("langSelected", ""));

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

        //changeLang(sharedPreferences.getString("langSelected", String.valueOf(MODE_PRIVATE)));


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
