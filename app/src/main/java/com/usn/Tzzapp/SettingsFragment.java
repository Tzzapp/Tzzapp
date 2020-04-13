package com.usn.Tzzapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import static android.content.Context.MODE_PRIVATE;


public class SettingsFragment extends PreferenceFragmentCompat {
    public SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        ListPreference langPref = findPreference("list_preference_lang");


        PreferenceManager preferenceManager = getPreferenceManager();



        final SwitchPreference nightMode = findPreference(getString(R.string.key_dark_mode));
        nightMode.setDefaultValue(false);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            nightMode.setChecked(true);

            nightMode.setOnPreferenceChangeListener((preference, newValue) -> {
                if ((Boolean) newValue) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    nightMode.setChecked(true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    nightMode.setChecked(false);
                }
                getActivity().recreate();
                return false;
            });


        if (langPref != null) {
            // Log.e("Langpref", langPref.getValue());

            preferenceManager.getSharedPreferences().edit().putString("langSelected", langPref.getValue());
            Log.e("Langpref", preferenceManager.getSharedPreferences().getString("langSelected", ""));




        }


    }
}











