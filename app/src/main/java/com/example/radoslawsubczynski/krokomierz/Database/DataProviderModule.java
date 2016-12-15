package com.example.radoslawsubczynski.krokomierz.Database;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Radek on 2016-12-03.
 */

public interface DataProviderModule {

    void isStringExist(String searchValue);

    void getAllScore();

    void registerListener(DataProviderListener dataProviderListener);

    void unregisterListener(DataProviderListener dataProviderListener);


    void broadcastGetAllScoreSuccess(String cos);

    void broadcastGetAllScoreFail();


}
