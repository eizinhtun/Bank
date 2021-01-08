package com.upper.team1726.bankbox.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.upper.team1726.bankbox.DividerItemDecoration;
import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.adapter.BankAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Bank;

import java.util.ArrayList;
import java.util.List;


public class AllBank extends AppCompatActivity {

    Toolbar toolbar;
    BankDBHandler db;
    AutoCompleteTextView auto_etSearch;
    Spinner sp_CitySearch;
    ArrayList<String> bank_list = null;
    ArrayList<String> auto_citytown = null;
    String searchData = null;
    private List<Bank> bankAll_list;
    private RecyclerView recyclerView;
    private BankAdapter mAdapter;
    private ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_bank);
        toolbar = (Toolbar) findViewById(R.id.toolbar
        );
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSearch = (ImageButton) findViewById(R.id.btn_dia_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AllBank.this);
                LayoutInflater inflater = AllBank.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.customsearch_dialog, null);

                BankDBHandler mydb = new BankDBHandler(getApplicationContext());
                //get data for autocomplete

                auto_citytown = mydb.getAllTownship();
                auto_citytown.addAll(mydb.getAllCity());


                Log.e("Auto_complete count", String.valueOf(auto_citytown.size()));
                ArrayAdapter auto_adapter = new ArrayAdapter(AllBank.this, android.R.layout.simple_dropdown_item_1line, auto_citytown);
                auto_etSearch = (AutoCompleteTextView) dialogView.findViewById(R.id.auto_etSearch);

                auto_etSearch.setAdapter(auto_adapter);
                auto_etSearch.addTextChangedListener(new TextWatcher() {
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
                });


                bank_list = mydb.getAllBankName();
                bank_list.remove(4);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AllBank.this, android.R.layout.simple_dropdown_item_1line, bank_list);
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
                                Log.e("Search Bank data", searchBankData);
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

        });


        db = new BankDBHandler(this);
        bankAll_list = db.getAllBank();
        bankAll_list.remove(3);  //remove MEB
        mAdapter = new BankAdapter(AllBank.this, bankAll_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
