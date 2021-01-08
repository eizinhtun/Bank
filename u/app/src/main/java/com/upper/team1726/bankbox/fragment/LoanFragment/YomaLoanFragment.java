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


public class YomaLoanFragment extends Fragment implements View.OnClickListener {

    TextView tvTermInfo, tvOverInfo, tvTempoInfo, tvGoldInfo, tvLienInfo, tvShareInfo, tvPledgeInfo, tvHomeInfo, tvHireInfo;
    TextView tvTerm, tvOver, tvTempo, tvGold, tvLien, tvShare, tvPledge, tvHome, tvHire;
    ArrayList<TextView> info = new ArrayList<>();
    ArrayList<TextView> title = new ArrayList<>();


    String homeLoan = "<ul><li>Land  Building, Condo - <b>up to 15 years</b>" +
            "  <li>  Apartment – <b>up to 10 years</b>" +
            "  <li><b>13% </b>Interest per annum" +
            "  <li> <b>1% </b>Processing Expenses (one time – include assessor  lawyer fees)" +
            "  <li>Penalty – <b>same as existing loans </b>" +
            "  <li> Early repayment fee – <b>3 years, 3%</b> on the outstanding amount";

    String tearmLoan = " &nbsp &nbsp The Yoma Bank Term Loan is a business loan backed by eligible collateral with a one-year tenor •<ul><li><b>12%</b> p.a. interest (fees and charges apply) + principal\n" +
            "<li><b>One-year tenor</b>, renewable" +
            "<li>Must be used to <b>fund business-related activities</b></ul>";

    String overdraft = " &nbsp Yoma Bank’s Overdraft facility allows you the flexibility of drawing and repaying your loan according to your needs.<br>" +
            "  &nbsp Overdraft is<b> recommended for business clients not certain of their funding needs or requiring flexibility</b> for their working capital needs in a constantly changing business environment.<br>" +
            " &nbsp The loan must be supported by eligible collateral, and has<b> a maximum one-year tenor.</b> <br>" +
            "<ul><li><b>12 % </b> p.a. interest (fees and charges apply)." +
            "<li><b>Maximum 1 year tenorv</b>, renewable." +
            "<li>Interest is <b>calculated daily and charged monthly.</b>" +
            "<li>Must be used to<b> fund business-related activities.</b>" +
            "<li>Overdraft has a <b>1 % commitment fee</b> (competitive-cost fee).</ul>";

    String tempo = " &nbsp &nbsp Yoma Bank’s Temporary Overdraft loan supports your short term financing needs.<br> " +
            " &nbsp &nbsp It is an essential product for businesses in need of short term funding, allowing you to seize unexpected business opportunities and stay flexible and competitive in a changing economic environment.<br>" +
            "<ul><li><b>13 %</b> p.a. interest (fees and charges apply)." +
            "<li><b>Rapid processing. </b>" +
            "<li>Must be used to <b>fund business-related activities. </b></ul>";

    String lien = " &nbsp &nbsp Yoma Bank’s Lien loan is a short-term loan backed by your Yoma Bank Savings or Fixed Deposit account.<br>" +
            "  &nbsp &nbsp It gives you quick access to your deposits, using your account as a short-term loan collateral.<br> " +
            " &nbsp &nbsp Yoma Bank’s Lien Loan lets you borrow up to <b>80% of your savings balance.</b>" +
            "<ul><li><b>13 % </b>p.a. interest (fees and charges apply)." +
            "<li><b>30-day tenor (maximum)</b> for Yoma Bank’s regular saving account holders.</ul>";

    String gold = " &nbsp &nbsp To help customers expand their businesses, Yoma Bank offers the Gold Loan.<br>" +
            " &nbsp &nbsp  Customers can use gold bars or bullion as collateral. <br>" +
            "<ul><li><b>Precious stones</b> are excluded." +
            "<li><b>13 %</b> interest per annum." +
            "<li>The loan must be<b> repaid within one year.</b> </ul>";

    String share = " &nbsp &nbsp For customers who have bought shares in FMI Company Limited, it is possible to use these shares as a guarantee for a loan." +
            "<ul><li>These loans must be <b>repaid within 12 months (maximum)</b>" +
            "<li><b>13 % </b> pa interest" +
            "<li>Must pay <b>monthly interest </b>(competitive rate)" +
            "<li><b>Does not require a commitment fee</b></ul>";

