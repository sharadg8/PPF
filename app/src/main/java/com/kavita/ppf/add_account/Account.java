package com.kavita.ppf.add_account;

import java.util.Calendar;

/**
 * Created by Sharad on 16-Jun-16.
 */

public class Account {
    private long mId;
    private String mBankName;
    private String mAccountNumber;
    private String mBranchName;
    private Calendar mStartDate;

    public Account(long id, String bankName, String accNumber, String branchName, long startDateMsec) {
        mId = id;
        mBankName = bankName;
        mAccountNumber = accNumber;
        mBranchName = branchName;
        mStartDate = Calendar.getInstance();
        mStartDate.setTimeInMillis(startDateMsec);
    }

    public Account(long id, String bankName, String accNumber, String branchName, Calendar startDate) {
        mId = id;
        mBankName = bankName;
        mAccountNumber = accNumber;
        mBranchName = branchName;
        mStartDate = startDate;
    }

    public long getId() {
        return mId;
    }

    public String getBankName() {
        return mBankName;
    }

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public String getBranchName() {
        return mBranchName;
    }

    public Calendar getStartDate() {
        return mStartDate;
    }

    public long getStartDateMsec() {
        return mStartDate.getTimeInMillis();
    }
}
