package com.kavita.ppf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kavita.ppf.interest_rate.InterestRateActivity;
import com.kavita.ppf.widget.LineChartView;
import com.kavita.ppf.yearly_limit.YearlyLimitActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        YearRecyclerAdapter recyclerAdapter = new YearRecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);

        LineChartView lineChartView = (LineChartView) findViewById(R.id.lineChart);
        float values[] = { 100, 150, 160, 180, 230, 300, 250, 350, 400 };
        lineChartView.setValues(values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.menu_item_limit:
            {
                Intent intent = new Intent(MainActivity.this, YearlyLimitActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.menu_item_rate:
            {
                Intent intent = new Intent(MainActivity.this, InterestRateActivity.class);
                startActivity(intent);
                return true;
            }
            default:
                // Not ours
        }

        return super.onOptionsItemSelected(item);
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
