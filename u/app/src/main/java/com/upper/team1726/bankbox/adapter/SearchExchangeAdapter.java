package com.upper.team1726.bankbox.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.activity.TypeMapActivity;
import com.upper.team1726.bankbox.model.Exchange;

import java.util.List;


/**
 * Created by acer on 10/23/2017.
 */
public class SearchExchangeAdapter extends RecyclerView.Adapter<SearchExchangeAdapter.MyViewHolder> {


    private List<Exchange> exchangeList;

    public SearchExchangeAdapter(List<Exchange> exchangeList) {

        this.exchangeList = exchangeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card_view, parent, false);
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
        public ImageView map;
        private Exchange exchange;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            bankName = (TextView) view.findViewById(R.id.tvBankName);
            exchangeAddress = (TextView) view.findViewById(R.id.tvAddress);
            exchangeName = (TextView) view.findViewById(R.id.tvName);
            exchangeTime = (TextView) view.findViewById(R.id.tvInfo);
            map = (ImageView) view.findViewById(R.id.map);


            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double lat = exchange.getExchangeLatitude();
                    double log = exchange.getExchangeLongitude();
                    String name = exchange.getExchangeName();
//                    Uri uri=Uri.parse("geo:"+lat+","+log);

                    Log.e("Good", "true");
                    Intent i = new Intent(mContext, TypeMapActivity.class);
                    i.putExtra("Lat", lat);
                    i.putExtra("Log", log);
                    i.putExtra("Name", name);
                    mContext.startActivity(i);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(mContext, "Click on " + exchange.getExchangeName(), Toast.LENGTH_SHORT).show();
                }
            });

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
