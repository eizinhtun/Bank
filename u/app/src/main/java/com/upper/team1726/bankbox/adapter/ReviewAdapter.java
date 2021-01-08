package com.upper.team1726.bankbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.model.Review;

import java.util.List;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private List<Review> reviewList;


    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false);
        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.bindData(review);

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Context mContext;
        public TextView comment, date;
        private Review review;

        public MyViewHolder(View view, Context context) {
            super(view);
            this.mContext = context;
            comment = (TextView) view.findViewById(R.id.tvComment);
            date = (TextView) view.findViewById(R.id.tvDate);

        }

        public void bindData(Review mReview) {
            this.review = mReview;
            comment.setText(review.getReview());
            date.setText(review.getReviewDate());

        }
    }

}




