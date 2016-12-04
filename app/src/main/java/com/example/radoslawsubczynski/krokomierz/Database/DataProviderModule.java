package com.example.radoslawsubczynski.krokomierz.Database;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Radek on 2016-12-03.
 */

public interface DataProviderModule {

    void isStringExist(final String searchValue, String FIREBASE_URL);

    void getAllContact(DBHelper mydb);

    void registerListener(DataProviderListener dataProviderListener);

    void unregisterListener(DataProviderListener dataProviderListener);


    void broadcastGetAllContactsSuccess(String cos);

    void broadcastGetAllContactsFail();


}
