package com.kavita.ppf.interest_rate;

import java.util.Calendar;

/**
 * Created by Sharad on 24-Jun-16.
 */

public class InterestRateItem {
    long id;
    private float interestRate;
    private Calendar date;

    public InterestRateItem(long id, float interestRate,long dateMsec) {
        this.id = id;
        this.interestRate = interestRate;
        this.date.setTimeInMillis(dateMsec);
    }

    public InterestRateItem(long id, float interestRate, Calendar date) {
        this.id = id;
        this.interestRate = interestRate;
        this.date = date;
    }

    public long getId(){return this.id;}
    public float getInterestRate() {
        return this.interestRate;
    }
    public long getDateMsec() {
        return this.date.getTimeInMillis();
    }
    public Calendar getDate() {
        return this.date;
    }
}
