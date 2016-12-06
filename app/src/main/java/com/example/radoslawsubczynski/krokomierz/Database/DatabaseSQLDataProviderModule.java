package com.example.radoslawsubczynski.krokomierz.Database;

/**
 * Created by Radek on 2016-12-03.
 */
public class DatabaseSQLDataProviderModule extends DefaultDataProviderModule {
   private DBHelper mydb  = new DBHelper();

    @Override
    public void isStringExist(String searchValue, String FIREBASE_URL) {

    }

//    @Override
    public void getAllContact() {
        try {
            String cos = mydb.getAllCotacts().toString();
            broadcastGetAllContactsSuccess(cos);
        }
        catch (Exception e){
            broadcastGetAllContactsFail();
        }

    }
}