    String pledge = " &nbsp &nbsp For customers who need working capital during the period while their investment is tied up in stocks, they can receive Yoma Bank’s Pledge Loan.<br>" +
            " &nbsp &nbsp To receive this loan, the customer must pledge agricultural produce, various goods, and machinery as collateral against their loan." +
            "<ul><li><b>13 %</b> interest pa" +
            "<li>Interest can be<b> paid monthly (competitive rate)</b>" +
            "<li>Depending on the collateral, the<b> term of the loan can be negotiated</b></ul>";

    String hire = "<ul><li>Down Payment: <b> 30-50 %</b>" +
            "<li>Rental Fee: <b> 7% (1 year) 12% (2 year) 19% (3 year)</b>" +
            "<li>Service Charges: <b>1 %</b>" +
            "<li>Commission: <b> 1% </b></ul>";


    public YomaLoanFragment() {
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
        View view = inflater.inflate(R.layout.fragment_yoma_loan, container, false);

        tvTermInfo = (TextView) view.findViewById(R.id.tvYomaTearmInfo);
        tvTerm = (TextView) view.findViewById(R.id.tvYomaTearm);
        tvOverInfo = (TextView) view.findViewById(R.id.tvYomaOverDraftInfo);
        tvOver = (TextView) view.findViewById(R.id.tvYomaOverDraft);
        tvTempoInfo = (TextView) view.findViewById(R.id.tvYomaTemOverInfo);
        tvTempo = (TextView) view.findViewById(R.id.tvYomaTemOver);
        tvLienInfo = (TextView) view.findViewById(R.id.tvYomaLienInfo);
        tvLien = (TextView) view.findViewById(R.id.tvYomaLien);
        tvShareInfo = (TextView) view.findViewById(R.id.tvYomaShareInfo);
        tvShare = (TextView) view.findViewById(R.id.tvYomaShare);
        tvGoldInfo = (TextView) view.findViewById(R.id.tvYomaGoldInfo);
        tvGold = (TextView) view.findViewById(R.id.tvYomaGold);
        tvPledgeInfo = (TextView) view.findViewById(R.id.tvYomaPledgeInfo);
        tvPledge = (TextView) view.findViewById(R.id.tvYomaPledge);
        tvHomeInfo = (TextView) view.findViewById(R.id.tvYomaHomeInfo);
        tvHome = (TextView) view.findViewById(R.id.tvYomaHome);
        tvHireInfo = (TextView) view.findViewById(R.id.tvYomaHireInfo);
        tvHire = (TextView) view.findViewById(R.id.tvYomaHire);

        tvTermInfo.setText(Html.fromHtml(tearmLoan));
        tvHomeInfo.setText(Html.fromHtml(homeLoan));
        tvOverInfo.setText(Html.fromHtml(overdraft));
        tvTempoInfo.setText(Html.fromHtml(tempo));
        tvLienInfo.setText(Html.fromHtml(lien));
        tvGoldInfo.setText(Html.fromHtml(gold));
        tvShareInfo.setText(Html.fromHtml(share));
        tvPledgeInfo.setText(Html.fromHtml(pledge));
        tvHireInfo.setText(Html.fromHtml(hire));


        addDataToArray();

        tvTerm.setOnClickListener(this);
        tvOver.setOnClickListener(this);
        tvTempo.setOnClickListener(this);
        tvGold.setOnClickListener(this);
        tvLien.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        tvPledge.setOnClickListener(this);
        tvHome.setOnClickListener(this);
        tvHire.setOnClickListener(this);


        return view;
    }


    private void addDataToArray() {

        info.add(tvTermInfo);
        info.add(tvOverInfo);
        info.add(tvTempoInfo);
        info.add(tvLienInfo);
        info.add(tvShareInfo);
        info.add(tvGoldInfo);
        info.add(tvPledgeInfo);
        info.add(tvHomeInfo);
        info.add(tvHireInfo);

        title.add(tvTerm);
        title.add(tvOver);
        title.add(tvTempo);
        title.add(tvLien);
        title.add(tvShare);
        title.add(tvGold);
        title.add(tvPledge);
        title.add(tvHome);
        title.add(tvHire);
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
