package com.upper.team1726.bankbox.fragment.IndividualBankFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.fragment.ATMFragment;
import com.upper.team1726.bankbox.fragment.BranchFragment;
import com.upper.team1726.bankbox.fragment.ExchangeFragment;


public class AddressFragment extends Fragment implements View.OnClickListener {

    private Button btnBranch, btnExchange, btnATM;
    private EditText etSearch;
    private int bank_id;
    private String searchData;

    public AddressFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        //get data from specific bank
        Bundle bundle = getArguments();
        if (bundle != null) {
            bank_id = bundle.getInt("BANK_ID");//String bankNam=db.getBankName(bank_id);
            Log.e("Bank in af", String.valueOf(bank_id));
        }


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        btnBranch = (Button) view.findViewById(R.id.btnBranch);
        btnATM = (Button) view.findViewById(R.id.btnATM);
        btnExchange = (Button) view.findViewById(R.id.btnExchange);


        displayView(R.id.btnBranch);

        btnBranch.setSelected(true);
        btnBranch.setOnClickListener(this);
        btnATM.setOnClickListener(this);
        btnExchange.setOnClickListener(this);
        // etSearch.setFocusableInTouchMode(true);
        //etSearch.requestFocus();


        return view;
    }


    @Override
    public void onClick(View v) {


        Button btn = (Button) v;


        // clear state
        btnBranch.setSelected(false);

        btnATM.setSelected(false);

        btnExchange.setSelected(false);


        // change state
        btn.setSelected(true);


        //btn.setBackgroundResource(R.drawable.buttonborderactive);
        displayView(v.getId());

    }

    private void displayView(int menuid) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putInt("KEY", bank_id);
        bundle.putString("SEARCH_DATA", searchData);
        switch (menuid) {
            case R.id.btnBranch:
                fragment = new BranchFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.btnATM:
                fragment = new ATMFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.btnExchange:
                fragment = new ExchangeFragment();
                fragment.setArguments(bundle);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }
    }


}
