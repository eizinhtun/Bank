package com.upper.team1726.bankbox.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Suggest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class AccountSuggestion extends AppCompatActivity implements View.OnClickListener {


    Toolbar toolbar;
    ProgressDialog progress;

    Button btnPurpose, btnType, btnInitialD;
    Button btnWithdrawTime, btnDepositTime;

    Button btnGo;
    AlertDialog.Builder builder;
    String msg = "";
    int[][] amount = {{1000, 9999},
            {10000, 99999},
            {100000, 999999},
            {1000000, 9999999},
            {10000000, 999999999}};
    ArrayList<Suggest> suggest_list = new ArrayList<>();
    ArrayList<Integer> suggest_list_result = new ArrayList<>();
    ArrayList<Suggest> suggestSort = new ArrayList<>();
    ArrayList<Integer> temp = new ArrayList<>();
    int possible_outcomes = 0;
    int initial_deposit_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_suggestion);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnPurpose = (Button) findViewById(R.id.btnPurpose);
        btnType = (Button) findViewById(R.id.btnType);
        btnInitialD = (Button) findViewById(R.id.btnInitialD);
        btnWithdrawTime = (Button) findViewById(R.id.btnWithdrawFrequency);
        btnDepositTime = (Button) findViewById(R.id.btnDepositFrequency);
        btnGo = (Button) findViewById(R.id.btnRecommand);


        btnPurpose.setOnClickListener(this);
        btnInitialD.setOnClickListener(this);
        btnType.setOnClickListener(this);
        btnDepositTime.setOnClickListener(this);
        btnWithdrawTime.setOnClickListener(this);
        btnGo.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        builder = new AlertDialog.Builder(this);
        Button btn = (Button) v;

        switch (btn.getId()) {
            case R.id.btnRecommand:
                possible_outcomes = 0;
                suggest_list_result.clear();
                suggest_list.clear();
                suggestSort.clear();

                String purpose = btnPurpose.getText().toString().trim();
                String initial_deposit = btnInitialD.getText().toString().trim();
                // int initial_deposit_index = spInitialD.getSelectedItemPosition();
                String type = btnType.getText().toString().trim();
                String depositTime = btnDepositTime.getText().toString().trim();
                String withdrawTime = btnWithdrawTime.getText().toString().trim();

                BankDBHandler db = new BankDBHandler(getApplicationContext());
                db.showAccount();

                if (!purpose.equals("Others")) {
                    temp = db.getPurposeSuggestionId(purpose);
                    for (int i = 0; i < temp.size(); i++) {
                        Log.e("ACC_ID", "" + temp.get(i));
                        Suggest suggest = new Suggest(temp.get(i), 3);//one suit => +3
                        suggest_list.add(suggest);

                    }
                    Log.e("Purpose suggest", "" + temp.size());
                    possible_outcomes += 3;
                }


                //for amount suggest
                temp.clear();

                int startAmount = amount[initial_deposit_index][0];
                int endAmount = amount[initial_deposit_index][1];
                Log.e("statAmount", "" + startAmount);
                temp = db.getAmountSuggestionId(startAmount, endAmount);
                for (int i = 0; i < temp.size(); i++) {
                    int index = search(temp.get(i));
                    if (index >= 0) {
                        Suggest suggest = suggest_list.get(index);
                        int count = suggest.getCount() + 3;
                        suggest.setCount(count);
                        Log.e("Amount count old", "" + suggest.getCount());

                    } else {
                        Suggest suggest = new Suggest(temp.get(i), 3);
                        suggest_list.add(suggest);
                        Log.e("Amount count ", "" + suggest.getCount());
                    }
                }
                possible_outcomes += 3;

                //for type suggestion
                if (!type.equals("none")) {
                    temp.clear();
                    possible_outcomes += 1;
                    temp = db.getTypeSuggestion(type);
                    for (int i = 0; i < temp.size(); i++) {
                        int index = search(temp.get(i));
                        if (index >= 0) {
                            Suggest suggest = suggest_list.get(index);
                            int count = suggest.getCount() + 1;
                            suggest.setCount(count);
                            Log.e("type count old", "" + suggest.getCount());

                        } else {
                            Suggest suggest = new Suggest(temp.get(i), 1);
                            suggest_list.add(suggest);
                            Log.e("type count ", "" + suggest.getCount());
                        }
                    }
                }


                //for deposit time  suggestion
                if (!depositTime.equals("Anytime")) {
                    temp.clear();
                    possible_outcomes += 1;
                    temp = db.getDepositFrequency(depositTime);
                    for (int i = 0; i < temp.size(); i++) {
                        int index = search(temp.get(i));
                        if (index >= 0) {
                            Suggest suggest = suggest_list.get(index);
                            int count = suggest.getCount() + 1;
                            suggest.setCount(count);
                            // Log.e("Deposit count Old",""+suggest.getCount());

                        } else {
                            Suggest suggest = new Suggest(temp.get(i), 1);
                            suggest_list.add(suggest);
                            // Log.e("Deposit count ",""+suggest.getCount());
                        }
                    }
                } else {
                    possible_outcomes += 1;
                }

                //for withdraw time  suggestion
                if (withdrawTime.equals("Fixed date")) {
                    temp.clear();
                    possible_outcomes += 1;
                    temp = db.getWithdrawFrequency(withdrawTime);
                    for (int i = 0; i < temp.size(); i++) {
                        int index = search(temp.get(i));
                        if (index >= 0) {
                            Suggest suggest = suggest_list.get(index);
                            int count = suggest.getCount() + 1;
                            suggest.setCount(count);
                            Log.e("Withdraw count old", "" + suggest.getCount());
                        } else {
                            Suggest suggest = new Suggest(temp.get(i), 1);
                            suggest_list.add(suggest);
                            Log.e("Withdraw count ", "" + suggest.getCount());
                        }
                    }
                } else {
                    possible_outcomes += 1;
                }

                //calculate probability
                for (int i = 0; i < suggest_list.size(); i++) {
                    double probability = 0;
                    Suggest suggest = suggest_list.get(i);
                    int favorableOutcomes = suggest.getCount();
                    Log.e("prob is", "" + favorableOutcomes + "/" + possible_outcomes);
                    probability = (double) favorableOutcomes / possible_outcomes;
                    Log.e("Probability is", "" + probability);
                    Log.e("ACC id", "" + suggest.getAccID());

                    if (probability >= 0.5) {
                        Suggest sortSuggest = new Suggest(suggest.getAccID(), probability);
                        suggestSort.add(sortSuggest);
                        // suggest_list_result.add(suggest.getAccID());
                    }
                }

                Collections.sort(suggestSort, new Comparator<Suggest>() {
                    @Override
                    public int compare(Suggest o1, Suggest o2) {
                        if (o1.getProbability() > o2.getProbability()) {
                            return -1;
                        } else if (o1.getProbability() > o2.getProbability()) {
                            return 1;
                        }
                        return 0;
                    }
                });
                for (int i = 0; i < suggestSort.size(); i++) {
                    Suggest suggest = suggestSort.get(i);
                    suggest_list_result.add(suggest.getAccID());
                }
                for (int i = 0; i < suggest_list_result.size(); i++) {
                    Log.e("Real Result ACC_ID", "" + suggest_list_result.get(i));

                }

                progress=new ProgressDialog(AccountSuggestion.this);
                progress.setMessage("Searching...");
                progress.setCancelable(false);
                progress.show();

                Runnable progressRunnable=new Runnable(){
                    @Override
                    public void run(){
                        progress.cancel();
                        Intent intent = new Intent(AccountSuggestion.this, ShowSuggestion.class);
                        Bundle bundle = new Bundle();
                        bundle.putIntegerArrayList("SUGGEST_ID", suggest_list_result);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                };

                Handler pgCanceller=new Handler();
                pgCanceller.postDelayed(progressRunnable,1500);


                break;

            case R.id.btnPurpose:

                builder.setItems(R.array.purpose_array, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] strarr = getResources().getStringArray(R.array.purpose_array);
                        msg = strarr[which];
                        btnPurpose.setText(Html.fromHtml(msg));

                    }
                });
                builder.show();
                break;

            case R.id.btnType:
                builder.setItems(R.array.type_array, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] strarr = getResources().getStringArray(R.array.type_array);
                        msg = strarr[which];
                        btnType.setText(Html.fromHtml(msg));

                    }
                });
                builder.show();
                break;

            case R.id.btnInitialD:
                builder.setItems(R.array.initial_deposit_array, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] strarr = getResources().getStringArray(R.array.initial_deposit_array);
                        msg = strarr[which];
                        initial_deposit_index = which;
                        btnInitialD.setText(Html.fromHtml(msg));

                    }
                });
                builder.show();
                break;

            case R.id.btnWithdrawFrequency:


                builder.setItems(R.array.withdraw_time_array, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] strarr = getResources().getStringArray(R.array.withdraw_time_array);
                        msg = strarr[which];
                        btnWithdrawTime.setText(Html.fromHtml(msg));

                    }
                });
                builder.show();
                break;
            case R.id.btnDepositFrequency:
                builder.setItems(R.array.deposit_time_array, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] strarr = getResources().getStringArray(R.array.deposit_time_array);
                        msg = strarr[which];
                        btnDepositTime.setText(Html.fromHtml(msg));

                    }
                });
                builder.show();
        }

    }

    public int search(int id) {
        int index = -1;
        for (int i = 0; i < suggest_list.size(); i++) {
            Suggest suggest = suggest_list.get(i);
            if (suggest.getAccID() == id) {
                return i;
            }
        }
        return -1;
    }

}
