package com.kavita.ppf.add_account;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.kavita.ppf.MainActivity;
import com.kavita.ppf.R;
import com.kavita.ppf.database.AccountDataSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddAccountActivity extends AppCompatActivity {
    private int mYear;
    private int mMonth;
    private int mDay;
    private EditText mEtBankName;
    private EditText mEtAccountNumber;
    private EditText mEtBankBranch;
    private TextInputLayout mTilBankName;
    private TextInputLayout mTilAccountNumber;
    private TextInputLayout mTilBankBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEtBankName = (EditText) findViewById(R.id.et_bank_name);
        mEtBankBranch = (EditText) findViewById(R.id.et_bank_branch);
        mEtAccountNumber = (EditText) findViewById(R.id.et_account_number);
        mTilBankName = (TextInputLayout) findViewById(R.id.til_bank_name);
        mTilBankBranch = (TextInputLayout) findViewById(R.id.til_bank_branch);
        mTilAccountNumber = (TextInputLayout) findViewById(R.id.til_account_number);

        Calendar currentDate = Calendar.getInstance();
        mYear = currentDate.get(Calendar.YEAR);
        mMonth = currentDate.get(Calendar.MONTH);
        mDay = currentDate.get(Calendar.DAY_OF_MONTH);

        final Button buttonCalendar = (Button) findViewById(R.id.button_calender);
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(AddAccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, day);
                        final SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
                        buttonCalendar.setText(df.format(cal.getTime()));
                        mYear = year;
                        mMonth = month;
                        mDay = day;
                    }
                }, mYear, mMonth, mDay);
                datePicker.show();
            }
        });

        Button buttonCreate = (Button) findViewById(R.id.btn_create_account);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateUserInput()){
                    /* Save data and go to main activity */
                    Calendar date = Calendar.getInstance();
                    date.set(mYear, mMonth, mDay);
                    Account account = new Account(0, mEtBankName.getText().toString(),
                            mEtAccountNumber.getText().toString(),
                            mEtBankBranch.getText().toString(),
                            date
                            );

                    AccountDataSource ds = new AccountDataSource(getApplicationContext());
                    ds.insertAccount(account);

                    Intent intent = new Intent(AddAccountActivity.this, MainActivity.class);
                    intent.putExtra("key", 1); //Optional parameters
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean validateUserInput() {
        mTilBankName.setErrorEnabled(false);
        mTilAccountNumber.setErrorEnabled(false);
        mTilBankBranch.setErrorEnabled(false);

        if(mEtBankName.getText().length() == 0) {
            mTilBankName.setError("Invalid Bank Name!");
            mTilBankName.setErrorEnabled(true);
            return false;
        }
        if(mEtAccountNumber.getText().length() == 0) {
            mTilAccountNumber.setError("Invalid Account Number!");
            mTilAccountNumber.setErrorEnabled(true);
            return false;
        }
        if(mEtBankBranch.getText().length() == 0) {
            mTilBankBranch.setError("Invalid Bank Branch!");
            mTilBankBranch.setErrorEnabled(true);
            return false;
        }
        return true;
    }
}
