package com.kavita.ppf.year_details;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sharad on 01-Jul-16.
 */

public class YearDetailsItem {
    private long id;
    private Calendar date;
    private float deposit, balance, rate, interest;

    public YearDetailsItem(long id, float deposit, float balance, float rate, float interest,long DateMsec) {
        this.id = id;;
        this.deposit = deposit;
        this.balance = balance;
        this.rate = rate;
        this.interest = interest;
        this.date.setTimeInMillis(DateMsec);
    }
    public YearDetailsItem(long id, Calendar date, float deposit, float balance, float rate, float interest) {
        this.id = id;
        this.date = date;
        this.deposit = deposit;
        this.balance = balance;
        this.rate = rate;
        this.interest = interest;
    }
    public long getId(){return id; }

    public Calendar getDate() {
        return date;
    }

    public long getDateMsec() {
        return date.getTimeInMillis();
    }

    public String getMonthString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM");
        return sdf.format(date.getTime());
    }

    public float getDeposit() {
        return deposit;
    }
    public String getDepositString() {
        String string = "";
        if(deposit > 0) {
            DecimalFormat nf = new DecimalFormat("##,##,###");
            string = nf.format(Math.floor(deposit));
        }
        return string;
    }
    public float getBalance() {
        return balance;
    }
    public String getBalanceString() {
        String string = "";
        if(balance > 0) {
            DecimalFormat nf = new DecimalFormat("##,##,###");
            string = nf.format(Math.floor(balance));
        }
        return string;
    }
    public float getRate() {
        return rate;
    }
    public String getRateString() {
        DecimalFormat df = new DecimalFormat("##.0%");
        return df.format(rate/100);
    }
    public float getInterest() {
        return interest;
    }
    public String getInterestString() {
        String string = "";
        if(interest > 0) {
            DecimalFormat nf = new DecimalFormat("##,##,###");
            string = nf.format(Math.floor(interest));
        }
        return string;
    }
}