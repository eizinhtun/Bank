package com.upper.team1726.bankbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.model.Exchange;

import java.util.List;


/**
 * Created by acer on 10/23/2017.
 */
public class ExchangeMapAdapter extends RecyclerView.Adapter<ExchangeMapAdapter.MyViewHolder> {


    private List<Exchange> exchangeList;

    public ExchangeMapAdapter(List<Exchange> exchangeList) {

        this.exchangeList = exchangeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_card_view, parent, false);
        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Exchange exchange = exchangeList.get(position);
        holder.bindData(exchange);

    }

    @Override
    public int getItemCount() {
        return exchangeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView bankName, exchangeAddress, exchangeName, exchangeTime;
        public Context mContext;
        private Exchange exchange;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            bankName = (TextView) view.findViewById(R.id.tvBankName);
            exchangeAddress = (TextView) view.findViewById(R.id.tvAddress);
            exchangeName = (TextView) view.findViewById(R.id.tvName);
            exchangeTime = (TextView) view.findViewById(R.id.tvInfo);

        }

        public void bindData(Exchange mExchange) {
            this.exchange = mExchange;

            bankName.setText(exchange.getBankName()+" Bank");
            exchangeName.setText(exchange.getExchangeName());
            exchangeAddress.setText(exchange.getExchangeAddress());
            exchangeTime.setText(exchange.getExchangeOpenTime());

        }
    }
}
