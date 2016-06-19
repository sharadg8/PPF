package com.kavita.ppf;

/**
 * Created by Sharad on 19-Jun-16.
 */

public class YearItem {
    private int year;
    private float invest;
    private float interest;
    private float balance;

    public YearItem(int year, float invest, float interest, float balance) {
        this.year = year;
        this.invest = invest;
        this.interest = interest;
        this.balance = balance;
    }

    public int getYear() {
        return year;
    }

    public float getInvest() {
        return invest;
    }

    public float getInterest() {
        return interest;
    }

    public float getBalance() {
        return balance;
    }
}
