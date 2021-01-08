package com.upper.team1726.bankbox.activity;

/**
 * Created by MICRO on 10/25/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;


public class CurrencyCalculator extends AppCompatActivity {

    BankDBHandler db;
    Toolbar toolbar;
    Spinner spCurrencyBank, spFrom, spTo;
    ArrayAdapter<String> fromAdapter, toAdapter, bankAdapter;
    EditText etAmount;
    TextView tvBuy, tvSell;
    TextView tvDate;
    Button calculate;

    ArrayList<String> currencyType1, currencyTypeMyan, myan;

    String[] bank = {"Central Bank", "KBZ", "AGD", "CB", "AYA", "YOMA"};

    double amount, buy, sell, buyResult, sellResult;
    char operation = 'p';


    Date dd = new Date();
    CharSequence c = DateFormat.format("d/M/yyyy ", dd.getTime());
    String ss = (String) c;
    String s = String.valueOf(ss);
    //String trimdate=s.trim();
    String trimdate = "31/10/2017";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = new BankDBHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_calculator);

        toolbar = (Toolbar) findViewById(R.id.interestCalculatorToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvBuy = (TextView) findViewById(R.id.tvBuy);
        tvSell = (TextView) findViewById(R.id.tvSell);

        BankDBHandler db = new BankDBHandler(this);

        bankAdapter = new ArrayAdapter<>(CurrencyCalculator.this, android.R.layout.simple_dropdown_item_1line, bank);
        spCurrencyBank = (Spinner) findViewById(R.id.spCurrencyBank);
        spCurrencyBank.setAdapter(bankAdapter);


        currencyType1 = new ArrayList<>();

        myan = new ArrayList<>();
        myan.add("MMK");


        currencyType1.add("USD");
        currencyType1.add("EURO");
        currencyType1.add("SGD");


        currencyTypeMyan = (ArrayList<String>) currencyType1.clone();//This must be write after adding data to currencytype1
        //append MMK in currencyType2
        currencyTypeMyan.add("MMK");


        fromAdapter = new ArrayAdapter<>(CurrencyCalculator.this, android.R.layout.simple_dropdown_item_1line, currencyTypeMyan);
        spFrom = (Spinner) findViewById(R.id.spFrom);
        spFrom.setAdapter(fromAdapter);


        spCurrencyBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                changeFromArrayContent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        toAdapter = new ArrayAdapter<>(CurrencyCalculator.this, android.R.layout.simple_dropdown_item_1line, myan);
        spTo = (Spinner) findViewById(R.id.spTo);
        spTo.setAdapter(toAdapter);


        spFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                changToArrayContent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        etAmount = (EditText) findViewById(R.id.etCurrencyAmount);


        calculate = (Button) findViewById(R.id.btnCurrencyCalculate);

        calculate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (etAmount.getText().toString().equals("")) {

                    Toast.makeText(CurrencyCalculator.this, "Please write amount!!", Toast.LENGTH_SHORT).show();
                } else {
                    calculateAndShowCurrency(operation);
                }


            }
        });


    }


    public void changeFromArrayContent() {

        if (!spCurrencyBank.getSelectedItem().toString().equals("Central Bank")) {

            String bankName = spCurrencyBank.getSelectedItem().toString();
            int bank_id = db.getBankID(bankName);
            currencyType1.clear();
            ArrayList<String> allCurrencyNameByBank = db.getCurrencyByBank(bank_id);
            currencyType1.addAll(allCurrencyNameByBank);
            currencyType1.add("MMK");
            //currencyType1.add("EURO");
            //currencyType1.add("SGD");

            fromAdapter.clear();
            fromAdapter.addAll(currencyType1);
//            currencyTypeMyan.clear();
//            currencyTypeMyan.add("THB");
//            currencyTypeMyan.add("MYR");
//            currencyTypeMyan.add("MMK");
            fromAdapter.notifyDataSetChanged();
        } else {
            currencyType1.clear();
            currencyType1.addAll(db.getCurrencyTypeByCentral());
            currencyType1.add("MMK");
            fromAdapter.clear();
            fromAdapter.addAll(currencyType1);
//
            fromAdapter.notifyDataSetChanged();

        }


        //clear and retrieve data according to bank type and add to fromAdapter and notifyChange


    }


    public void changToArrayContent() {

        if (spFrom.getSelectedItem().toString().equals("MMK")) {

            toAdapter.clear();
            currencyType1.remove("MMK");
            toAdapter.addAll(currencyType1);
            toAdapter.notifyDataSetChanged();

            //for division
            operation = 'd';

        } else {
            toAdapter.clear();
            toAdapter.add("MMK");
            toAdapter.notifyDataSetChanged();

            //for multiplication
            operation = 'm';
        }
    }


    public void calculateAndShowCurrency(char operation) {

        // buy=db.gtvBuy(trimDate,)
        double[] buySell;
        double centralRate;

        String bankName = spCurrencyBank.getSelectedItem().toString();

        String currency = spFrom.getSelectedItem().toString();
        String currencyTo = spTo.getSelectedItem().toString();

        if (currency.equals("MMK")) {
            currency = spTo.getSelectedItem().toString();
        }

        if (!bankName.equals("Central Bank")) {
            Log.e("TEST BANK NAME", " " + bankName);
            int bank_id = db.getBankID(bankName);
            Log.e("TEST BANK ID", "" + bank_id);
            int currency_id = db.getCurrencyID(currency);
            Log.e("TEST CURRENCY ID", " " + currency_id);
            buySell = db.getBuySell(bank_id, trimdate, currency_id);
            buy = buySell[0];
            sell = buySell[1];
            Log.e("TEST BUY", " " + buy);
            Log.e("TEST SELL", " " + sell);


        } else {
            centralRate = db.getCentralRate(currency);
            buy = centralRate;
            sell = centralRate;

        }


//        buy = 1320;
//        sell = 1321;//fetch data from database

        amount = Double.parseDouble(etAmount.getText().toString());

        switch (operation) {
            case 'd':
                buyResult = amount / buy;
                sellResult = amount / sell;
                break;
            case 'm':
                buyResult = amount * buy;
                sellResult = amount * sell;
                break;
        }


//        tvBuy.setEnabled(false);
//        tvSell.setEnabled(false);


        String formatBuy = NumberFormat.getInstance().format(buyResult);
        String formatSell = NumberFormat.getInstance().format(sellResult);

        tvBuy.setText(formatBuy + " " + currencyTo);
        tvSell.setText(formatSell + " " + currencyTo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_clear) {

//            spCurrencyBank.setSelection(0);
//            spFrom.setSelection(0);
//            spTo.setSelection(0);
            etAmount.setText("");

            tvBuy.setText("");

            tvSell.setText("");

           // Toast.makeText(getApplicationContext(), "Clear action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);

        //  return super.onOptionsItemSelected(item);
    }
}

