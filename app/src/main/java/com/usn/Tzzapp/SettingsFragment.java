package com.usn.Tzzapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;


public class SettingsFragment extends PreferenceFragmentCompat {
    public SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);


        ListPreference langPref = findPreference("list_preference_lang");

      /*  PreferenceManager preferenceManager = getPreferenceManager();


        if (langPref != null) {
            Log.e("Langpref", langPref.getValue());


            *//*preferenceManager.getSharedPreferences().edit().putString("langSelected", "no2");
            Log.e("Langpref", preferenceManager.getSharedPreferences().getString("langSelected","nnn"));*/


    }


}











