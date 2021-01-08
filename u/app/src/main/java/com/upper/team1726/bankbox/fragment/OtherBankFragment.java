package com.upper.team1726.bankbox.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.adapter.OtherBankAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Currency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class OtherBankFragment extends Fragment {
    BankDBHandler db;
    Context context;
    ///DateFormat fmtDateAndTime=DateFormat.getDateTi;
    TextView dateAndTimeLabel;
    Calendar dateAndTime = Calendar.getInstance();
    RecyclerView recyclerView;
    ImageButton imgCalendar;
    TextView tvDate;
    Date dd = new Date();
    CharSequence c = DateFormat.format("dd/MM/yyyy ", dd.getTime());
    String ss = (String) c;

//    Date dd = new Date();
//    CharSequence c = DateFormat.format("MMMM d, yyyy ", dd.getTime());
//    String ss = (String) c;
//    String s = String.valueOf(ss);
//    String trimdate=s.trim();
    String s = String.valueOf(ss);
    String trimdate = "31/10/2017";
    private ArrayList<Integer> OtherBankArrayList = new ArrayList<>();
    private ArrayList<Currency> EachBankArrayList = new ArrayList<>();
    private ArrayList<ArrayList<Currency>> OtherBankDetailArrayList = new ArrayList<ArrayList<Currency>>();

    //String trimdate=s.trim();


    public OtherBankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_other_bank, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvDate.setText(s);

        BankDBHandler db = new BankDBHandler(getContext());


        OtherBankArrayList = db.getAllCurrency();

        Log.e(" size", " " + OtherBankArrayList.size());

        for (int i = 0; i < OtherBankArrayList.size(); i++) {

            int currency_id = OtherBankArrayList.get(i);
            if (db.getCurrencyDetail(trimdate, currency_id).size() != 0) {

                EachBankArrayList = db.getCurrencyDetail(trimdate, currency_id);
                OtherBankDetailArrayList.add(EachBankArrayList);


            }
        }


        OtherBankAdapter otherBankAdapter = new OtherBankAdapter(OtherBankDetailArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(otherBankAdapter);

        return view;


    }


}



