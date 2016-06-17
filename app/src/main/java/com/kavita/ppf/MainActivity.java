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

    private List<String> createItemList() {
        List<String> rowListItem = new ArrayList<String>();
        rowListItem.add("Row 1");
        rowListItem.add("Row 2");
        rowListItem.add("Row 3");
        rowListItem.add("Row 4");
        rowListItem.add("Row 5");
        rowListItem.add("Row 6");
        rowListItem.add("Row 7");
        rowListItem.add("Row 8");
        rowListItem.add("Row 9");
        rowListItem.add("Row 10");
        return rowListItem;
    }
}
