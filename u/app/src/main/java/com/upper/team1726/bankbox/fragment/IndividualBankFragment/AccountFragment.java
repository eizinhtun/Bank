package com.upper.team1726.bankbox.fragment.IndividualBankFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.adapter.AccountAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Account;

import java.util.ArrayList;


public class AccountFragment extends Fragment {

    String bankName;
    //    ArrayList<TextView> infoArray = new ArrayList<>();
    ArrayList<Account> accountArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    AccountAdapter accountAdapter;

    public AccountFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Bundle bundle = getArguments();
        bankName = bundle.getString("BANK_NAME");
        accountArrayList.clear();

        BankDBHandler db = new BankDBHandler(getContext());
        int bank_id = db.findBankIndex(bankName);
        Log.e("Selected Fragment is", bankName);
        accountArrayList = db.getAccountDescription(bank_id);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        accountAdapter = new AccountAdapter(accountArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(accountAdapter);


        return view;
    }


}

