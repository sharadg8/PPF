package com.kavita.ppf.interest_rate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kavita.ppf.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InterestRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_rate);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<InterestRateItem> list = new ArrayList<>();
        list.add(new InterestRateItem(1, Calendar.getInstance(), 8.5f));
        list.add(new InterestRateItem(2, Calendar.getInstance(), 8.5f));
        list.add(new InterestRateItem(3, Calendar.getInstance(), 8.0f));
        list.add(new InterestRateItem(4, Calendar.getInstance(), 8.0f));
        InterestRateRecyclerAdapter adapter = new InterestRateRecyclerAdapter(list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
