package com.usn.tzzapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;


public class SettingsFragment extends PreferenceFragmentCompat {

    private PreferenceManager preferenceManager;
    ListPreference langPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        langPref = findPreference(getString(R.string.lang));

        preferenceManager = getPreferenceManager();


    }

    @Override
    public void onPause() {
        super.onPause();

        preferenceManager.getSharedPreferences().edit().putString("langSelected", langPref.getValue()).apply();
    }

    @Override
    public void onStart() {
        super.onStart();
        String lang = "";
        lang = preferenceManager.getSharedPreferences().getString("langSelected" ,"");
        langPref.setValue(lang);
    }
}











