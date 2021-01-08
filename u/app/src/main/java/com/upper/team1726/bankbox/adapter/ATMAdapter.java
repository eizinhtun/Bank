package com.upper.team1726.bankbox.adapter;

/**
 * Created by acer on 10/23/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.activity.TypeMapActivity;
import com.upper.team1726.bankbox.model.ATM;

import java.util.List;


public class ATMAdapter extends RecyclerView.Adapter<ATMAdapter.MyViewHolder> {


    private List<ATM> atmList;

    public ATMAdapter(List<ATM> atmList) {

        this.atmList = atmList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
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
        public ImageView map;
        private ATM atm;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            bankName = (TextView) view.findViewById(R.id.tvBankName);
            atmName = (TextView) view.findViewById(R.id.tvName);
            atmAddress = (TextView) view.findViewById(R.id.tvAddress);
            atmOpenTime = (TextView) view.findViewById(R.id.tvInfo);
            map = (ImageView) view.findViewById(R.id.map);


            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double lat = atm.getAtmLatitude();
                    double log = atm.getAtmLongitude();
                    String name = atm.getAtmName();
//                    Uri uri=Uri.parse("geo:"+lat+","+log);

                    Log.e("Good", "true");
                    Intent i = new Intent(mContext, TypeMapActivity.class);
                    i.putExtra("Lat", lat);
                    i.putExtra("Log", log);
                    i.putExtra("Name", name);
                    mContext.startActivity(i);
                    // mContext.startActivity(new Intent(Intent.ACTION_VIEW,uri));

                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(mContext, "Click on "+atm.getAtmName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindData(ATM mAtm) {
            this.atm = mAtm;
            bankName.setText(atm.getBankName());//bankName.setText(dbObj.getBankName(atm.getBankId()));
            atmName.setText(atm.getAtmName());
            atmAddress.setText(atm.getAtmAddress());
            // atmOpenTime.setText(atm.getAtmOpenTime());

        }
    }

}



