package com.example.radoslawsubczynski.krokomierz;

import android.content.Context;

/**
 * Created by radoslawsubczynski on 06.12.16.
 */

public class Application extends android.app.Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Application.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Application.context;
    }
}
