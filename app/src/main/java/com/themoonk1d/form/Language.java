package com.themoonk1d.form;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class Language {
    private final Context _context;
    public Language(Context context){
        _context = context;
    }
    public void changeRes(String code){
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources res = _context.getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, res.getDisplayMetrics());

    }
}
