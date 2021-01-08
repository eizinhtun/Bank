package com.upper.team1726.bankbox.adapter;

/**
 * Created by acer on 10/22/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.activity.SpecificBankActivity;
import com.upper.team1726.bankbox.model.Bank;

import java.util.List;


public class BankAdapter extends RecyclerView.Adapter<BankAdapter.MyViewHolder> {

    private Context mContext;
    private List<Bank> bankList;

    public BankAdapter(Context mContext, List<Bank> bankList) {
        this.mContext = mContext;
        this.bankList = bankList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bank bank = bankList.get(position);

        holder.bindView(bank);
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgvBank, imgvGo;
        public TextView tvBank;
        private Bank bank;

        public MyViewHolder(View view) {
            super(view);
            tvBank = (TextView) view.findViewById(R.id.tvBank);
            imgvBank = (ImageView) view.findViewById(R.id.imgvBank);
            imgvGo = (ImageView) view.findViewById(R.id.imgvGo);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SpecificBankActivity.class);
                    intent.putExtra("BANK_ID", bank.getBankId());
                    mContext.startActivity(intent);
                }
            });
        }

        public void bindView(Bank bank) {


            this.bank = bank;

            tvBank.setText(bank.getName().toString());
            tvBank.setTextColor(Color.parseColor(bank.getColor().toString()));
            switch (bank.getImageURL()) {
                case 1:
                    imgvBank.setImageResource(R.drawable.kbz);
                    break;
                case 5:
                    imgvBank.setImageResource(R.drawable.yoma);
                    break;
                case 2:
                    imgvBank.setImageResource(R.drawable.agd);
                    break;
                case 4:
                    imgvBank.setImageResource(R.drawable.cb);
                    break;
                case 3:
                    imgvBank.setImageResource(R.drawable.aya);
                    break;
            }

        }
    }
}
