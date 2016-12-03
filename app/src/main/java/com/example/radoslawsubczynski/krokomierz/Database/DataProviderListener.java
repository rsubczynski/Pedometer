package com.example.radoslawsubczynski.krokomierz.Database;

/**
 * Created by Radek on 2016-12-03.
 */

public interface DataProviderListener {

    void onSearchSuccess(String searchUser);

    void onSearchFail();
}
