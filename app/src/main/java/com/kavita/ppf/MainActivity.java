package com.kavita.ppf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kavita.ppf.add_account.Account;
import com.kavita.ppf.add_account.AddAccountActivity;
import com.kavita.ppf.database.AccountDataSource;
import com.kavita.ppf.interest_rate.InterestRateActivity;
import com.kavita.ppf.widget.LineChartView;
import com.kavita.ppf.widget.RecyclerItemClickListener;
import com.kavita.ppf.year_details.YearDetailsActivity;
import com.kavita.ppf.yearly_limit.YearlyLimitActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AddTransactionFragment.OnFragmentInteractionListener {
    ArrayList<Account> mAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccounts = new ArrayList<>();

        /*
         * Check if the account is already created,
         * Otherwise direct to add account activity
         */
        if(!isAccountCreated()) {
            /* Switch to add account activity */
            Intent intent = new Intent(MainActivity.this, AddAccountActivity.class);
            intent.putExtra("key", 0); //Optional parameters
            startActivity(intent);
            finish();
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        YearRecyclerAdapter recyclerAdapter = new YearRecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(MainActivity.this, YearDetailsActivity.class);
                    intent.putExtra("key", 1); //Optional parameters
                    startActivity(intent);
                }
            })
        );

        LineChartView lineChartView = (LineChartView) findViewById(R.id.lineChart);
        float values[] = { 100, 150, 160, 180, 230, 300, 250, 350, 400 };
        lineChartView.setValues(values);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_transaction);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                AddTransactionFragment fragment = AddTransactionFragment.newInstance("", "");
                fragment.show(fm, "AddTransaction");
            }
        });
    }

    private boolean isAccountCreated() {
        AccountDataSource ds = new AccountDataSource(this);
        ds.getAccounts(mAccounts);
        return (mAccounts.size() > 0);
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
        if(mAccounts.size() > 0) {
            Calendar startDate = mAccounts.get(0).getStartDate();
            Calendar now = Calendar.getInstance();
            for(int year = startDate.get(Calendar.YEAR); year <= now.get(Calendar.YEAR); year++) {
                rowListItem.add(new YearItem(year, 50000.1f, 50000, 500000));
            }
        }
        return rowListItem;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
