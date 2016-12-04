package com.example.radoslawsubczynski.krokomierz.Database;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Radek on 2016-12-03.
 */

public interface DataProviderComponent {

    void searchString(final String SearchValue, String firebaseURL);

    void getAllCotacts(DBHelper mydb);

}
