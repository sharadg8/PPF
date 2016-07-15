package com.kavita.ppf.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kavita.ppf.YearItem;

import java.util.ArrayList;

/**
 * Created by Sharad on 15-Jul-16.
 */

public class YearDataSource extends DBAdapter {
    public YearDataSource(Context context){
        super(context);
    }
    public long insertYear(YearItem year) {
        // Create row's data:
        SQLiteDatabase db = openDb();
        ContentValues content = new ContentValues();
        content.put(KEY_YEAR_YEAR , year.getYear());
        content.put(KEY_YEAR_INVEST  ,year.getInvest());
        content.put(KEY_YEAR_BALANCE  ,year.getBalance());
        content.put(KEY_YEAR_INTEREST  ,year.getInterest());

        // Insert it into the database.
        long id = db.insert(DATABASE_TABLE_YEAR, null, content);
        closeDb();
        return id;
    }

    public boolean updateYear(YearItem year) {
        SQLiteDatabase db = openDb();
        String where = KEY_YEAR_ROWID + "=" + year.getId();

        // Create row's data:
        ContentValues content = new ContentValues();
        content.put(KEY_YEAR_YEAR , year.getYear());
        content.put(KEY_YEAR_INVEST  ,year.getInvest());
        content.put(KEY_YEAR_BALANCE  ,year.getBalance());
        content.put(KEY_YEAR_INTEREST  ,year.getInterest());

        // Insert it into the database.
        boolean status = (db.update(DATABASE_TABLE_YEAR, content, where, null) != 0);
        closeDb();
        return status;
    }

    public boolean deleteYear(long rowId) {
        SQLiteDatabase db = openDb();
        String where = KEY_YEAR_ROWID + "=" + rowId;
        boolean status = db.delete(DATABASE_TABLE_YEAR, where, null) != 0;
        closeDb();
        return status;
    }

    public YearItem getYear(long rowId) {
        SQLiteDatabase db = openDb();
        YearItem year= null;
        String where = KEY_YEAR_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE_YEAR, ALL_KEYS_YEAR,
                where, null, null, null, null, null);
        if(c.moveToFirst()) {
            year= parseYear(c);
            closeDb();
        }
        return year;
    }

    public void getYear(ArrayList<YearItem> year) {
        SQLiteDatabase db = openDb();
        year.clear();
        Cursor c = 	db.query(true, DATABASE_TABLE_YEAR, ALL_KEYS_YEAR,
                null, null, null, null, null, null);
        if (c != null) {
            if(c.moveToFirst()) {
                do {
                    year.add(parseYear(c));
                } while (c.moveToNext());
            }
        }
        closeDb();
    }

    private YearItem parseYear(Cursor c) {
        long id = c.getLong(c.getColumnIndex(KEY_YEAR_ROWID));
        int year= c.getInt(c.getColumnIndex(KEY_YEAR_YEAR));
        float invest = c.getFloat(c.getColumnIndex(KEY_YEAR_INVEST));
        float balance = c.getFloat(c.getColumnIndex(KEY_YEAR_BALANCE));
        float interest = c.getFloat(c.getColumnIndex(KEY_YEAR_INTEREST));

        return new YearItem(id, year, invest, interest, balance);
    }
}
