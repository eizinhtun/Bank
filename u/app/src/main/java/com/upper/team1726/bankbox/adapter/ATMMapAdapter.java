package com.upper.team1726.bankbox.adapter;

/**
 * Created by acer on 10/23/2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.model.ATM;

import java.util.List;


public class ATMMapAdapter extends RecyclerView.Adapter<ATMMapAdapter.MyViewHolder> {


    private List<ATM> atmList;

    public ATMMapAdapter(List<ATM> atmList) {

        this.atmList = atmList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_card_view, parent, false);
        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ATM atm = atmList.get(position);
        holder.bindData(atm);

    }

    @Override
    public int getItemCount() {
        return atmList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView bankName, atmAddress, atmName, atmOpenTime;
        public Context mContext;
        private ATM atm;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            bankName = (TextView) view.findViewById(R.id.tvBankName);
            atmName = (TextView) view.findViewById(R.id.tvName);
            atmAddress = (TextView) view.findViewById(R.id.tvAddress);
            atmOpenTime = (TextView) view.findViewById(R.id.tvInfo);

        }

        public void bindData(ATM mAtm) {
            this.atm = mAtm;
            bankName.setText(atm.getBankName()+" Bank");//bankName.setText(dbObj.getBankName(atm.getBankId()));
            atmName.setText(atm.getAtmName());
            atmAddress.setText(atm.getAtmAddress());
            // atmOpenTime.setText(atm.getAtmOpenTime());

        }
    }

}



