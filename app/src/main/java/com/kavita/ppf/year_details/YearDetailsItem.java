package com.kavita.ppf.year_details;

import java.util.Calendar;

/**
 * Created by Sharad on 01-Jul-16.
 */

public class YearDetailsItem {
    private Calendar date;
    private float deposit, balance, rate, interest;

    public YearDetailsItem(Calendar date, float deposit, float balance, float rate, float interest) {
        this.date = date;
        this.deposit = deposit;
        this.balance = balance;
        this.rate = rate;
        this.interest = interest;
    }

    public Calendar getDate() {
        return date;
    }

    public float getDeposit() {
        return deposit;
    }

    public float getBalance() {
        return balance;
    }

    public float getRate() {
        return rate;
    }

    public float getInterest() {
        return interest;
    }
}