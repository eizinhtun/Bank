package com.upper.team1726.bankbox.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.TopGravityDrawable;
import com.upper.team1726.bankbox.model.Account;

import java.util.List;


/**
 * Created by USER on 11/9/2017.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder> {


    private List<Account> accountList;

    public AccountAdapter(List<Account> accountList) {

        this.accountList = accountList;
    }

    @Override
    public AccountAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_list, parent, false);
        return new AccountAdapter.MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final AccountAdapter.MyViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.bindData(account);

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvAccount, tvInfo;
        public Context mContext;
        public TopGravityDrawable iconUp;
        private Account account;

        public MyViewHolder(View view, Context context) {
            super(view);

            this.mContext = context;
            tvAccount = (TextView) view.findViewById(R.id.tvAccountName);
            tvAccount.setTextColor(Color.BLACK);
            tvInfo = (TextView) view.findViewById(R.id.tvAccountInfo);


            tvAccount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow, 0);


            tvAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (tvInfo.getVisibility() == View.VISIBLE) {
                        tvInfo.setVisibility(View.GONE);
                        tvAccount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow, 0);

                    } else {
                        tvInfo.setVisibility(View.VISIBLE);
                        tvAccount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uparrow, 0);


                    }

                }
            });


        }

        public void bindData(Account mAccount) {
            this.account = mAccount;
            tvAccount.setText(Html.fromHtml(account.getName()));
            tvInfo.setText(Html.fromHtml(account.getDescription()));


        }
    }

}
