package com.upper.team1726.bankbox.fragment.LoanFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;


public class AYALoanFragment extends Fragment {
    TextView tvEdu;


    public AYALoanFragment() {
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
        View view = inflater.inflate(R.layout.fragment_aya_loan, container, false);

        String edu = "<h4>AYA Education Loan</h4><p>AYA Education Loan provides financial support for diploma,degree and post graduate programmes From local private or overseas instiutions.</p>" +
                "<h5>Benefits</h5><b>Amount you can borrow</b> <br> From Ks.500,000 to Ks.15,000,000 (80 % Program Fees)<br><br>" +
                "<b>How long you can borrow? </b> <br> Repayment terms of Up to 60 months available until your program completion.<br><br>" +
                "<b>Fixed Monthly instalments</b><br> You always know how much you need to pay each month.<br><br>" +
                "<b>Further your education and enjoy interest rate from as low as 13% per year (Amortization method)</b><br><br>" +
                "<h5>Charges</h5><b>Processing Fee  </b> <br>  There are no application fees or processing fees. The opportunity for you to obtain AYA Education Loan is assessed free of charge by AYA Bank.<br><br>" +
                "<b>Annual Interest </b> <br>  13% per year Interest on the loan amount is calculate based on amortization method.<br><br>" +
                "<b>Late Payment Fee   </b> <br> Late Payment Fee is charged at 3% per month on the total monthly payment due calculated daily.3% is charged to the loan account directly.<br><br>" +
                "<b>Early Repayment Fee </b> <br> There are no early repayment fees for a lump sum payment.<br><br>" +
                "<b>Cancellation Fee   </b> <br>   There are no cancellation fees or charges. Your loan repayment must be paid up in full upon your cancellation request or you can repay monthly until your last payment has been made.";

        tvEdu = (TextView) view.findViewById(R.id.tvAYAEduInfo);

        tvEdu.setText(Html.fromHtml(edu));
        return view;
    }


}
