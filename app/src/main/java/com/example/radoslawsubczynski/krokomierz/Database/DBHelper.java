package com.example.radoslawsubczynski.krokomierz.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.radoslawsubczynski.krokomierz.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Radek on 2016-12-03.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "score";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_STEP = "step";
    public static final String CONTACTS_COLUMN_DISTANCE = "distance";
    public static final String CONTACTS_COLUMN_DATA = "data";
    private HashMap hp;

    public DBHelper() {
        super(Application.getAppContext(), DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table score " +
                        "(id integer primary key, step text,distance text,data text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS score");
        onCreate(db);
    }

    public boolean insertScore(String step, String distance, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("step", step);
        contentValues.put("distance", distance);
        contentValues.put("data", data);
        db.insert("score", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from score where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateScore(Integer id, String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("step", name);
        contentValues.put("distance", phone);
        contentValues.put("data", email);
        db.update("score", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteScore(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("score",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllScores() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from score", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_STEP)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_DISTANCE)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_DATA)));
            res.moveToNext();
        }
        return array_list;
    }
}

