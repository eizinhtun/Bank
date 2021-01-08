package com.upper.team1726.bankbox.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by MICRO on 11/14/2017.
 */

public class LoanCalculatorActivity extends AppCompatActivity {

    BankDBHandler db;
    Spinner spLoanBank, spLoan, spLoanDuration;
    EditText etLoanAmount;
    TextView tvLoanInterest_two, tvResult_two, tvResult_four, tvResult_three, tvResult_one;
    Button btnCalculate;
    LinearLayout downLyt;

    double interestRate[] = {9, 14, 19, 24, 29};
    double interestRateYoma[] = {7, 12, 19};

    ArrayList<String> loanBankArrayList = new ArrayList<>();
    ArrayList<String> loanArrayList = new ArrayList<>();
    ArrayList<String> loanDurationArrayList = new ArrayList<>();

    ArrayAdapter<String> loanBankAdapter;
    ArrayAdapter<String> loanAdapter;
    ArrayAdapter<String> loanDurationAdapter;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loan_calculator);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new BankDBHandler(this);

        spLoanBank = (Spinner) findViewById(R.id.spLoanBank);
        spLoan = (Spinner) findViewById(R.id.spLoan);
        spLoanDuration = (Spinner) findViewById(R.id.spLoanDuration);

        etLoanAmount = (EditText) findViewById(R.id.etLoanAmount);

        tvLoanInterest_two = (TextView) findViewById(R.id.tvLoanInterest_two);
        tvResult_one = (TextView) findViewById(R.id.tvResult_one);
        tvResult_two = (TextView) findViewById(R.id.tvResult_two);
        tvResult_three = (TextView) findViewById(R.id.tvResult_three);
        tvResult_four = (TextView) findViewById(R.id.tvResult_four);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);


        //downLyt=(LinearLayout)findViewById(R.id.downLyt);

        loanBankArrayList = db.getAllBankNameInterest();
        loanBankArrayList.remove("MEB");

        loanBankAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanBankArrayList);
        spLoanBank.setAdapter(loanBankAdapter);

        loanArrayList = db.getAllLoanByBank(1);

        loanAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanArrayList);
        spLoan.setAdapter(loanAdapter);

        loanDurationArrayList.add("one year");
        loanDurationArrayList.add("two years");
        loanDurationArrayList.add("three years");
        loanDurationArrayList.add("four years");
        loanDurationArrayList.add("five years");

        loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
        spLoanDuration.setAdapter(loanDurationAdapter);

        spLoanBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                changeLoanArrayContent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        spLoan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int bank_id = db.getBankID(spLoanBank.getSelectedItem().toString());
                Log.e("GGFfgh", "spinner Loan");
                String loanName = spLoan.getSelectedItem().toString();

                int loan_id = db.getLoanID(loanName, bank_id);
                changeDurationAndInterest(db.getLoanType(loan_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        spLoanDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int bank_id = db.getBankID(spLoanBank.getSelectedItem().toString());
                String loanName = spLoan.getSelectedItem().toString();
                String loanType = db.getLoanType(db.getLoanID(loanName, bank_id));

                if (loanType.substring(loanType.length() - 4).equals("hire")) {
                    int loanDuration = spLoanDuration.getSelectedItemPosition();
                    switch (loanType) {
                        case "kbz_hire":


                            tvLoanInterest_two.setText(String.valueOf(interestRate[loanDuration]) + " %");
                            break;
                        case "agd_hire":
                            tvLoanInterest_two.setText(String.valueOf(interestRate[loanDuration]) + " %");
                            break;
                        case "yoma_hire":
                            tvLoanInterest_two.setText(String.valueOf(interestRateYoma[loanDuration]) + " %");
                            break;
                        case "cb_hire":
                            tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanType)) + " %");
                            break;
                    }
                } else {
                    tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanType)) + " %");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });


    }


    public void changeLoanArrayContent() {
        db = new BankDBHandler(this);
        int bank_id = db.getBankID(spLoanBank.getSelectedItem().toString());
        Log.e("sp BANK name", " = " + spLoanBank.getSelectedItem().toString());
        Log.e("sp BANK_ID", "=" + bank_id);

        loanArrayList.clear();
        Log.e("ALL LOAN name", "=" + db.getAllLoanByBank(bank_id));
        loanArrayList.addAll(db.getAllLoanByBank(bank_id));

        //loanAdapter=new ArrayAdapter<String>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanArrayList);

        loanAdapter.notifyDataSetChanged();
        int loan_id = db.getLoanID(spLoan.getItemAtPosition(0).toString(), bank_id);

        String loanName = db.getLoanType(loan_id);
        changeDurationAndInterest(loanName);
        // loanAdapter.notifyDataSetChanged();
    }


    public void changeDurationAndInterest(String loanName) {

        Log.e("HIRE", "is " + loanName);

        if (loanName.substring(loanName.length() - 4).equals("hire")) {
            switch (loanName) {
                case "kbz_hire":

                    //if(loanName.equals("kbz_hire")) {
                    Log.e("HIRE", "KBZ");
                    loanDurationArrayList.clear();
                    loanDurationArrayList.add(0, "one year");
                    loanDurationArrayList.add(1, "two years");
                    loanDurationArrayList.add(2, "three years");
                    loanDurationArrayList.add(3, "four years");
                    loanDurationArrayList.add(4, "five years");
                    loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                    spLoanDuration.setAdapter(loanDurationAdapter);
                    loanDurationAdapter.notifyDataSetChanged();
                    tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
                    // }
                    break;

                case "yoma_hire":
                    //else if(loanName.equals("yoma_hire")) {
                    loanDurationArrayList.clear();
                    loanDurationArrayList.add(0, "one year");
                    loanDurationArrayList.add(1, "two years");
                    loanDurationArrayList.add(2, "three years");
                    loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                    spLoanDuration.setAdapter(loanDurationAdapter);
                    loanDurationAdapter.notifyDataSetChanged();
                    tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
                    break;
                //}


                case "agd_hire":
                    //else if(loanName.equals("agd_hire")) {
                    loanDurationArrayList.clear();
                    loanDurationArrayList.add(0, "one year");
                    loanDurationArrayList.add(1, "two years");
                    loanDurationArrayList.add(2, "three years");
                    loanDurationArrayList.add(3, "four years");
                    loanDurationArrayList.add(4, "five years");
                    Log.e("AGD", "HIRE");
                    loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                    spLoanDuration.setAdapter(loanDurationAdapter);
                    loanDurationAdapter.notifyDataSetChanged();
                    tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
                    break;
                //}

                case "cb_hire":
                    // else if(loanName.equals("cb_hire")) {
                    loanDurationArrayList.clear();
                    loanDurationArrayList.add(0, "one year");
                    loanDurationArrayList.add(1, "two years");
                    loanDurationArrayList.add(2, "three years");
                    loanDurationArrayList.add(3, "four years");
                    loanDurationArrayList.add(4, "five years");
                    loanDurationArrayList.add(5, "six years");
                    loanDurationArrayList.add(6, "seven years");
                    loanDurationArrayList.add(7, "eight years");
                    loanDurationArrayList.add(8, "nine years");
                    loanDurationArrayList.add(9, "ten years");
                    loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                    spLoanDuration.setAdapter(loanDurationAdapter);
                    loanDurationAdapter.notifyDataSetChanged();
                    tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
                    break;

            }
        } else
        //if(loanName.substring(loanName.length() - 4).equals("loan"))
        {
            if (loanName.equals("yoma_lien_loan")) {

                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "1 day");
                loanDurationArrayList.add(1, "2 days");
                loanDurationArrayList.add(2, "3 days");
                loanDurationArrayList.add(3, "4 days");
                loanDurationArrayList.add(4, "5 days");
                loanDurationArrayList.add(5, "6 days");
                loanDurationArrayList.add(6, "7 days");
                loanDurationArrayList.add(7, "8 days");
                loanDurationArrayList.add(8, "9 days");
                loanDurationArrayList.add(9, "10 days");
                loanDurationArrayList.add(10, "11 days");
                loanDurationArrayList.add(11, "12 days");
                loanDurationArrayList.add(12, "13 days");
                loanDurationArrayList.add(13, "14 days");
                loanDurationArrayList.add(14, "15 days");
                loanDurationArrayList.add(15, "16 days");
                loanDurationArrayList.add(16, "17 days");
                loanDurationArrayList.add(17, "18 days");
                loanDurationArrayList.add(18, "19 days");
                loanDurationArrayList.add(19, "20 days");
                loanDurationArrayList.add(20, "21 days");
                loanDurationArrayList.add(21, "22 days");
                loanDurationArrayList.add(22, "23 days");
                loanDurationArrayList.add(23, "24 days");
                loanDurationArrayList.add(24, "25 days");
                loanDurationArrayList.add(25, "26 days");
                loanDurationArrayList.add(26, "27 days");
                loanDurationArrayList.add(27, "28 days");
                loanDurationArrayList.add(28, "29 days");
                loanDurationArrayList.add(29, "30 days");

                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
            } else if (loanName.equals("aya_education_loan")) {

                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "one year");
                loanDurationArrayList.add(1, "one and half year");
                loanDurationArrayList.add(2, "two year");
                loanDurationArrayList.add(3, "two and half year");
                loanDurationArrayList.add(4, "three year");
                loanDurationArrayList.add(5, "three and half year");
                loanDurationArrayList.add(6, "four year");
                loanDurationArrayList.add(7, "four and half year");
                loanDurationArrayList.add(8, "five year");

                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
            } else if (loanName.equals("cb_gold_loan")) {

                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "1 month");
                loanDurationArrayList.add(1, "2 months");
                loanDurationArrayList.add(2, "3 months");

                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
            } else if (loanName.equals("cb_easi_credit_loan")) {

                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "1 month");

                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");

            } else if (loanName.equals("cb_economy_loan")) {

                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "1 month");
                loanDurationArrayList.add(1, "2 months");
                loanDurationArrayList.add(2, "3 months");
                loanDurationArrayList.add(3, "4 month");
                loanDurationArrayList.add(4, "5 months");
                loanDurationArrayList.add(5, "6 months");
                loanDurationArrayList.add(6, "7 month");
                loanDurationArrayList.add(7, "8 months");
                loanDurationArrayList.add(8, "9 months");
                loanDurationArrayList.add(9, "10 month");
                loanDurationArrayList.add(10, "11 months");
                loanDurationArrayList.add(11, "12 months");

                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
            } else if (loanName.equals("cb_trade_loan")) {

                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "1 month");
                loanDurationArrayList.add(1, "2 months");
                loanDurationArrayList.add(2, "3 months");
                loanDurationArrayList.add(3, "4 month");
                loanDurationArrayList.add(4, "5 months");
                loanDurationArrayList.add(5, "6 months");

                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
            } else if (loanName.equals("cb_education_loan")) {

                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "six months");
                loanDurationArrayList.add(1, "one year");
                loanDurationArrayList.add(2, "one and half year");
                loanDurationArrayList.add(3, "two year");

                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
            } else {
                loanDurationArrayList.clear();
                loanDurationArrayList.add(0, "1 month");
                loanDurationArrayList.add(1, "2 months");
                loanDurationArrayList.add(2, "3 months");
                loanDurationArrayList.add(3, "4 months");
                loanDurationArrayList.add(4, "5 months");
                loanDurationArrayList.add(5, "6 month");
                loanDurationArrayList.add(6, "7 months");
                loanDurationArrayList.add(7, "8 months");
                loanDurationArrayList.add(8, "9 months");
                loanDurationArrayList.add(9, "10 months");
                loanDurationArrayList.add(10, "11 months");
                loanDurationArrayList.add(11, "12 months");
                loanDurationAdapter = new ArrayAdapter<>(LoanCalculatorActivity.this, android.R.layout.simple_dropdown_item_1line, loanDurationArrayList);
                spLoanDuration.setAdapter(loanDurationAdapter);
                loanDurationAdapter.notifyDataSetChanged();
                tvLoanInterest_two.setText(String.valueOf(db.getLoanInterest(loanName)) + " %");
            }

        }

    }
    //11}


    public void calculate() {
        double initialPayment = 0;
        double monthlyPayment = 0;
        String initialPaymentStr = "";
        String monthlyPaymentStr = "";

        if (TextUtils.isEmpty(etLoanAmount.getText().toString())) {
            etLoanAmount.setError("Your Amount is Empty !");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert")
                    .setMessage("Loan Amount is Empty")
                    //.setIcon()
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            etLoanAmount.setText("");
                            dialog.dismiss();
                        }
                    });

            builder.show();

        } else {
            double amount = Double.parseDouble(etLoanAmount.getText().toString());
            String interest = null;
            if (tvLoanInterest_two.getText().toString() != null) {
                interest = tvLoanInterest_two.getText().toString().replace(" %", "");
            }

            double interestRate = Double.parseDouble(interest);

            int[] month;
            int[] year;
            int roomIndex = spLoanDuration.getSelectedItemPosition();


            int bank_id = db.getBankID(spLoanBank.getSelectedItem().toString());
            int loan_id = db.getLoanID((spLoan.getSelectedItem().toString()), bank_id);


            String loanName = db.getLoanType(loan_id);
            if (loanName.substring(loanName.length() - 4).equals("hire")) {
                switch (loanName) {
                    case "kbz_hire":

                        year = new int[]{1, 2, 3, 4, 5};
                        initialPayment = amount * (0.3 + (interestRate + 5 * roomIndex) * (1 - 0.3) * 0.01 + 0.01) * year[roomIndex];
                        monthlyPayment = (1 - 0.3) * amount / (12 * year[roomIndex]);
                        break;

                    case "yoma_hire":
                        year = new int[]{1, 2, 3};
                        initialPayment = amount * (0.3 + (interestRate + 5 * roomIndex) * (1 - 0.3) * 0.01 + 0.01) * year[roomIndex];
                        monthlyPayment = (1 - 0.3) * amount / (12 * year[roomIndex]);
                        break;

                    case "agd_hire":
                        year = new int[]{1, 2, 3, 4, 5};
                        initialPayment = amount * (0.3 + (interestRate + 5 * roomIndex) * (1 - 0.3) * 0.01) + amount * 0.01 * year[roomIndex];
                        monthlyPayment = (1 - 0.3) * amount / (12 * year[roomIndex]);
                        break;

                    case "cb_hire":
                        double percent = 0;
                        if (amount < 5000000) {
                            percent = 0.3;
                        } else if (amount > 5000000 && amount < 10000000) {
                            percent = 0.25;
                        } else {
                            percent = 0.2;
                        }
                        year = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        initialPayment = amount * (percent + (interestRate + 5 * roomIndex) * (1 - percent) * 0.01 + 0.01) * year[roomIndex];
                        monthlyPayment = (1 - percent) * amount / (12 * year[roomIndex]);
                        break;

                }
                initialPaymentStr = NumberFormat.getInstance().format(initialPayment);
                monthlyPaymentStr = NumberFormat.getInstance().format(monthlyPayment);


                if (tvResult_three.getVisibility() != View.VISIBLE) {
                    tvResult_three.setVisibility(View.VISIBLE);
                }

                if (tvResult_four.getVisibility() != View.VISIBLE) {
                    tvResult_four.setVisibility(View.VISIBLE);
                }

                tvResult_one.setText("Initial Payment");

                tvResult_two.setText(initialPaymentStr + " MMK");
                tvResult_four.setText(monthlyPaymentStr + " MMK");

            } else {
                switch (loanName) {
                    case "yoma_lien_loan":
                        month = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
                        break;

                    case "aya_education_loan":
                        month = new int[]{12, 18, 24, 30, 36, 42, 48, 54, 60};
                        break;

                    case "cb_gold_loan":
                        month = new int[]{1, 2, 3};
                        break;

                    case "cb_easi_credit_loan":
                        month = new int[]{1};
                        break;

                    case "cb_economy_loan":
                        month = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        break;

                    case "cb_trade_loan":
                        month = new int[]{1, 2, 3, 4, 5, 6};
                        break;

                    case "cb_education_loan":
                        month = new int[]{6, 12, 18, 24};
                        break;

                    default:
                        month = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                        break;

                }
                initialPayment = amount * interestRate * month[roomIndex] / 1200;
                Log.e("Interest Rate", "" + interestRate);

                // monthlyPayment = amount * interestRate*month[roomIndex]/1200;

                initialPaymentStr = NumberFormat.getInstance().format(initialPayment);
                // monthlyPaymentStr = NumberFormat.getInstance().format(monthlyPayment);

                tvResult_one.setText("Total Interest");
                tvResult_two.setText(initialPaymentStr + " MMK");

                if (tvResult_three.getVisibility() == View.VISIBLE) {
                    tvResult_three.setVisibility(View.GONE);
                }

                if (tvResult_four.getVisibility() == View.VISIBLE) {
                    tvResult_four.setVisibility(View.GONE);
                }

            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_clear) {

            etLoanAmount.setText("");
            //tvLoanInterest_two.setText("");
            tvResult_two.setText("0.0 MMK");
            tvResult_four.setText("0.0 MMK");

            return true;

        }
        return false;

    }
}
