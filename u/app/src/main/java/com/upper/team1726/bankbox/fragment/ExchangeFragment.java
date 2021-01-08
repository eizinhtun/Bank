package com.upper.team1726.bankbox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.adapter.ExchangeAdapter;
import com.upper.team1726.bankbox.adapter.SearchExchangeAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Exchange;

import java.util.ArrayList;
import java.util.List;


public class ExchangeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Exchange> exchangeArrayList = new ArrayList<>();
    private ExchangeAdapter exchangeAdapter;
    private TextView emptyView;
    private boolean isCheck=false;
    public ExchangeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Log.e(",status", "in exchange");

        String searchLocation = null;
        String searchBank = null;
        int bank_id = 0;
        BankDBHandler db = new BankDBHandler(getContext());
        if (bundle != null) {
            if (bundle.getString("SEARCH_lOCATION") != null && bundle.getString("SEARCH_BANKDATA") != null) {// check data from custom search

                searchLocation = bundle.getString("SEARCH_lOCATION");
                searchBank = bundle.getString("SEARCH_BANKDATA");

                if (searchLocation.equals("")) {
                    if (searchBank.equals("All Banks")) {
                        exchangeArrayList = db.getAllExchangeAddress();
                    } else {
                        bank_id = db.findBankIndex(searchBank);
                        exchangeArrayList = db.getSearchExchangeAddress(bank_id);
                    }

                } else {
                    if (searchBank.equals("All Banks")) {
                        exchangeArrayList = db.getSearchExchangeData(searchLocation);
                    } else {
                        exchangeArrayList = db.getSearchExchangeData(searchBank, searchLocation);
                    }
                }

            }//end for custom search
            else {
                bank_id = bundle.getInt("KEY");
                exchangeArrayList = db.getSearchExchangeAddressNoBankName(bank_id);
                isCheck = true;
            }
        }//end check null
        else {
            Toast.makeText(getContext(), "passed data is null", Toast.LENGTH_SHORT).show();
        }

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        emptyView = (TextView) view.findViewById(R.id.emptyView);
        if (exchangeArrayList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        if (isCheck){
            exchangeAdapter = new ExchangeAdapter(exchangeArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(exchangeAdapter);
    }
    else
    {
        SearchExchangeAdapter exchangeAdapter=new SearchExchangeAdapter(exchangeArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(exchangeAdapter);
    }
        return view;

    }


}
