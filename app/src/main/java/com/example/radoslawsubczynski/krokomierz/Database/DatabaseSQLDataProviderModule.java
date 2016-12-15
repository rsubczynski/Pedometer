package com.example.radoslawsubczynski.krokomierz.Database;

/**
 * Created by Radek on 2016-12-03.
 */
public class DatabaseSQLDataProviderModule extends DefaultDataProviderModule {
   private DBHelper mydb  = new DBHelper();

    @Override
    public void isStringExist(String searchValue) {

    }

    @Override
    public void getAllScore() {
        try {
            String proba = mydb.getAllScores().toString();
            broadcastGetAllScoreSuccess(proba);
        }
        catch (NullPointerException e){
            broadcastGetAllScoreFail();
        }
        catch (ArithmeticException e){
            broadcastGetAllScoreFail();
        }
    }
}

