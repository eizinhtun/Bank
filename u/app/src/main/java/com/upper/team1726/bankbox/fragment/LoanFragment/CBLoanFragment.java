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


public class CBLoanFragment extends Fragment implements View.OnClickListener {
    TextView tvHireInfo, tvOverInfo, tvLoanInfo, tvGoldInfo, tvEasiInfo, tvEcoInfo, tvPledgeInfo, tvTradeInfo, tvEduInfo;
    TextView tvHire, tvOver, tvLoan, tvGold, tvEasi, tvEco, tvPledge, tvTrade, tvEdu;
    ArrayList<TextView> info = new ArrayList<>();
    ArrayList<TextView> title = new ArrayList<>();


    String hire = "It is a kind of loan to be paid back by customer in installment for the amount paid by bank during target period." +
            "<ul><li>DownPayment: <b>30 %( minimum) </b>" +
            "<li>Loan Tenor: <b> 10 years (maximum )</b>" +
            "<li>Service Charges: <b> 1 % </b>" +
            "<li>Interest Rate: <b> 13 % p.a</ul>";

    String over = "It shall be a kind of loan permitted by bank to be borrowed more than the amount in the current account of customer.";

    String loan = "It is a kind of loan suitable for profitable projects/plans in period limitation.";

    String gold = "Loan can be got for necessary investment keeping gold ware owned by customer as security deposit in bank." +
            "<ul><li>Interest rate: <b>13 % p.a</b></ul>";

    String easi = "It is a kind of loan to be borrowed with deposit guaranty without any loss of interest upon the deposit of customer." +
            "<ul><li>Interest rate: <b>13 % p.a</b></ul>";

    String eco = "It is a kind of loan supporting to businessmen to be better in cash circulation in business." +
            "<ul><li>Interest rate:<b> 10 % p.a</b></ul>";

    String pledge = "Exporters can get loan keeping their agricultural products as pledge in bank. ";

    String trade = "Trade Facility is a product which apply to the customers of Import/Export connection with bank." +
            "<ul><li>Interest rate:<b> 12 % p.a</b> <li>Service Charge: <b>1 %</b></ul>";

    String edu = "CB bank Education Loan aims to provide the much needed financial support to deserving students for pursuing higher education. " +
            "<ul><li>Interest rate:<b> 10 % p.a</b> <li>Service Charge: <b>1 %</b> <li> <b> 9 % </b> on remaining amount by monthly installment</ul>";


    public CBLoanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cb_loan, container, false);

        tvHireInfo = (TextView) view.findViewById(R.id.tvCBHirePurInfo);
        tvHire = (TextView) view.findViewById(R.id.tvCBHirePur);
        tvOverInfo = (TextView) view.findViewById(R.id.tvCBOverDraftInfo);
        tvOver = (TextView) view.findViewById(R.id.tvCBOverDraft);
        tvLoanInfo = (TextView) view.findViewById(R.id.tvCBLoanInfo);
        tvLoan = (TextView) view.findViewById(R.id.tvCBLoan);
        tvGoldInfo = (TextView) view.findViewById(R.id.tvCBGoldInfo);
        tvGold = (TextView) view.findViewById(R.id.tvCBGold);
        tvEasiInfo = (TextView) view.findViewById(R.id.tvCBEasiInfo);
        tvEasi = (TextView) view.findViewById(R.id.tvCBEasi);
        tvEcoInfo = (TextView) view.findViewById(R.id.tvCBEcoInfo);
        tvEco = (TextView) view.findViewById(R.id.tvCBEco);
        tvPledgeInfo = (TextView) view.findViewById(R.id.tvCBPledgeInfo);
        tvPledge = (TextView) view.findViewById(R.id.tvCBPledge);
        tvTradeInfo = (TextView) view.findViewById(R.id.tvCBTradeInfo);
        tvTrade = (TextView) view.findViewById(R.id.tvCBTrade);
        tvEduInfo = (TextView) view.findViewById(R.id.tvCBEduInfo);
        tvEdu = (TextView) view.findViewById(R.id.tvCBEdu);


        tvHireInfo.setText(Html.fromHtml(hire));
        tvOverInfo.setText(Html.fromHtml(over));
        tvLoanInfo.setText(Html.fromHtml(loan));
        tvGoldInfo.setText(Html.fromHtml(gold));
        tvEasiInfo.setText(Html.fromHtml(easi));
        tvEcoInfo.setText(Html.fromHtml(eco));
        tvPledgeInfo.setText(Html.fromHtml(pledge));
        tvTradeInfo.setText(Html.fromHtml(trade));
        tvEduInfo.setText(Html.fromHtml(edu));


        addDataToArray();

        tvHire.setOnClickListener(this);
        tvOver.setOnClickListener(this);
        tvLoan.setOnClickListener(this);
        tvGold.setOnClickListener(this);
        tvEasi.setOnClickListener(this);
        tvEco.setOnClickListener(this);
        tvPledge.setOnClickListener(this);
        tvTrade.setOnClickListener(this);
        tvEdu.setOnClickListener(this);


        return view;
    }

    private void addDataToArray() {

        info.add(tvHireInfo);
        info.add(tvOverInfo);
        info.add(tvLoanInfo);
        info.add(tvGoldInfo);
        info.add(tvEasiInfo);
        info.add(tvEcoInfo);
        info.add(tvPledgeInfo);
        info.add(tvTradeInfo);
        info.add(tvEduInfo);

        title.add(tvHire);
        title.add(tvOver);
        title.add(tvLoan);
        title.add(tvGold);
        title.add(tvEasi);
        title.add(tvEco);
        title.add(tvPledge);
        title.add(tvTrade);
        title.add(tvEdu);
    }


    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        TextView tvInfo = info.get(title.indexOf(v));
        if (tvInfo.getVisibility() == View.VISIBLE) {
            tvInfo.setVisibility(View.GONE);
            // txtView.startAnimation(animSlideup);
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow, 0);
        } else {
            tvInfo.setVisibility(View.VISIBLE);
            // txtView.startAnimation(animSlidedown);
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uparrow, 0);
        }

    }


}
