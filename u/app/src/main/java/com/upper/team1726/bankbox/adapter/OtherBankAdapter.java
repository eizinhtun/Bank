package com.upper.team1726.bankbox.adapter;

/**
 * Created by MICRO on 10/24/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.model.Currency;

import java.util.ArrayList;


public class OtherBankAdapter extends RecyclerView.Adapter<OtherBankAdapter.MyViewHolder> {
    public ArrayList<ArrayList<Currency>> OtherBankDetailList;
    int[] CurrencyLogo = {R.drawable.usd, R.drawable.eur, R.drawable.sgd, R.drawable.thb, R.drawable.mlr};
    int[] BankLogo = {R.drawable.kbz, R.drawable.yoma, R.drawable.agd, R.drawable.meb, R.drawable.cb, R.drawable.aya};
    private ArrayList<Currency> OtherBankList;


    public OtherBankAdapter(ArrayList<ArrayList<Currency>> otherBankDetailArrayList) {
        this.OtherBankDetailList = otherBankDetailArrayList;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_card_view, parent, false);
        Log.e("ArrayList : ", "inside  onCreateViewHolder");
        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ArrayList<Currency> OtherBankDetail = OtherBankDetailList.get(position);
        holder.bindData(OtherBankDetail);

    }

    @Override
    public int getItemCount() {
        return OtherBankDetailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgLogo;
        public TextView tvName;
        public TableLayout tbCurrency;
        public Context mContext;
        Currency detail = new Currency();
        private ArrayList<Currency> OtherBank;
        private ArrayList<Currency> OtherBankList;
        private ArrayList<Currency> OtherBankDetail;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tbCurrency = (TableLayout) view.findViewById(R.id.tbCurrency);

        }


        public void bindData(ArrayList<Currency> mOtherBankList) {
            this.OtherBankList = mOtherBankList;
            Log.e("ArrayList : ", "inside Bind Data");


            for (int i = 0; i < OtherBankList.size(); i++) {

                tvName.setText(OtherBankList.get(i).getCurrency_type());
                tvName.setTextColor(Color.BLACK);

                imgLogo.setImageResource(CurrencyLogo[OtherBankList.get(i).getCurrency_id() - 1]);


                TableRow tbrow = new TableRow(mContext);
                tbrow.setMinimumHeight(100);

                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);

                int leftMargin = 40;
                int topMargin = 270;
                int rightMargin = 100;
                int bottomMargin = 80;
                layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
                tbrow.setLayoutParams(layoutParams);

                tbrow.setPadding(2, 2, 2, 2);

                Log.e("ArrayList : ", "inside ArrayList");
                ImageView imgView = new ImageView(mContext);
                TextView name = new TextView(mContext);
                TextView buy = new TextView((mContext));
                TextView sell = new TextView(mContext);

                imgView.setImageResource(BankLogo[OtherBankList.get(i).getBank_id() - 1]);

                Log.e("Currency Type:", " " + OtherBankList.get(i).getBank_name());
                name.setText(OtherBankList.get(i).getBank_name());

                name.setTextColor(Color.BLACK);
                name.setGravity(Gravity.CENTER_VERTICAL);


                buy.setText(String.valueOf(OtherBankList.get(i).getBuy()));
                buy.setTextColor(Color.BLACK);
                buy.setGravity(Gravity.CENTER_VERTICAL);

                sell.setText(String.valueOf(OtherBankList.get(i).getSell()));
                sell.setTextColor(Color.BLACK);
                sell.setGravity(Gravity.CENTER_VERTICAL);

                tbrow.addView(imgView);
                tbrow.addView(name);
                tbrow.addView(buy);
                tbrow.addView(sell);

                tbCurrency.addView(tbrow);
            }


        }

    }
}


