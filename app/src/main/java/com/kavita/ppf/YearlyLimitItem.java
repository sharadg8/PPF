package com.kavita.ppf;

import java.text.DecimalFormat;

/**
 * Created by Sharad on 22-Jun-16.
 */

public class YearlyLimitItem {
    private int year;
    private float limit;

    public YearlyLimitItem(int year, float limit) {
        this.year = year;
        this.limit = limit;
    }

    public int getYear() {
        return year;
    }

    public String getYearString() {
        return ""+year+String.format("-%02d", (year+1)%100);
    }

    public float getLimit() {
        return limit;
    }

    public String getLimitString() {
        DecimalFormat nf = new DecimalFormat("##,##,##,###");
        return nf.format(limit);
    }
}
