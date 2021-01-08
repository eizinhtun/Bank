package com.upper.team1726.bankbox.fragment.LoanFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;

import java.util.ArrayList;


public class AGDLoanFragment extends Fragment implements View.OnClickListener {
    TextView tvPersonalInfo, tvOverInfo, tvHirePurInfo, tvGoldInfo, tvTreasuryInfo;
    TextView tvPersonal, tvOver, tvHirePur, tvGold, tvTreasury;
    ArrayList<TextView> info = new ArrayList<>();
    ArrayList<TextView> title = new ArrayList<>();
    String personal, over, hire, gold, treasury;


    public AGDLoanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agd_loan, container, false);

        tvPersonalInfo = (TextView) view.findViewById(R.id.tvAGDPersonalInfo);
        tvPersonal = (TextView) view.findViewById(R.id.tvAGDPersonal);
        tvOverInfo = (TextView) view.findViewById(R.id.tvAGDOverdraftInfo);
        tvOver = (TextView) view.findViewById(R.id.tvAGDOverdraft);
        tvHirePurInfo = (TextView) view.findViewById(R.id.tvAGDHirePurInfo);
        tvHirePur = (TextView) view.findViewById(R.id.tvAGDHirePur);
        tvGoldInfo = (TextView) view.findViewById(R.id.tvAGDGoldInfo);
        tvGold = (TextView) view.findViewById(R.id.tvAGDGold);
        tvTreasuryInfo = (TextView) view.findViewById(R.id.tvAGDTreasuryInfo);
        tvTreasury = (TextView) view.findViewById(R.id.tvAGDTreasury);

        personal = "Interest rate:&nbsp <b>12% per year.</b><br>" +
                "Service charges: &nbsp <b>1% per year.</b><br>" +
                "Authorized amount:&nbsp <b> 60% of Full Sale Value (Collateral)</b>";

        over = "Interest rate:&nbsp <b> 12% per year.</b><br>" +
                "Service charges: &nbsp <b>1% per year.</b>";

        hire = "It is an installment payment plan after a person makes first initial down payment. Installment payment plan can be from 1 year to 5 years.<br><br>" +
                "Down Payment:&nbsp <b>30%</b><br><br>" +
                "Rental Fee:&nbsp <b>9% (1 year) <br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp (9+5)% (2 year) <br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  (9+5+5)% (3 year)<br>" +
                "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  (9+5+5+5)% (4 year)<br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp (9+5+5+5+5)% (5 year)</b><br><br>" +
                "Service Charges: &nbsp <b>1% (1 year)</b><br>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>(1+1)% (2 year)</b><br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>(1+1+1)% (3 year)</b><br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>(1+1+1+1)% (4 year)</b><br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>(1+1+1+1=1)% (5 year)</b><br> <br>" +
                "Commission: &nbsp <b>1%</b>";

        gold = "Interest rate:&nbsp <b> 12% per year.</b><br>" +
                "Service charges:&nbsp <b> 1% per year.</b><br>" +
                "Authorized amount: &nbsp <b>75% of Gold Value</b>";

        treasury = "Interest rate:&nbsp <b> 11.5%.</b><br>" +
                "Service charges:&nbsp <b> 1%.</b><br>" +
                "Authorized amount:&nbsp <b> 80% of Treasury Bond Balance</b>";


        tvPersonalInfo.setText(Html.fromHtml(personal));
        tvOverInfo.setText(Html.fromHtml(over));
        tvHirePurInfo.setText(Html.fromHtml(hire));
        tvGoldInfo.setText(Html.fromHtml(gold));
        tvTreasuryInfo.setText(Html.fromHtml(treasury));


        addDataToArray();

        tvPersonal.setOnClickListener(this);
        tvOver.setOnClickListener(this);
        tvHirePur.setOnClickListener(this);
        tvGold.setOnClickListener(this);
        tvTreasury.setOnClickListener(this);


        return view;
    }


    private void addDataToArray() {

        info.add(tvPersonalInfo);
        info.add(tvOverInfo);
        info.add(tvHirePurInfo);
        info.add(tvGoldInfo);
        info.add(tvTreasuryInfo);

        title.add(tvPersonal);
        title.add(tvOver);
        title.add(tvHirePur);
        title.add(tvGold);
        title.add(tvTreasury);
    }


    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        TextView tvInfo = info.get(title.indexOf(v));
        if (tvInfo.getVisibility() == View.VISIBLE) {
            tvInfo.setVisibility(View.GONE);

            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow, 0);
        } else {
            tvInfo.setVisibility(View.VISIBLE);

            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uparrow, 0);
        }

    }


}
