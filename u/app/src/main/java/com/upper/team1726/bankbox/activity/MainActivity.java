package com.upper.team1726.bankbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    BankDBHandler db;
    Button btnSearh;
    Button btnBank, btnLocation, btnCurrency, btnAccount, btnLoan, btnReview;
    TextView tvAbout;


    AutoCompleteTextView auto_etSearch;
    Spinner sp_CitySearch;
    ArrayList<String> bank_list = null;
    ArrayList<String> auto_citytown = null;
    String searchData = null;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // btnSearh = (Button) findViewById(R.id.btn_search);

        btnBank = (Button) findViewById(R.id.btn_bank);
        btnLocation = (Button) findViewById(R.id.btn_location);
        btnCurrency = (Button) findViewById(R.id.btn_currency_exchange);
        btnAccount = (Button) findViewById(R.id.btn_account);
        btnLoan = (Button) findViewById(R.id.btn_loan);
        btnReview = (Button) findViewById(R.id.btn_review);
        tvAbout = (TextView) findViewById(R.id.tvAboutHome);


        //btnSearh.setOnClickListener(this);
        btnBank.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnCurrency.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnLoan.setOnClickListener(this);
        btnReview.setOnClickListener(this);
        tvAbout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class);

                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View v) {
        Button btnObj = (Button) v;
        switch (btnObj.getId()) {
            /*case R.id.btn_search: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.customsearch_dialog, null);

                BankDBHandler mydb = new BankDBHandler(getApplicationContext());
                //get data for autocomplete

                auto_citytown = mydb.getAllTownship();
                auto_citytown.addAll(mydb.getAllCity());


                Log.e("Auto_complete count", String.valueOf(auto_citytown.size()));
                ArrayAdapter auto_adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, auto_citytown);
                auto_etSearch = (AutoCompleteTextView) dialogView.findViewById(R.id.auto_etSearch);

                auto_etSearch.setAdapter(auto_adapter);
                auto_etSearch.addTextChangedListener(this);

                //  bank_list.add(0,"All Banks");
                bank_list = mydb.getAllBankName();

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, bank_list);
                sp_CitySearch = (Spinner) dialogView.findViewById(R.id.sp_searchBank);
                sp_CitySearch.setAdapter(arrayAdapter);

                builder.setView(dialogView)
                        .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //searchCityTownship()
                                searchData = auto_etSearch.getText().toString();
                                String searchBankData = sp_CitySearch.getSelectedItem().toString();
                                Log.e("Search data", searchData);
                                Log.e("Search Bankdata", searchBankData);
                                Intent intent = new Intent(getApplicationContext(), SearchBank.class);
                                intent.putExtra("SEARCH_DATA", searchData);
                                intent.putExtra("SEARCH_BANK_DATA", searchBankData);
                                startActivity(intent);


                            }
                        })
                        .setNegativeButton(" Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
            break;*/

            case R.id.btn_bank: {
                Intent intent = new Intent(this, AllBank.class);
                startActivity(intent);
            }
            break;

            case R.id.btn_currency_exchange: {
                Intent intent = new Intent(this, CurrencyActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.btn_location: {


                //showProgress(btnLocation);
                Intent intent = new Intent(this, ArroundAllActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.btn_account: {
                Intent intent = new Intent(this, AccountActivity.class);

                startActivity(intent);
            }
            break;

            case R.id.btn_loan: {
                Intent intent = new Intent(this, LoanActivity.class);

                startActivity(intent);
            }
            break;

            case R.id.btn_review: {
                Intent intent = new Intent(this, ReviewActivity.class);

                startActivity(intent);
            }
            break;

        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchData = auto_etSearch.getText().toString();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}