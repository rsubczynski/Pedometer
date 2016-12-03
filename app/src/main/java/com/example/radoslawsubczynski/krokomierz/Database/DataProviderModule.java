package com.example.radoslawsubczynski.krokomierz.Database;

/**
 * Created by Radek on 2016-12-03.
 */

public interface DataProviderModule {

    void isStringExist(final String searchValue, String FIREBASE_URL);

    void registerListener(DataProviderListener dataProviderListener);

    void unregisterListener(DataProviderListener dataProviderListener);

    void broadcastOnSearchUserSuccess(String searchUser);


}
