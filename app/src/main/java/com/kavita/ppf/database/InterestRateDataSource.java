package com.kavita.ppf.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kavita.ppf.interest_rate.InterestRateItem;

import java.util.ArrayList;

/**
 * Created by Sharad on 12-Jul-16.
 */

public class InterestRateDataSource extends DBAdapter {
    public InterestRateDataSource(Context context){
        super(context);
    }
    public long insertInterestRate(InterestRateItem interestRate) {
        // Create row's data:
        SQLiteDatabase db = openDb();
        ContentValues content = new ContentValues();
        content.put(KEY_RATE_INTEREST_RATE , interestRate.getInterestRate());
        content.put(KEY_RATE_DATE          , interestRate.getDateMsec());

        // Insert it into the database.
        long id = db.insert(DATABASE_TABLE_RATE, null, content);
        closeDb();
        return id;
    }

    public boolean updateInterestRate(InterestRateItem interestrate) {
        SQLiteDatabase db = openDb();
        String where = KEY_RATE_ROWID + "=" + interestrate.getId();

        // Create row's data:
        ContentValues content = new ContentValues();
        content.put(KEY_RATE_INTEREST_RATE , interestrate.getInterestRate());
        content.put(KEY_RATE_DATE          , interestrate.getDateMsec());

        // Insert it into the database.
        boolean status = (db.update(DATABASE_TABLE_RATE, content, where, null) != 0);
        closeDb();
        return status;
    }

    public boolean deleteInterestRate(long rowId) {
        SQLiteDatabase db = openDb();
        String where = KEY_RATE_ROWID + "=" + rowId;
        boolean status = db.delete(DATABASE_TABLE_RATE, where, null) != 0;
        closeDb();
        return status;
    }

    public InterestRateItem getInterestRate(long rowId) {
        SQLiteDatabase db = openDb();
        InterestRateItem interestrate= null;
        String where = KEY_RATE_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE_RATE, ALL_KEYS_RATE,
                where, null, null, null, null, null);
        if(c.moveToFirst()) {
            interestrate = parseInterestRate(c);
            closeDb();
        }
        return interestrate;
    }

    public void getInterestRate(ArrayList<InterestRateItem> rates) {
        SQLiteDatabase db = openDb();
        rates.clear();
        Cursor c = 	db.query(true, DATABASE_TABLE_RATE, ALL_KEYS_RATE,
                null, null, null, null, null, null);
        if (c != null) {
            if(c.moveToFirst()) {
                do {
                    rates.add(parseInterestRate(c));
                } while (c.moveToNext());
            }
        }
        closeDb();
    }

    private InterestRateItem parseInterestRate(Cursor c) {
        long id = c.getLong(c.getColumnIndex(KEY_RATE_ROWID));
        long dateMsec = c.getLong(c.getColumnIndex(KEY_RATE_DATE));
        float interestRate = c.getFloat(c.getColumnIndex(KEY_RATE_INTEREST_RATE));

        return new InterestRateItem(id, interestRate, dateMsec);
    }
}
