package com.kavita.ppf.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kavita.ppf.add_account.Account;

import java.util.ArrayList;

/**
 * Created by Sharad on 06-Jul-16.
 */

public class AccountDataSource extends DBAdapter {
    public AccountDataSource(Context context){
        super(context);
    }
    public long insertAccount(Account account) {
        // Create row's data:
        SQLiteDatabase db = openDb();
        ContentValues content = new ContentValues();
        content.put(KEY_ACCOUNT_BANK_NAME      , account.getBankName());
        content.put(KEY_ACCOUNT_ACCOUNT_NUMBER , account.getAccountNumber());
        content.put(KEY_ACCOUNT_BRANCH_NAME    , account.getBranchName());
        content.put(KEY_ACCOUNT_START_DATE     , account.getStartDateMsec());

        // Insert it into the database.
        long id = db.insert(DATABASE_TABLE_ACCOUNT, null, content);
        closeDb();
        return id;
    }

    public boolean updateAccount(Account account) {
        SQLiteDatabase db = openDb();
        String where = KEY_ACCOUNT_ROWID + "=" + account.getId();

        // Create row's data:
        ContentValues content = new ContentValues();
        content.put(KEY_ACCOUNT_BANK_NAME      , account.getBankName());
        content.put(KEY_ACCOUNT_ACCOUNT_NUMBER , account.getAccountNumber());
        content.put(KEY_ACCOUNT_BRANCH_NAME    , account.getBranchName());
        content.put(KEY_ACCOUNT_START_DATE     , account.getStartDateMsec());

        // Insert it into the database.
        boolean status = (db.update(DATABASE_TABLE_ACCOUNT, content, where, null) != 0);
        closeDb();
        return status;
    }

    public boolean deleteAccount(long rowId) {
        SQLiteDatabase db = openDb();
        String where = KEY_ACCOUNT_ROWID + "=" + rowId;
        boolean status = db.delete(DATABASE_TABLE_ACCOUNT, where, null) != 0;
        closeDb();
        return status;
    }

    public Account getAccount(long rowId) {
        SQLiteDatabase db = openDb();
        Account account = null;
        String where = KEY_ACCOUNT_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE_ACCOUNT, ALL_KEYS_ACCOUNT,
                where, null, null, null, null, null);
        if(c.moveToFirst()) {
            account = parseAccount(c);
            closeDb();
        }
        return account;
    }

    public void getAccounts(ArrayList<Account> accounts) {
        SQLiteDatabase db = openDb();
        accounts.clear();
        Cursor c = 	db.query(true, DATABASE_TABLE_ACCOUNT, ALL_KEYS_ACCOUNT,
                null, null, null, null, null, null);
        if (c != null) {
            if(c.moveToFirst()) {
                do {
                    accounts.add(parseAccount(c));
                } while (c.moveToNext());
            }
        }
        closeDb();
    }

    private Account parseAccount(Cursor c) {
        long id = c.getLong(c.getColumnIndex(KEY_ACCOUNT_ROWID));
        String bank = c.getString(c.getColumnIndex(KEY_ACCOUNT_BANK_NAME));
        String accNum = c.getString(c.getColumnIndex(KEY_ACCOUNT_ACCOUNT_NUMBER));
        String branch = c.getString(c.getColumnIndex(KEY_ACCOUNT_BRANCH_NAME));
        long date = c.getLong(c.getColumnIndex(KEY_ACCOUNT_START_DATE));

        return new Account(id, bank, accNum, branch, date);
    }
}

