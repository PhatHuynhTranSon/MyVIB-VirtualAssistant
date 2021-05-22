package com.example.myvib_virtual_assistant.languages;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class Language {
    private Context mContext;

    private Language(Context context) { mContext = context; }

    public void setLanguage(String language) {
        //Set the language of the language
        Locale currentLocale = new Locale(language);
        Resources res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = currentLocale;
        res.updateConfiguration(conf, dm);
    }

    public String getLanguage() {
        //Get the language of the context
        Locale currentLocale = mContext.getResources().getConfiguration().locale;
        return currentLocale.getLanguage();
    }

    public static Language getInstance(Context context) {
        return new Language(context);
    }
}
