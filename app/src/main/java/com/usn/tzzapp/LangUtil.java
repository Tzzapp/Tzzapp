package com.usn.tzzapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public  class LangUtil {

    private Resources resources;
    private Activity activity;


    LangUtil(Resources resources, Activity activity){
        this.resources = resources;
        this.activity = activity;
    }

    /**
     * This method has one IN parameter, where the requested language will be send in
     * Using changeLang("") and the short-code for the language
     * like "no" for norwegian and "en" for english and so on.
     *
     * For this to work properly, there has to be a
     * string resource language file in the strings folder (values/strings)
     *
     * @param lang the requested language that the app should change to
     */
    void changeLang(String lang) {


        Configuration configuration = resources.getConfiguration();

        configuration.setLocale(new Locale(lang));

        DisplayMetrics dm = resources.getDisplayMetrics();

        resources.updateConfiguration(configuration,dm);
        activity.getResources().updateConfiguration(configuration, dm);


    }
}
