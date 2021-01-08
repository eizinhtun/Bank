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
import com.upper.team1726.bankbox.adapter.BranchAdapter;
import com.upper.team1726.bankbox.adapter.SearchBranchAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Branch;

import java.util.ArrayList;
import java.util.List;


public class BranchFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Branch> branchArrayList = new ArrayList<>();
    private BranchAdapter branchAdapter;
    private TextView emptyView;
    private boolean isCheck=false;

    public BranchFragment() {
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
        String search_data;
        BankDBHandler db = new BankDBHandler(getContext());
        if (bundle != null) {
            if (bundle.getString("SEARCH_lOCATION") != null && bundle.getString("SEARCH_BANKDATA") != null) {
                searchLocation = bundle.getString("SEARCH_lOCATION");
                searchBank = bundle.getString("SEARCH_BANKDATA");

                if (searchLocation.equals("")) {
                    if (searchBank.equals("All Banks")) {
                        branchArrayList = db.getAllBranchAddress();
                    } else {
                        bank_id = db.findBankIndex(searchBank);
                        branchArrayList = db.getSearchBranchAddress(bank_id);
                    }

                } else {
                    if (searchBank.equals("All Banks")) {
                        branchArrayList = db.getSearchBranchData(searchLocation);
                    } else {
                        branchArrayList = db.getSearchBranchData(searchBank, searchLocation);
                    }
                }

            }//end for custom search

            else {
                bank_id = bundle.getInt("KEY");
                Log.e("Bank id in branch f", String.valueOf(bank_id));
                branchArrayList = db.getSearchBranchAddressNoBankName(bank_id);
                isCheck=true;
            }

        } else {
            Toast.makeText(getContext(), "passed data is null", Toast.LENGTH_SHORT).show();
        }

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        emptyView = (TextView) view.findViewById(R.id.emptyView);
        if (branchArrayList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

        if(isCheck){
            branchAdapter = new BranchAdapter(branchArrayList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(branchAdapter);
            branchAdapter.notifyDataSetChanged();
        }
       else{
           SearchBranchAdapter branchAdapter = new SearchBranchAdapter(branchArrayList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(branchAdapter);
            branchAdapter.notifyDataSetChanged();
        }
        return view;

    }

}


