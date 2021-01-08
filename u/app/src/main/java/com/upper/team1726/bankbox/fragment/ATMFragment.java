package com.upper.team1726.bankbox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.adapter.ATMAdapter;
import com.upper.team1726.bankbox.adapter.SearchATMAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.ATM;

import java.util.ArrayList;
import java.util.List;


public class ATMFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<ATM> atmArrayList = new ArrayList<>();
    private ATMAdapter atmAdapter;
    private TextView emptyView;
    private boolean isCheck=false;
    public ATMFragment() {
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

        String searchLocation = null;
        String searchBank = null;
        int bank_id = 0;
        String search_data = null;
        BankDBHandler db = new BankDBHandler(getContext());
        if (bundle != null) {

            if (bundle.getString("SEARCH_lOCATION") != null && bundle.getString("SEARCH_BANKDATA") != null) {

                searchLocation = bundle.getString("SEARCH_lOCATION");
                searchBank = bundle.getString("SEARCH_BANKDATA");

                if (searchLocation.equals("")) {
                    if (searchBank.equals("All Banks")) {
                        atmArrayList = db.getAllATMAddress();
                    } else {
                        bank_id = db.findBankIndex(searchBank);
                        atmArrayList = db.getSearchATMAddress(bank_id);
                    }
                } else {
                    if (searchBank.equals("All Banks")) {
                        atmArrayList = db.getSearchATMAllBank(searchLocation);
                    } else {
                        atmArrayList = db.getSearchATMData(searchBank, searchLocation);
                    }
                }
            }//end for custom search

            else {
                bank_id = bundle.getInt("KEY");
                atmArrayList = db.getSearchATMAddressNoBankName(bank_id);
                isCheck=true;
            }
        } else {
            Toast.makeText(getContext(), "passed data is null", Toast.LENGTH_SHORT).show();
        }

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        emptyView = (TextView) view.findViewById(R.id.emptyView);
        if (atmArrayList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

        if(isCheck){
            atmAdapter = new ATMAdapter(atmArrayList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(atmAdapter);
        }
       else{
          SearchATMAdapter atmAdapter = new SearchATMAdapter(atmArrayList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(atmAdapter);
        }

        return view;

    }


}

