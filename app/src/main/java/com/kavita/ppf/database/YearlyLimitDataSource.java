package com.kavita.ppf.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kavita.ppf.yearly_limit.YearlyLimitItem;

import java.util.ArrayList;

/**
 * Created by Sharad on 13-Jul-16.
 */

public class YearlyLimitDataSource extends DBAdapter {
    public YearlyLimitDataSource(Context context){
        super(context);
    }
    public long insertYearlyLimit(YearlyLimitItem yearlyLimit) {
        // Create row's data:
        SQLiteDatabase db = openDb();
        ContentValues content = new ContentValues();
        content.put(KEY_LIMIT_YEARLY_LIMIT , yearlyLimit.getLimit());
        content.put(KEY_LIMIT_YEAR          , yearlyLimit.getYear());

        // Insert it into the database.
        long id = db.insert(DATABASE_TABLE_LIMIT, null, content);
        closeDb();
        return id;
    }

    public boolean updateYearlyLimit(YearlyLimitItem yearlyLimit) {
        SQLiteDatabase db = openDb();
        String where = KEY_LIMIT_ROWID + "=" + yearlyLimit.getId();

        // Create row's data:
        ContentValues content = new ContentValues();
        content.put(KEY_LIMIT_YEARLY_LIMIT , yearlyLimit.getLimit());
        content.put(KEY_LIMIT_YEAR          , yearlyLimit.getYear());

        // Insert it into the database.
        boolean status = (db.update(DATABASE_TABLE_LIMIT, content, where, null) != 0);
        closeDb();
        return status;
    }

    public boolean deleteYearlyLimit(long rowId) {
        SQLiteDatabase db = openDb();
        String where = KEY_LIMIT_ROWID + "=" + rowId;
        boolean status = db.delete(DATABASE_TABLE_LIMIT, where, null) != 0;
        closeDb();
        return status;
    }

    public YearlyLimitItem getYearlyLimit(long rowId) {
        SQLiteDatabase db = openDb();
        YearlyLimitItem yearlyLimit= null;
        String where = KEY_LIMIT_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE_LIMIT, ALL_KEYS_LIMIT,
                where, null, null, null, null, null);
        if(c.moveToFirst()) {
            yearlyLimit= parseYearlyLimit(c);
            closeDb();
        }
        return yearlyLimit;
    }

    public void getYearlyLimit(ArrayList<YearlyLimitItem> limits) {
        SQLiteDatabase db = openDb();
        limits.clear();
        Cursor c = 	db.query(true, DATABASE_TABLE_LIMIT, ALL_KEYS_LIMIT,
                null, null, null, null, null, null);
        if (c != null) {
            if(c.moveToFirst()) {
                do {
                    limits.add(parseYearlyLimit(c));
                } while (c.moveToNext());
            }
        }
        closeDb();
    }

    private YearlyLimitItem parseYearlyLimit(Cursor c) {
        long id = c.getLong(c.getColumnIndex(KEY_LIMIT_ROWID));
        int year= c.getInt(c.getColumnIndex(KEY_LIMIT_YEAR));
        float limit = c.getFloat(c.getColumnIndex(KEY_LIMIT_YEARLY_LIMIT));

        return new YearlyLimitItem(id, year, limit);
    }
}
