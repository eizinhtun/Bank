package com.upper.team1726.bankbox.activity;

import android.content.DialogInterface;
import android.graphics.Color;
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


public class InterestActivity extends AppCompatActivity {
    Spinner spInterestBank, spAccountType, spDuration;
    TextView closingBalance, interest, tvRate;
    EditText etInterestAmount, etDuration;
    Button btnInterestCalculate;
    LinearLayout spLayout;
    double rate;
    int account_id = 1;
    double totalInterest = 0, interest_three = 0, total = 0;
    ArrayList<String> bankNameArrayList = new <String>ArrayList();
    ArrayList<String> accountArrayList = new <String>ArrayList();
    ArrayList<Double> interestRate = new <String>ArrayList();
    ArrayList<String> suPwarArrayList = new ArrayList<String>();
    ArrayList<String> fixedArrayList = new ArrayList<String>();
    ArrayList<String> wedArrayList = new ArrayList<String>();
    ArrayAdapter<String> accountAdapter;
    ArrayAdapter<String> bankAdapter;
    ArrayAdapter<String> durationAdaptor;
    BankDBHandler db;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interest);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new BankDBHandler(this);

//        suPwarArrayList.add(0,"1 year");
//        suPwarArrayList.add(1,"2 years");
//        suPwarArrayList.add(2,"3 years");

