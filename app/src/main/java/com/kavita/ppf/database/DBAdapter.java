package com.kavita.ppf.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sharad on 16-Jun-16.
 */

public class DBAdapter {
    private static final String TAG = "DBAdapter";
    public static final int DATABASE_VERSION = 1;

    public static final String KEY_ACCOUNT_ROWID            = "id";
    public static final String KEY_ACCOUNT_BANK_NAME        = "bank_name";
    public static final String KEY_ACCOUNT_ACCOUNT_NUMBER   = "account_number";
    public static final String KEY_ACCOUNT_BRANCH_NAME      = "branch_name";
    public static final String KEY_ACCOUNT_START_DATE       = "start_date";

    public static final String[] ALL_KEYS_ACCOUNT = new String[] {KEY_ACCOUNT_ROWID,
            KEY_ACCOUNT_BANK_NAME, KEY_ACCOUNT_ACCOUNT_NUMBER,
            KEY_ACCOUNT_BRANCH_NAME, KEY_ACCOUNT_START_DATE };

    public static final String DATABASE_NAME = "ppf";
    public static final String DATABASE_TABLE_ACCOUNT = "account_table";

    private static final String DATABASE_CREATE_SQL_ACCOUNT = "create table " + DATABASE_TABLE_ACCOUNT
            + " ("
            + KEY_ACCOUNT_ROWID          + " integer primary key autoincrement, "
            + KEY_ACCOUNT_BANK_NAME      + " text not null, "
            + KEY_ACCOUNT_ACCOUNT_NUMBER + " text not null, "
            + KEY_ACCOUNT_BRANCH_NAME    + " text not null, "
            + KEY_ACCOUNT_START_DATE     + " integer not null"
            + ");";

    private final Context context;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        mDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBAdapter open() {
        mDB = mDBHelper.getWritableDatabase();

        // Enable foreign key constraints
        if (!mDB.isReadOnly()) {
            mDB.execSQL("PRAGMA foreign_keys = ON;");
        }

        return this;
    }

    // Close the database connection.
    public void close() {
        mDBHelper.close();
    }

    /**
     * Private class which handles database creation and upgrading.
     * Used to handle low-level database access.
     */
    private class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL_ACCOUNT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ACCOUNT);

            // Recreate new database:
            onCreate(_db);
        }
    }

    /*
       ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
       ++++++++++++++++++ ACCOUNT RECORD METHODS ++++++++++++++++++++
       ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    public long insertAccount(Account account) {
        // Create row's data:
        ContentValues content = new ContentValues();
        content.put(KEY_ACCOUNT_BANK_NAME      , account.getBankName());
        content.put(KEY_ACCOUNT_ACCOUNT_NUMBER , account.getAccountNumber());
        content.put(KEY_ACCOUNT_BRANCH_NAME    , account.getBranchName());
        content.put(KEY_ACCOUNT_START_DATE     , account.getStartDateMsec());

        // Insert it into the database.
        return mDB.insert(DATABASE_TABLE_ACCOUNT, null, content);
    }

    public boolean updateAccount(Account account) {
        String where = KEY_ACCOUNT_ROWID + "=" + account.getId();

        // Create row's data:
        ContentValues content = new ContentValues();
        content.put(KEY_ACCOUNT_BANK_NAME      , account.getBankName());
        content.put(KEY_ACCOUNT_ACCOUNT_NUMBER , account.getAccountNumber());
        content.put(KEY_ACCOUNT_BRANCH_NAME    , account.getBranchName());
        content.put(KEY_ACCOUNT_START_DATE     , account.getStartDateMsec());

        // Insert it into the database.
        return (mDB.update(DATABASE_TABLE_ACCOUNT, content, where, null) != 0);
    }

    public boolean deleteAccount(long rowId) {
        String where = KEY_ACCOUNT_ROWID + "=" + rowId;
        return mDB.delete(DATABASE_TABLE_ACCOUNT, where, null) != 0;
    }

    public Account getAccount(long rowId) {
        Account account = null;
        String where = KEY_ACCOUNT_ROWID + "=" + rowId;
        Cursor c = 	mDB.query(true, DATABASE_TABLE_ACCOUNT, ALL_KEYS_ACCOUNT,
                where, null, null, null, null, null);
        if(c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndex(KEY_ACCOUNT_ROWID));
            String bank = c.getString(c.getColumnIndex(KEY_ACCOUNT_BANK_NAME));
            String accNum = c.getString(c.getColumnIndex(KEY_ACCOUNT_ACCOUNT_NUMBER));
            String branch = c.getString(c.getColumnIndex(KEY_ACCOUNT_BRANCH_NAME));
            long date = c.getLong(c.getColumnIndex(KEY_ACCOUNT_START_DATE));

            account = new Account(id, bank, accNum, branch, date);
        }
        return account;
    }
}
