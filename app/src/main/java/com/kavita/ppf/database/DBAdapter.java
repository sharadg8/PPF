package com.kavita.ppf.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sharad on 16-Jun-16.
 */

public class DBAdapter {
    private static final String TAG = "DBAdapter";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ppf";

    /*
     * Account data table
     */
    public static final String KEY_ACCOUNT_ROWID            = "id";
    public static final String KEY_ACCOUNT_BANK_NAME        = "bank_name";
    public static final String KEY_ACCOUNT_ACCOUNT_NUMBER   = "account_number";
    public static final String KEY_ACCOUNT_BRANCH_NAME      = "branch_name";
    public static final String KEY_ACCOUNT_START_DATE       = "start_date";

    public static final String[] ALL_KEYS_ACCOUNT = new String[] {KEY_ACCOUNT_ROWID,
            KEY_ACCOUNT_BANK_NAME, KEY_ACCOUNT_ACCOUNT_NUMBER,
            KEY_ACCOUNT_BRANCH_NAME, KEY_ACCOUNT_START_DATE };

    public static final String DATABASE_TABLE_ACCOUNT = "account_table";

    private static final String DATABASE_CREATE_SQL_ACCOUNT = "create table " + DATABASE_TABLE_ACCOUNT
            + " ("
            + KEY_ACCOUNT_ROWID          + " integer primary key autoincrement, "
            + KEY_ACCOUNT_BANK_NAME      + " text not null, "
            + KEY_ACCOUNT_ACCOUNT_NUMBER + " text not null, "
            + KEY_ACCOUNT_BRANCH_NAME    + " text not null, "
            + KEY_ACCOUNT_START_DATE     + " integer not null"
            + ");";

    /*
     * Interest rate data table
     */
    public static final String KEY_RATE_ROWID            = "id";
    public static final String KEY_RATE_INTEREST_RATE    = "interest_rate";
    public static final String KEY_RATE_DATE             = "date";

    public static final String[] ALL_KEYS_RATE = new String[] {KEY_RATE_ROWID,
            KEY_RATE_INTEREST_RATE, KEY_RATE_DATE};

    public static final String DATABASE_TABLE_RATE = "rate_table";

    private static final String DATABASE_CREATE_SQL_RATE = "create table " + DATABASE_TABLE_RATE
            + " ("
            + KEY_RATE_ROWID         + "integer primary key autoincrement,"
            + KEY_RATE_INTEREST_RATE + "float not null,"
            + KEY_RATE_DATE          + "integer not null"
            + ");";

    /*
     * Yearly limit data table
     */
    public static final String KEY_LIMIT_ROWID          = "id";
    public static final String KEY_LIMIT_YEARLY_LIMIT   = "limit";
    public static final String KEY_LIMIT_YEAR           = "year";

    public static final String[] ALL_KEYS_LIMIT = new String[] {KEY_LIMIT_ROWID,
            KEY_LIMIT_YEARLY_LIMIT, KEY_LIMIT_YEAR};

    public static final String DATABASE_TABLE_LIMIT = "limit_table";

    private static final String DATABASE_CREATE_SQL_LIMIT = "create table " + DATABASE_TABLE_LIMIT
            + " ("
            + KEY_LIMIT_ROWID         + "integer primary key autoincrement,"
            + KEY_LIMIT_YEARLY_LIMIT  + "float not null,"
            + KEY_LIMIT_YEAR          + "integer not null"
            + ");";
    /*
     *Year details data table
     */
    public static final String KEY_DETAILS_ROWID  ="id";
    public static final String KEY_DETAILS_DATE   ="date";
    public static final String KEY_DETAILS_DEPOSIT   ="deposit";
    public static final String KEY_DETAILS_BALANCE   ="balance";
    public static final String KEY_DETAILS_INTEREST   ="interest";
    public static final String KEY_DETAILS_RATE   ="rate";

    public static final String[] ALL_KEYS_DETAILS = new String[] {KEY_LIMIT_ROWID,
        KEY_DETAILS_DATE,KEY_DETAILS_DEPOSIT,KEY_DETAILS_BALANCE,KEY_DETAILS_INTEREST,
    KEY_DETAILS_RATE};

    public static final String DATABASE_TABLE_DETAILS = "details_table";

    public static final String DATABASE_CREATE_SQL_DETAILS= "create table" + DATABASE_TABLE_DETAILS
            +"("
            +KEY_DETAILS_ROWID   +"integer primary key autoincrement,"
            +KEY_DETAILS_DATE    +"integer not null,"
            +KEY_DETAILS_DEPOSIT +"float not null,"
            +KEY_DETAILS_BALANCE +"float not null,"
            +KEY_DETAILS_INTEREST+"float not null,"
            +KEY_DETAILS_RATE    +"float not null"
            +");";

    /*
     *Year data table
     */
    public static final String KEY_YEAR_ROWID  ="id";
    public static final String KEY_YEAR_YEAR  ="year";
    public static final String KEY_YEAR_INVEST  ="invest";
    public static final String KEY_YEAR_BALANCE   ="balance";
    public static final String KEY_YEAR_INTEREST   ="interest";

    public static final String[] ALL_KEYS_YEAR = new String[] {KEY_YEAR_ROWID,
            KEY_YEAR_YEAR,KEY_YEAR_INVEST,KEY_YEAR_BALANCE,KEY_YEAR_INTEREST};

    public static final String DATABASE_TABLE_YEAR = "year_table";

    public static final String DATABASE_CREATE_SQL_YEAR= "create table" + DATABASE_TABLE_YEAR
            +"("
            +KEY_YEAR_ROWID   +"integer primary key autoincrement,"
            +KEY_YEAR_YEAR    +"integer not null,"
            +KEY_YEAR_INVEST  +"float not null,"
            +KEY_YEAR_BALANCE +"float not null,"
            +KEY_YEAR_INTEREST+"float not null"
            +");";


    private final Context mContext;

    private DatabaseHelper mDbHelper;

    public DBAdapter(Context ctx) {
        this.mContext = ctx.getApplicationContext();
    }

    // Open the database connection.
    protected SQLiteDatabase openDb() {
        if (mDbHelper == null) {
            mDbHelper = new DatabaseHelper(mContext);
        }
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Enable foreign key constraints
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys = ON;");
        }

        return db;
    }

    // Close the database connection.
    public void closeDb() {
        mDbHelper.close();
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
            _db.execSQL(DATABASE_CREATE_SQL_RATE);
            _db.execSQL(DATABASE_CREATE_SQL_LIMIT);
            _db.execSQL(DATABASE_CREATE_SQL_DETAILS);
            _db.execSQL(DATABASE_CREATE_SQL_YEAR);

        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ACCOUNT);
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_RATE);
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LIMIT);
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_DETAILS);
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_YEAR);

            // Recreate new database:
            onCreate(_db);
        }
    }
}
