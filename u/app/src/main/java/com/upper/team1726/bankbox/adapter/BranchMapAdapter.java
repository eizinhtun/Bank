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
import com.upper.team1726.bankbox.model.Branch;

import java.util.List;


public class BranchMapAdapter extends RecyclerView.Adapter<BranchMapAdapter.MyViewHolder> {


    private List<Branch> branchList;

    public BranchMapAdapter(List<Branch> branchList) {

        this.branchList = branchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_card_view, parent, false);

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
        public Context mContext;
        private Branch branch;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            bankName = (TextView) view.findViewById(R.id.tvBankName);
            branchAddress = (TextView) view.findViewById(R.id.tvAddress);
            branchName = (TextView) view.findViewById(R.id.tvName);
            branchPhno = (TextView) view.findViewById(R.id.tvInfo);

        }

        public void bindData(Branch mBranch) {
            this.branch = mBranch;
            bankName.setText(branch.getBankName()+" Bank");//bankName.setText(dbObj.getBankName(branch.getBankId()));
            branchName.setText(branch.getBranchName());
            branchAddress.setText(branch.getBranchAddress());
            branchPhno.setText(branch.getBranchPhNo());
        }
    }

}

