package com.example.radoslawsubczynski.krokomierz.Database;

/**
 * Created by Radek on 2016-12-03.
 */
public class DatabaseSQLDataProviderModule extends DefaultDataProviderModule {



    @Override
    public void isStringExist(String searchValue, String FIREBASE_URL) {

    }

    @Override
    public void getAllContact(DBHelper mydb) {
        try {
            String cos = mydb.getAllCotacts().toString();
            broadcastGetAllContactsSuccess(cos);
        }
        catch (Exception e){
            broadcastGetAllContactsFail();
        }

    }
}