//        fixedArrayList.clear();
//        fixedArrayList.add(0,"1 month");
//        fixedArrayList.add(1,"3 months");
//        fixedArrayList.add(2,"6 months");
//        fixedArrayList.add(3,"9 months");
//        fixedArrayList.add(4,"12 months");
//        Log.e("0 ","is" +fixedArrayList.get(0));
//        Log.e("1 ","is" +fixedArrayList.get(1));
//        Log.e("2 ","is" +fixedArrayList.get(2));
//        Log.e("3","is" +fixedArrayList.get(3));
//        Log.e("4 ","is" +fixedArrayList.get(4));


        spInterestBank = (Spinner) findViewById(R.id.spInterestBank);
        spAccountType = (Spinner) findViewById(R.id.spAccountType);
        spLayout = (LinearLayout) findViewById(R.id.spLayout);

        etInterestAmount = (EditText) findViewById(R.id.etInterestAmount);
        etDuration = (EditText) findViewById(R.id.etDuration);
        etDuration.setHint("Number of days");

        interest = (TextView) findViewById(R.id.tvInterest);
        closingBalance = (TextView) findViewById(R.id.tvCloseBalance);
        tvRate = (TextView) findViewById(R.id.tvRate);

        btnInterestCalculate = (Button) (findViewById(R.id.btnInterestCalculate));
        spDuration = new Spinner(InterestActivity.this);


        bankNameArrayList = db.getAllBankNameInterest();
        bankAdapter = new ArrayAdapter<>(InterestActivity.this, android.R.layout.simple_dropdown_item_1line, bankNameArrayList);
        spInterestBank.setAdapter(bankAdapter);


        //bankNameArrayList.get(0)
        accountArrayList = db.getAllAccountName(db.getBankID("KBZ"));
        accountAdapter = new ArrayAdapter<>(InterestActivity.this, android.R.layout.simple_dropdown_item_1line, accountArrayList);
        spAccountType.setAdapter(accountAdapter);

        //durationArrayList = fixedArrayList;
        durationAdaptor = new ArrayAdapter<>(InterestActivity.this, android.R.layout.simple_dropdown_item_1line, fixedArrayList);
        //spDuration.setAdapter(durationAdaptor);


        //for bank spinner
        spInterestBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                changeAccountArrayContent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        spAccountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (changeDurationContent().equals("call deposit") || changeDurationContent().equals("saving") ||
                        changeDurationContent().equals("esaving") || changeDurationContent().equals("smart saving") ||
                        changeDurationContent().equals("maximize") || changeDurationContent().equals("loyal")) {
                    Log.e("CHANGE_DURATION", "" + changeDurationContent());

                    if (changeDurationContent().equals("call deposit")) {
                        etDuration.setHint("Number of days");
                        etDuration.setHintTextColor(Color.GRAY);
                    } else {
                        etDuration.setHint("Number of months");
                        etDuration.setHintTextColor(Color.GRAY);
                    }

//                    spLayout.removeView(etDuration);
//                    spLayout.addView(etDuration);

//                } else {
//                    etDuration.setHint("number of months");
//                    etDuration.setHintTextColor(Color.TRANSPARENT);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        //add button Listener
        btnInterestCalculate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                int acc_id = db.getAccountID(spAccountType.getSelectedItem().toString(), db.getBankID(spInterestBank.getSelectedItem().toString()));


//                if (etDuration.getText().toString().equals("") || etInterestAmount.getText().toString().equals("")) {
//                    Log.e("empty", "true");
//
//                        if (db.getAccountType(acc_id).equals("fixed") || db.getAccountType(acc_id).equals("wedding") || db.getAccountType(acc_id).equals("supwar")) {
//                            if (TextUtils.isEmpty(etInterestAmount.getText().toString())) {
//                                etInterestAmount.setError("Your Amount is Empty !");
//                                showDialog("Amount  is Empty !");
//                            }
//                        }
//
//
//                        else {

//                                if (etInterestAmount.getText().toString().equals("") && etDuration.getText().toString().equals("")) {
//                                    etInterestAmount.setError("Your Amount is Empty !");
//                                    etDuration.setError("Your Duration is Empty");
//                                    showDialog("Amount  is Empty !" + "\n Duration is Empty");
//                                } else
                if (TextUtils.isEmpty(etInterestAmount.getText().toString())) {
                    etInterestAmount.setError("Your Amount is Empty !");
                    showDialog(1, "Amount  is Empty !");


                }
//                                } else {
//                                    etInterestAmount.setError("Your Duration is Empty !");
//                                    showDialog("Duration is Empty !");
//                                }
//
//                            }
//                        }
//
//

                else {
                    double amount = Double.parseDouble(etInterestAmount.getText().toString());

//                    DecimalFormat formatter = new DecimalFormat("#,###");
//                  String formatted = formatter.format(amount_one);
//                  etInterestAmount.setText(formatted);
//                 double amount =Double.parseDouble(etInterestAmount.getText().toString().replace(",",""));

                    Log.e("account_id", " is " + acc_id);
                    Log.e("amount", " is " + amount);
                    Log.e("account type", " is " + spAccountType.getSelectedItem().toString());
                    Log.e("bank_id", " is " + db.getBankID(spInterestBank.getSelectedItem().toString()));

                    if (db.checkAmount(acc_id, amount).equals("true")) {
                        Log.e("mainCHeck", "aa");

                        double initial = amount;
//                    int month_or_day = Integer.parseInt(etDuration.getText().toString());
//                    Log.e("month","month is "+month_or_day);
//                    doubl
//
// e interest_rate = Double.parseDouble(tvRate.getText().toString());
//                    Log.e("interest rate ","is "+interest_rate);

                        double interest_rate = 0;
                        switch (db.getAccountType(acc_id)) {
                            case "saving":
                                total = 0;

                                totalInterest = 0;
                                etDuration.setHint("Number of months");
                                etDuration.setHintTextColor(Color.TRANSPARENT);
                                if (TextUtils.isEmpty(etDuration.getText().toString())) {
                                    etDuration.setError("Duration is Empty !");
                                } else {
                                    int month = Integer.parseInt(etDuration.getText().toString());

                                    Log.e("month", "month is " + month);

                                    if (checkDuration(month, 1200).equals("true")) {

                                        String interestFirst = null;
                                        if (tvRate.getText().toString() != null) {
                                            interestFirst = tvRate.getText().toString().replace(" %", "");
                                        }
                                        interest_rate = Double.parseDouble(interestFirst);
                                        Log.e("interest rate ", "is " + interest_rate);

                                        //interest_three = 0;
                                        int i;
                                        double allInterest = 0;


                                        if (month > 3) {
                                            int monthDiv = month / 3;
                                            int monthRest = month % 3;

                                            double allThreeInterest = 0;
                                            // interest_three=0;

                                            for (int k = 0; k < monthDiv; k++) {

                                                interest_three = 0;
                                                interest_three += 3 * amount * interestRate.get(0) / 1200;
                                                amount += interest_three;
                                                allInterest += interest_three;


                                            }

                                            if (monthRest > 0) {
                                                double bonusInterest = amount * monthRest * interestRate.get(0) / 1200;
                                                total = amount + bonusInterest;
                                                totalInterest = total - initial;
                                            } else {

                                                total = amount + allInterest;
                                                totalInterest = total - initial;
                                            }


                                        } else {
                                            allInterest = amount * month * interestRate.get(0) / 1200;

                                            total = amount + allInterest;
                                            totalInterest = total - initial;
                                        }

//                                        for (i = 1; i <= month; i++) {
//                                            interest_three = amount * interestRate.get(0) / 1200;
//                                            allInterest += interest_three;
//                                            if (i % 3 == 0 && i / 3 > 1) {
//                                                amount += 3 * interest_three;
//                                                interest_three = 0;
//                                            }
//
//                                        }
//                                        total = amount + allInterest;
//                                        totalInterest = allInterest;

                                    } else {
                                        showDialog(2, checkDuration(month, 1200));
                                    }

                                }
                                break;


                            case "call deposit":
                                Log.e("call deposit", " is ");
                                total = 0;
                                totalInterest = 0;
                                etDuration.setHint("Number of days");
                                etDuration.setHintTextColor(Color.TRANSPARENT);
                                if (TextUtils.isEmpty(etDuration.getText().toString())) {
                                    etDuration.setError("Duration is Empty !");
                                } else {
                                    int day = Integer.parseInt(etDuration.getText().toString());
                                    Log.e("month", "month is " + day);

                                    if (checkDuration(day, 36500).equals("true")) {


                                        String interestFirst = null;
                                        if (tvRate.getText().toString() != null) {
                                            interestFirst = tvRate.getText().toString().replace(" %", "");
                                        }
                                        interest_rate = Double.parseDouble(interestFirst);

                                        Log.e("interest rate ", "is " + interest_rate);

                                        if (day > 91) {

                                            int dayDiv = day / 91;
                                            int dayRest = day % 91;
                                            for (int h = 0; h < dayDiv; h++) {

                                                interest_three = 0;
                                                totalInterest = amount * interest_rate * 91 / 36500;
                                                amount += interest_three;
                                            }


                                            if (dayRest > 0) {
                                                double bonusInterest = amount * interest_rate * dayRest / 36500;
                                                total = amount + bonusInterest;
                                                totalInterest = total - initial;
                                            } else {

                                                total = amount;
                                                totalInterest = total - initial;
                                            }

                                        } else {

                                            totalInterest = amount * interest_rate * day / 36500;
                                            total = amount + totalInterest;
                                        }
                                    } else {
                                        showDialog(2, checkDuration(day, 36500));
                                    }

                                }
                                break;

                            case "fixed":
                                total = 0;
                                totalInterest = 0;
                                //spLayout.removeAllViewsInLayout();
                                // spLayout.removeView(etDuration);

//                            durationAdaptor.clear();
//                            durationAdaptor.addAll(fixedArrayList);
//                            durationAdaptor.notifyDataSetChanged();
//                            spDuration.setAdapter(durationAdaptor);
//                            Log.e("f1","1"+fixedArrayList.get(0));
//                            Log.e("f1","1"+fixedArrayList.get(2));
//                            Log.e("f1","1"+fixedArrayList.get(3));
//                            Log.e("f1","1"+fixedArrayList.get(4));
//                            Log.e("f1","1"+fixedArrayList.get(5));

                                //etDuration.setHint("number of days");
                                //etDuration.setHintTextColor(Color.TRANSPARENT)
                                int durationMonth = 0;
                                switch (spDuration.getSelectedItemPosition()) {
                                    case 0:
                                        durationMonth = 1;
                                        break;
                                    case 1:
                                        durationMonth = 3;
                                        break;
                                    case 2:
                                        durationMonth = 6;
                                        break;
                                    case 3:
                                        durationMonth = 9;
                                        break;
                                    case 4:
                                        durationMonth = 12;
                                        break;
                                }
                                int a = durationMonth / 3;
                                totalInterest = amount * interestRate.get(a) * durationMonth / 1200;
                                total = totalInterest + amount;
                                break;

                            case "smart saving":
                                total = 0;
                                totalInterest = 0;
                                etDuration.setHint(null);
                                etDuration.setHint("Number of months");
                                etDuration.setHintTextColor(Color.TRANSPARENT);

                                if (TextUtils.isEmpty(etDuration.getText().toString())) {
                                    etDuration.setError("Duration is Empty !");
                                } else {
                                    int smart_month = Integer.parseInt(etDuration.getText().toString());
                                    if (checkDuration(smart_month, 1200).equals("true")) {

                                        if (amount < 10000000) {
                                            tvRate.setText("8.25 %");
                                            totalInterest = amount * 8.25 * smart_month / 1200;
                                        } else if (amount >= 10000000 && amount <= 50000000) {
                                            tvRate.setText("8.35 %");
                                            totalInterest = amount * 8.35 * smart_month / 1200;
                                        } else {
                                            tvRate.setText("8.5 %");
                                            totalInterest = amount * 8.5 * smart_month / 1200;
                                        }

                                        total = totalInterest + amount;
                                    } else {
                                        showDialog(2, checkDuration(smart_month, 1200));
                                    }
                                }
                                break;

                            case "esaving":
                                total = 0;
                                totalInterest = 0;
                                etDuration.setHint("Number of months");
                                etDuration.setHintTextColor(Color.TRANSPARENT);
                                if (TextUtils.isEmpty(etDuration.getText().toString())) {
                                    etDuration.setError("Duration is Empty !");
                                } else {

                                    int esaving_month = Integer.parseInt(etDuration.getText().toString());
                                    if (checkDuration(esaving_month, 1200).equals("true")) {
                                        if (amount > 500000000) {
                                            tvRate.setText("8.5 %");
                                            total += amount * 8.5 / 1200;
                                        } else {
                                            tvRate.setText("8.25 %");
                                            total += amount * 8.25 / 1200;
                                        }
                                        totalInterest = total - amount;
                                    } else {
                                        showDialog(2, checkDuration(esaving_month, 1200));
                                    }

                                }
                                break;

                            case "wedding":
                                total = 0;
                                totalInterest = 0;
                                total += amount * interestRate.get(0);
                                double bonus = 0;
                                if (amount >= 5000000) {
                                    bonus = 100000;
                                } else if (amount >= 4000000) {
                                    bonus = 80000;
                                } else if (amount >= 3000000) {
                                    bonus = 60000;
                                } else if (amount < 2000000) {
                                    bonus = 40000;
                                } else {
                                    bonus = 20000;
                                }
                                total += bonus;
                                totalInterest = total - amount;
                                break;

                            case "supwar":
                                int year = 0;
                                switch (spDuration.getSelectedItemPosition()) {
                                    case 0:
                                        year = 1;
                                        break;
                                    case 1:
                                        year = 2;
                                        break;
                                    case 2:
                                        year = 3;
                                        break;
                                }

                                total = amount;
                                //total=amount;
                                double calInterest = 0;
                                double origin = amount;
                                double totalcalInterest = 0;

                                for (int l = 0; l < year * 12; l++) {
                                    calInterest = total * interestRate.get(year - 1) / 1200;
                                    //total+=calInterest+amount;
                                    totalcalInterest += calInterest;

                                    if (l == year * 12 - 1) {
                                        total = totalcalInterest + origin * (l + 1);
                                    } else {
                                        total = totalcalInterest + origin * (l + 2);
                                    }
                                }

                                totalInterest = total - amount * 12 * year;
//                                totalInterest = amount * interestRate.get(year - 1) * year;
//                                total = totalInterest + amount;
                                break;

                        }


                        String totalStr = NumberFormat.getInstance().format(total);
                        String totalInterestStr = NumberFormat.getInstance().format(totalInterest);

                        closingBalance.setText(totalStr + " MMK");
                        interest.setText(totalInterestStr + " MMK");
//                        etInterestAmount.setBackgroundColor(Color.LTGRAY);
//                        etDuration.setBackgroundColor(Color.LTGRAY);
                    } else {
                        showDialog(1, db.checkAmount(acc_id, amount));
                    }
//                    String totalStr = NumberFormat.getInstance().format(total);
//                    String totalInterestStr = NumberFormat.getInstance().format(totalInterest);
//
//                    closingBalance.setText(totalStr + " MMK");
//                    interest.setText(totalInterestStr + " MMK");
                }


            }
        });


    }


    //to change Account Spinner Content
    public void changeAccountArrayContent() {
        db = new BankDBHandler(this);
        int bank_id = db.getBankID(spInterestBank.getSelectedItem().toString());
        Log.e("sp name", " = " + spInterestBank.getSelectedItem().toString());
        Log.e("other", "=" + bank_id);

        accountArrayList.clear();
        //accountArrayList = db.getAllAccountName(bank_id);
        Log.e("other bank name", "=" + db.getAllAccountName(bank_id));
        accountArrayList.addAll(db.getAllAccountName(bank_id));


        //My latest
        account_id = db.getAccountID((spAccountType.getItemAtPosition(0)).toString(), db.getBankID(spInterestBank.getSelectedItem().toString()));

        if (db.getAccountType(account_id).equals("call deposit")) {
            etDuration.setHint("Number of days");
        } else {
            etDuration.setHint("Number of months");
        }


        Log.e("TESST ACC", "" + account_id);

        interestRate = db.getInterestRate(account_id);

        Log.e("TESST Interest RATe", "" + interestRate);

        tvRate.setText(String.valueOf(interestRate.get(0)) + " %");


        //accountAdapter.clear();
        //accountAdapter.addAll(accountArrayList);
        accountAdapter.notifyDataSetChanged();
    }


    //to change Duration Type
    public String changeDurationContent() {

        BankDBHandler db = new BankDBHandler(this);

        // if(spAccountType.getSelectedItem().toString()==null && db.getBankID(spInterestBank.getSelectedItem().toString())==0){
        //account_id=1;
        //}interestRate
        account_id = db.getAccountID(spAccountType.getSelectedItem().toString(), db.getBankID(spInterestBank.getSelectedItem().toString()));
        Log.e("account_id", "acc" + account_id);
        interestRate = db.getInterestRate(account_id);
        tvRate.setText(String.valueOf(interestRate.get(0)) + " %");
        //interestRate = db.getInterestRate(account_id);

        // String account_type = db.getAccountType(account_id);
        String account_type = db.getAccountType(account_id).trim();


        if (account_type.equals("fixed") || account_type.equals("supwar") || account_type.equals("wedding")) {

            //((ViewGroup) etDuration.getParent()).removeView(etDuration);
            spLayout.removeAllViewsInLayout();
            //spLayout.removeView(etDuration);
            spDuration = new Spinner(this);

            //durationAdaptor = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_dropdown_item_1line,fixedArrayList);
            //spDuration.setAdapter( new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_dropdown_item_1line,fixedArrayList));


            if (interestRate.size() == 3) {
                if (suPwarArrayList.size() != 0) {
                    suPwarArrayList.clear();
                }

                suPwarArrayList.add(0, "1 year");
                suPwarArrayList.add(1, "2 years");
                suPwarArrayList.add(2, "3 years");
                //durationAdaptor.clear();
                durationAdaptor = new ArrayAdapter<String>(InterestActivity.this, android.R.layout.simple_dropdown_item_1line, suPwarArrayList);
                durationAdaptor.notifyDataSetChanged();
            } else if (interestRate.size() == 5) {
                //durationAdaptor.clear();

                if (fixedArrayList.size() != 0) {
                    fixedArrayList.clear();
                }

                fixedArrayList.add(0, "1 month");
                fixedArrayList.add(1, "3 months");
                fixedArrayList.add(2, "6 months");
                fixedArrayList.add(3, "9 months");
                fixedArrayList.add(4, "12 months");
                durationAdaptor = new ArrayAdapter<String>(InterestActivity.this, android.R.layout.simple_dropdown_item_1line, fixedArrayList);
                //durationAdaptor.addAll(fixedArrayList);
                // spDuration.setAdapter(durationAdaptor);
//                Log.e("fixed size"," "+fixedArrayList.size());
//                Log.e("f1","1"+fixedArrayList.get(1));
//                Log.e("f1","1"+fixedArrayList.get(2));
//                Log.e("f1","1"+fixedArrayList.get(3));
//                Log.e("f1","1"+fixedArrayList.get(4));
                durationAdaptor.notifyDataSetChanged();
            } else {
                wedArrayList.clear();
                wedArrayList.add(0, "one year");
                //durationAdaptor.clear();
                durationAdaptor = new ArrayAdapter<String>(InterestActivity.this, android.R.layout.simple_dropdown_item_1line, wedArrayList);
                durationAdaptor.notifyDataSetChanged();
            }

            spDuration.setAdapter(durationAdaptor);
            spLayout.addView(spDuration);

            spDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    int selected = spDuration.getSelectedItemPosition();
                    rate = interestRate.get(selected);
                    tvRate.setText(String.valueOf(rate) + " %");

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });
            //spLayout.addView(spDuration);
        } else {
            spLayout.removeAllViewsInLayout();
            //spLayout.addView(etDuration);// MYYY

            if (account_type.equals("call deposit")) {
                //spLayout.removeAllViewsInLayout();
                Log.e("BBBB", "" + account_type.equals("call deposit"));
                etDuration.setHint("Number of days");
                etDuration.setHintTextColor(Color.LTGRAY);
                etDuration.setInputType(2);

                spLayout.addView(etDuration);
            } else {
                Log.e("SSSS", "" + account_type);
                // spLayout.removeAllViewsInLayout();
                etDuration.setHint("Number of months");
                etDuration.setHintTextColor(Color.LTGRAY);
                // spLayout.removeAllViewsInLayout();
                etDuration.setInputType(2);

                spLayout.addView(etDuration);
            }
            rate = interestRate.get(0);
            Log.e("Interest ", "=" + rate);
        }
        tvRate.setText(String.valueOf(rate) + " %");

        return account_type;
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

            etInterestAmount.setText("");
            //etInterestAmount.setBackgroundColor(Color.TRANSPARENT);
            etDuration.setText("");
            // etDuration.setBackgroundColor(Color.LTGRAY);


            if (changeDurationContent().equals("call deposit") || changeDurationContent().equals("saving")) {
                Log.e("CHANGE_DURATION", "" + changeDurationContent());

                if (changeDurationContent().equals("call deposit")) {
                    etDuration.setHint("Number of days");
                    etDuration.setHintTextColor(Color.GRAY);
                } else {
                    etDuration.setHint("Number of months");
                    etDuration.setHintTextColor(Color.GRAY);

                }

//                    spLayout.removeView(etDuration);
//                    spLayout.addView(etDuration);

//                } else {
//                    etDuration.setHint("number of months");
//                    etDuration.setHintTextColor(Color.TRANSPARENT);


            }


//            etDuration.setHint("number of days");
//            etDuration.setHint("number of months");
            interest.setText("0 MMK");
            closingBalance.setText("0 MMK");

            return true;
        }

        // return super.onOptionsItemSelected(item);


        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    //etDuration.getText().toString()
    public String checkDuration(int duration, int limit) {
        String err_duration = " ";
        if (duration >= limit) {
            err_duration = "Duration is beyond limit";
        } else {
            err_duration = "true";
        }
        return err_duration;
    }

    public void showDialog(final int a, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert")
                .setMessage(msg)
                //.setIcon()
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (a == 1) {
                            etInterestAmount.setText("");
                            // etInterestAmount.setBackgroundColor(Color.GRAY);
                        } else {

                            etDuration.setText("");
                            //etDuration.setBackgroundColor(Color.GRAY);
                        }

                        dialog.dismiss();
                    }
                });
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                });
        builder.show();

    }


}