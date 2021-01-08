package com.upper.team1726.bankbox.fragment.LoanFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;


public class KBZLoanFragment extends Fragment {

    TextView tvHireInfo, tvHire, tvOverInfo, tvOver;


    public KBZLoanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kbz_loan, container, false);


        tvHireInfo = (TextView) view.findViewById(R.id.tvKBZHirePurInfo);
        tvHire = (TextView) view.findViewById(R.id.tvKBZHirePur);
        tvOverInfo = (TextView) view.findViewById(R.id.tvKBZOverDraftInfo);
        tvOver = (TextView) view.findViewById(R.id.tvKBZOverDraft);


        String stHire = "<h7>Pay for goods in installations over a period of time with a required down payment of 30-50 percent.</h7>" +
                "<h6>Products/Items eligible for Hire Purchase</h6>" +
                "<ul>" +
                "   <li> Condominiums" +
                "    <li> Computers and electronics" +
                "    <li> Cell phones and other communication devices" +
                "    <li> Automobiles and motorbikes" +
                "    <li> Heavy machinery" +
                "    <li> Agricultural tractor and machinery" +
                "    <li> Gold and jewelry" +
                "    <li> Furniture" +
                "   <li>  Medical equipment" +
                "</ul>" +

                "Down Payment &nbsp  &nbsp    <b>   80-50% of product's total cost</b><br/>" +
                "Service Charges &nbsp  &nbsp  <b>   1% of product’s total cost</b><br/>" +
                "Rental Fees    &nbsp  &nbsp    <b>    9%(Year 1) 5%(Year 2) 5%(Year 3)</br>" +
                "   &nbsp  &nbsp &nbsp  &nbsp   5%(Year 4) 5%(Year 5)</b>";

        String stOver = "<h6>Applying for a loan</h6>" +
                "A typical term of loan and overdraft is one (1) year<br>" +
                "It is thereafter renewable on a yearly basis.Cards at our ATMs.<br>" +
                "<h6>Interest</h6>" +
                "13% annual<br>" +
                "<h6>Interest</h6>" +
                "Loan interest is paid on the total amount approved and overdraft interest is paid on the amount used.<br>" +
                "<h6>Payment period</h6>" +
                "1 Year<br>" +
                "In order to increase the payment period, you will need to reapply for the loan";
        tvOverInfo.setText(Html.fromHtml(stOver));

        tvHireInfo.setText(Html.fromHtml(stHire));


        tvHire.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (tvHireInfo.getVisibility() == View.VISIBLE) {
                    tvHireInfo.setVisibility(View.GONE);

                    tvHire.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow, 0);
                } else {
                    tvHireInfo.setVisibility(View.VISIBLE);

                    tvHire.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uparrow, 0);
                }

            }
        });

        tvOver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (tvOverInfo.getVisibility() == View.VISIBLE) {
                    tvOverInfo.setVisibility(View.GONE);

                    tvOver.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow, 0);
                } else {
                    tvOverInfo.setVisibility(View.VISIBLE);
                    tvOver.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uparrow, 0);
                }

            }
        });


        return view;
    }


}
