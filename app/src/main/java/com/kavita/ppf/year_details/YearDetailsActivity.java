package com.kavita.ppf.year_details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kavita.ppf.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class YearDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_details);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<YearDetailsItem> list = new ArrayList<>();
        list.add(new YearDetailsItem(Calendar.getInstance(),200000.0f,100000.0f,8.0f,1000.0f));
        list.add(new YearDetailsItem(Calendar.getInstance(),300000.0f,200000.0f,8.0f,3000.0f));
        list.add(new YearDetailsItem(Calendar.getInstance(),300000.0f,300000.0f,8.0f,4000.0f));
        list.add(new YearDetailsItem(Calendar.getInstance(),200000.0f,400000.0f,8.0f,5000.0f));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        YearDetailsRecyclerAdapter adapter = new YearDetailsRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
