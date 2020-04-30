package com.usn.tzzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class SettingsFragment extends PreferenceFragmentCompat {
    private SharedPreferences sharedPreferences;

    SwitchPreference nightMode;

    ListPreference langPref;

    PreferenceManager preferenceManager;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        preferenceManager = getPreferenceManager();

        sharedPreferences = preferenceManager.getSharedPreferences();
        langPref = findPreference(getString(R.string.lang));

        nightMode = findPreference(getString(R.string.key_dark_mode));

            nightMode.setOnPreferenceChangeListener((preference, newValue) -> {
                if ((Boolean) newValue) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    nightMode.setChecked(true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    nightMode.setChecked(false);
                }
                return false;
            });

       /* langPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(preference.getKey().equals(newValue)) {
                    Resources res = getResources();
                    Configuration configuration = res.getConfiguration();
                    configuration.setLocale(new Locale((sharedPreferences.getString("lang", ""))));
                }
                return false;
            }
        });*/

        langPref.setOnPreferenceChangeListener((preference, newValue) -> {
            langPref.setValue((String) newValue);
            new LangUtil(getResources(), getActivity()).changeLang((String) newValue);
            getActivity().recreate();

            return false;
        });

      /*  if (langPref != null) {
            // Log.e("Langpref", langPref.getValue());

            preferenceManager.getSharedPreferences().edit().putString("langSelected", langPref.getValue());
            Log.e("Langpref", preferenceManager.getSharedPreferences().getString("langSelected", ""));
        }*/
      
    }

    @Override
    public void onStart() {
        super.onStart();
        // This will load the preference back in to the screen when you open preferences
        nightMode.setChecked(sharedPreferences.getBoolean("nightmode", false));
        langPref.setValue(sharedPreferences.getString("lang", "no"));
    }

    @Override
    public void onPause() {
        super.onPause();
        // This will send the requested String in to SharedPreferences,
        // where the first argument is where to put it and the second is what to put there
        sharedPreferences.edit().putString("lang", langPref.getValue() ).apply();
        sharedPreferences.edit().putBoolean("nightmode", nightMode.isChecked()).apply();

    }
}











