package com.upper.team1726.bankbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.adapter.AccountAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Account;

import java.util.ArrayList;

public class ShowSuggestion extends AppCompatActivity {
    ArrayList<Account> accountArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    AccountAdapter accountAdapter;
    Toolbar toolbar;
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_suggestion);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this,"Sorted according your requirements.",Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<Integer> suggest_list = bundle.getIntegerArrayList("SUGGEST_ID");
        Log.e("In suggest list", "" + suggest_list.size());
        BankDBHandler db = new BankDBHandler(this);
        String str = "";
        for (int i = 0; i < suggest_list.size(); i++) {
            Account account = db.getAccountName(suggest_list.get(i));
            accountArrayList.add(account);
            str += "\n" + account.getBankName() + " " + account.getName();
        }
        //Toast.makeText(this,""+accountArrayList.size()+str,Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        emptyView = (TextView) findViewById(R.id.emptyView);
        if (accountArrayList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        accountAdapter = new AccountAdapter(accountArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(accountAdapter);
    }
}
