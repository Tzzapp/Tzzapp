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

    String lang = "";

    SharedPreferences sharedPreferences;

    Intent intentSettings;

    LangUtil langUtil;

    SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChanged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        langUtil = new LangUtil(getResources(), this);

        // This will first load in PreferenceManager and then it will get the getDefaultSharedPreferences
        // and then it will get the string using sharedPreferences.getString and the key langSelected
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        langUtil.changeLang(sharedPreferences.getString("lang", ""));
        setNightMode(sharedPreferences.getBoolean("nightmode", false));

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.tzzapp_logo);
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
        langUtil.changeLang(sharedPreferences.getString("lang", ""));
        setNightMode(sharedPreferences.getBoolean("nightmode", false));

        //sharedPreferences = getSharedPreferences("langSelected", MODE_PRIVATE);
      // lang = sharedPreferences.getString("langSelected", "");


    }

    private void setNightMode(boolean state) {
       // sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // sharedPreferences = getSharedPreferences("nightmode", MODE_PRIVATE);
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
