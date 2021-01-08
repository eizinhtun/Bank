package com.upper.team1726.bankbox.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.CentralCurrency;
import com.upper.team1726.bankbox.model.Currency;
import com.upper.team1726.bankbox.model.CurrencyClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

;

/**
 * Created by MICRO on 10/25/2017.
 */

public class CentralFragment extends Fragment {
    Context mContext;
    BankDBHandler db;

    int[] CurrencyLogo = {R.drawable.usd, R.drawable.eur, R.drawable.sgd, R.drawable.thb, R.drawable.mlr};

    ArrayList<Currency> centralArrayList;


    TextView tvDate;
    TableLayout tbCentral;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean refresh = false;

    Currency currency;

    ProgressDialog progressDialog;
    CentralCurrency centralCurrency;
    CurrencyClient client;

    //    Date dd = new Date();
//    CharSequence c = DateFormat.format("dd/MM/yyyy ", dd.getTime());
//    String ss = (String) c;
//    String s = String.valueOf(ss);
    String date = "00/00/0000";

    private RecyclerView recyclerView;

    public CentralFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("fragment ", ": Central");


//        Log.e("Count Central"," "+db.getCentralCount());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_central, container, false);
        db = new BankDBHandler(getContext());


        tbCentral = (TableLayout) view.findViewById(R.id.tbCentral);
        tvDate = (TextView) view.findViewById(R.id.tvDateCentral);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);


        if (db.isCentralTableEmpty()) {


            downloadAndShowData();

        } else {

            date = db.getCentralLastDate();
            showData(db.getCentralData(date));
        }


        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        // This method performs the actual data-refresh operation.
                        refreshCurrency();
                    }
                }
        );


        return view;
    }

    public void downloadAndShowData() {


        String API_BASE_URL = "http://forex.cbm.gov.mm/api/";

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(CurrencyClient.class);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);

        progressDialog.show();

        // Execute the call asynchronously. Get a positive or negative callback.

        client.loadCurrencies().enqueue(new Callback<CentralCurrency>() {
            @Override
            public void onResponse(Call<CentralCurrency> call, Response<CentralCurrency> response) {

                //Toast.makeText(MainActivity.this, "This is inside on Response :" + response.code(), Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()) {

                    centralCurrency = response.body();
                    //Toast.makeText(MainActivity.this, "RESPONSE SIZE : " + currencyList.getInfo()), Toast.LENGTH_SHORT).show();
                    Log.i("RESPONSE ", centralCurrency.getRates().getUSD());
                    insertCentralCurrency();

                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CentralCurrency> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please turn on the internet and refresh", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        Log.e("Statement 1", "true");
    }


    public void insertCentralCurrency() {

//        Date date = new Date((long)centralCurrency.getTimestamp()*1000);
//        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        //System.out.println(f.format(date));


        // String newDate = new java.text.SimpleDateFormat("dd/MM/yyyy (HH:mm:ss)").format(new java.util.Date(centralCurrency.getTimestamp() * 1000));
        // String newDate="17/11/2017";

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy (hh:mm a)");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Myanmar"));
//
//        String newDate=simpleDateFormat.format( new java.util.Date((centralCurrency.getTimestamp()) * 1000L));

        //String newDate=getDateCurrentTimeZone(centralCurrency.getTimestamp());
        //simpleDateFormat.parse(date);(new java.util.Date((centralCurrency.getTimestamp()) * 1000L)).toString();

        String newDate = (DateFormat.format("dd/MM/yyyy (h:mm a)", new java.util.Date((centralCurrency.getTimestamp()) * 1000)).toString());
        //Toast.makeText(getActivity(), "new date is  "+ newDate, Toast.LENGTH_SHORT).show();
        if (!(date.equals(newDate))) {

            //  Toast.makeText(getActivity(), "inside not equal", Toast.LENGTH_SHORT).show();

            double rateUSD = Double.parseDouble(centralCurrency.getRates().getUSD().replace(",", ""));
            double rateEUR = Double.parseDouble(centralCurrency.getRates().getEUR().replace(",", ""));
            double rateSGD = Double.parseDouble(centralCurrency.getRates().getSGD().replace(",", ""));
            double rateTHB = Double.parseDouble(centralCurrency.getRates().getTHB().replace(",", ""));
            double rateMYR = Double.parseDouble(centralCurrency.getRates().getMYR().replace(",", ""));


            db.insertCentral(rateUSD, rateEUR, rateSGD, rateTHB, rateMYR, newDate);
            this.date = db.getCentralLastDate();

            if (refresh) {

                tbCentral.removeAllViews();
                refresh = false;

            }
            showData(db.getCentralData(date));//must delete the previous show
            //showData(db.getCentralData());

        }


    }


    public void showData(ArrayList<Currency> centralArrayList) {

        int leftMargin = 110;
        int topMargin = 400;
        int rightMargin = 100;
        int bottomMargin = 80;


        TableRow headtbrow = new TableRow(getActivity());

        headtbrow.setMinimumHeight(60);
        headtbrow.setBackgroundResource(R.color.headColor);
        TableRow.LayoutParams layoutParamsRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParamsRow.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        headtbrow.setLayoutParams(layoutParamsRow);

        headtbrow.setPadding(4, 4, 4, 4);

        TextView unitCurrency = new TextView(getActivity());
        TextView exchangeRate = new TextView(getActivity());

       // unitCurrency.setBackgroundResource(R.color.headColor);
        unitCurrency.setText("     Unit Currency");
        unitCurrency.setTextSize(16);
        unitCurrency.setTextColor(Color.WHITE);


       // exchangeRate.setBackgroundResource(R.color.headColor);
        exchangeRate.setText("Exchange Rate");
        exchangeRate.setTextSize(16);
        exchangeRate.setTextColor(Color.WHITE);
        exchangeRate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        headtbrow.addView(unitCurrency);
        headtbrow.addView(exchangeRate);

        TableRow.LayoutParams paramsU = (TableRow.LayoutParams) unitCurrency.getLayoutParams();
        paramsU.span = 2;
        unitCurrency.setLayoutParams(paramsU);

        tbCentral.addView(headtbrow);

        TableRow padd= new TableRow(getActivity());
        padd.setLayoutParams(layoutParamsRow);
        padd.setMinimumHeight(50);
        tbCentral.addView(padd);

        this.centralArrayList = centralArrayList;//db.getCentralData(date);

        for (int i = 0; i < centralArrayList.size(); i++) {
            currency = new Currency();
            currency = centralArrayList.get(i);
            TableRow tbrow = new TableRow(getActivity());


            tbrow.setMinimumHeight(100);

            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);

            layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            tbrow.setLayoutParams(layoutParams);

            tbrow.setPadding(6, 6, 6, 6);

            ImageView imgView = new ImageView(getActivity());
            TextView name = new TextView(getActivity());
            TextView rate = new TextView(getActivity());
            // TextView sell = new TextView(getActivity());

            int logo_id = currency.getCurrency_id();


            if (logo_id > 5) {
                imgView.setImageResource(CurrencyLogo[(logo_id - 1) % 5]);

            } else {
                imgView.setImageResource(CurrencyLogo[logo_id - 1]);
            }


            String currencyName = currency.getCurrency_type();

            name.setText(currencyName);
            name.setTextColor(Color.BLACK);
            name.setGravity(Gravity.CENTER_VERTICAL);


            rate.setText(String.valueOf(currency.getRate()));
            rate.setTextColor(Color.BLACK);
            rate.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);


            tbrow.addView(imgView);
            tbrow.addView(name);
            tbrow.addView(rate);

            tbCentral.addView(tbrow);

            tvDate.setText(date);
            tvDate.setTextSize(12);
            tvDate.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);

        }

    }


    public void refreshCurrency() {

        refresh = true;
        this.date = db.getCentralLastDate();
        //Toast.makeText(getActivity(), "in refresh "+ db.getCentralLastDate(), Toast.LENGTH_SHORT).show();

        downloadAndShowData();

        swipeRefreshLayout.setRefreshing(false);

    }


}








