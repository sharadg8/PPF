package com.kavita.ppf.interest_rate;

import java.util.Calendar;

/**
 * Created by Sharad on 24-Jun-16.
 */

public class InterestRateItem {
    private float interestRate;
    private Calendar date;

    public InterestRateItem(Calendar date, float interestRate) {
        this.interestRate = interestRate;
        this.date = date;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public Calendar getDate() {
        return date;
    }
}
