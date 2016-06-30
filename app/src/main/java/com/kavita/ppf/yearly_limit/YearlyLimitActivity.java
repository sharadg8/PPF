package com.kavita.ppf.yearly_limit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kavita.ppf.R;

import java.util.ArrayList;
import java.util.List;

public class YearlyLimitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearly_limit);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<YearlyLimitItem> list = new ArrayList<>();
        list.add(new YearlyLimitItem(2016, 150000));
        list.add(new YearlyLimitItem(2015, 150000));
        list.add(new YearlyLimitItem(2014, 150000));
        list.add(new YearlyLimitItem(2013, 150000));
        list.add(new YearlyLimitItem(2012, 100000));
        list.add(new YearlyLimitItem(2011, 100000));
        list.add(new YearlyLimitItem(2010, 100000));
        YearlyLimitRecyclerAdapter adapter = new YearlyLimitRecyclerAdapter(list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
