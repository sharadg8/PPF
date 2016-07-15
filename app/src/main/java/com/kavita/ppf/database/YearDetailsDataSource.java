package com.kavita.ppf.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kavita.ppf.year_details.YearDetailsItem;

import java.util.ArrayList;

/**
 * Created by Sharad on 15-Jul-16.
 */

public class YearDetailsDataSource extends DBAdapter {
    public YearDetailsDataSource(Context context){
        super(context);
    }
    public long insertYearDetails(YearDetailsItem yearDetails) {
        // Create row's data:
        SQLiteDatabase db = openDb();
        ContentValues content = new ContentValues();
        content.put(KEY_DETAILS_DATE , yearDetails.getDateMsec());
        content.put(KEY_DETAILS_DEPOSIT  ,yearDetails.getDeposit());
        content.put(KEY_DETAILS_BALANCE  ,yearDetails.getBalance());
        content.put(KEY_DETAILS_INTEREST  ,yearDetails.getInterest());
        content.put(KEY_DETAILS_RATE  ,yearDetails.getRate());

        // Insert it into the database.
        long id = db.insert(DATABASE_TABLE_DETAILS, null, content);
        closeDb();
        return id;
    }

    public boolean updateYearDetails(YearDetailsItem yearDetails) {
        SQLiteDatabase db = openDb();
        String where = KEY_LIMIT_ROWID + "=" + yearDetails.getId();

        // Create row's data:
        ContentValues content = new ContentValues();
        content.put(KEY_DETAILS_DATE , yearDetails.getDateMsec());
        content.put(KEY_DETAILS_DEPOSIT  ,yearDetails.getDeposit());
        content.put(KEY_DETAILS_BALANCE  ,yearDetails.getBalance());
        content.put(KEY_DETAILS_INTEREST  ,yearDetails.getInterest());
        content.put(KEY_DETAILS_RATE  ,yearDetails.getRate());
        // Insert it into the database.
        boolean status = (db.update(DATABASE_TABLE_DETAILS, content, where, null) != 0);
        closeDb();
        return status;
    }

    public boolean deleteYearDetails(long rowId) {
        SQLiteDatabase db = openDb();
        String where = KEY_DETAILS_ROWID + "=" + rowId;
        boolean status = db.delete(DATABASE_TABLE_DETAILS, where, null) != 0;
        closeDb();
        return status;
    }

    public YearDetailsItem getYearDetails(long rowId) {
        SQLiteDatabase db = openDb();
        YearDetailsItem yearDetails= null;
        String where = KEY_DETAILS_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE_DETAILS, ALL_KEYS_DETAILS,
                where, null, null, null, null, null);
        if(c.moveToFirst()) {
            yearDetails= parseYearDetails(c);
            closeDb();
        }
        return yearDetails;
    }

    public void getYearDetails(ArrayList<YearDetailsItem> details) {
        SQLiteDatabase db = openDb();
        details.clear();
        Cursor c = 	db.query(true, DATABASE_TABLE_DETAILS, ALL_KEYS_DETAILS,
                null, null, null, null, null, null);
        if (c != null) {
            if(c.moveToFirst()) {
                do {
                    details.add(parseYearDetails(c));
                } while (c.moveToNext());
            }
        }
        closeDb();
    }

    private YearDetailsItem parseYearDetails(Cursor c) {
        long id = c.getLong(c.getColumnIndex(KEY_DETAILS_ROWID));
        long date= c.getInt(c.getColumnIndex(KEY_DETAILS_DATE));
        float deposit = c.getFloat(c.getColumnIndex(KEY_DETAILS_DEPOSIT));
        float balance = c.getFloat(c.getColumnIndex(KEY_DETAILS_BALANCE));
        float interest = c.getFloat(c.getColumnIndex(KEY_DETAILS_INTEREST));
        float rate = c.getFloat(c.getColumnIndex(KEY_DETAILS_RATE));

        return new YearDetailsItem(id, deposit, balance, interest, rate, date);
    }
}
