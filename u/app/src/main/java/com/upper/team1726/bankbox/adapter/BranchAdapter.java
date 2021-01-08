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
import com.upper.team1726.bankbox.model.Branch;

import java.util.List;


public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.MyViewHolder> {


    private List<Branch> branchList;

    public BranchAdapter(List<Branch> branchList) {

        this.branchList = branchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Branch branch = branchList.get(position);
        holder.bindData(branch);

    }

    @Override
    public int getItemCount() {
        return branchList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView bankName, branchAddress, branchName, branchPhno;
        public ImageView map;
        public Context mContext;
        private Branch branch;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            bankName = (TextView) view.findViewById(R.id.tvBankName);
            branchAddress = (TextView) view.findViewById(R.id.tvAddress);
            branchName = (TextView) view.findViewById(R.id.tvName);
            branchPhno = (TextView) view.findViewById(R.id.tvInfo);
            map = (ImageView) view.findViewById(R.id.map);


            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double lat = branch.getBranchLatitude();
                    double log = branch.getBranchLongitude();
                    String name = branch.getBranchName();
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
                    // Toast.makeText(mContext, "Click on " + branch.getBranchName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindData(Branch mBranch) {
            this.branch = mBranch;
            bankName.setText(branch.getBankName());//bankName.setText(dbObj.getBankName(branch.getBankId()));
            branchName.setText(branch.getBranchName());
            branchAddress.setText(branch.getBranchAddress());
            branchPhno.setText(branch.getBranchPhNo());
        }
    }

}

