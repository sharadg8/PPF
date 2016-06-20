package com.kavita.ppf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*Intent i = new Intent(getBaseContext(), AddActivity.class);
        i.putExtra("Id", 0);
        startActivity(i);*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        YearRecyclerAdapter recyclerAdapter = new YearRecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<YearItem> createItemList() {
        List<YearItem> rowListItem = new ArrayList<>();
        rowListItem.add(new YearItem(2016, 50000.1f, 50000, 500000));
        rowListItem.add(new YearItem(2015, 40000, 60000, 500000));
        rowListItem.add(new YearItem(2014, 60000, 40000, 500000));
        rowListItem.add(new YearItem(2013, 70000, 10000, 500000));
        rowListItem.add(new YearItem(2012, 120000, 3000, 500000));
        return rowListItem;
    }
}
