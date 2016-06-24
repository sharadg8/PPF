package com.kavita.ppf.interest_rate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kavita.ppf.widget.LineChartView;
import com.kavita.ppf.R;

public class InterestRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_rate);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LineChartView lineChartView = (LineChartView) findViewById(R.id.lineChart);
        float values[] = { 100000, 100000, 100000, 100000, 100000, 100000,
                150000, 150000, 150000, 150000, 150000, 150000, 150000, 150000 };
        lineChartView.setValues(values);

    }
}
