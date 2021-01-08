package com.upper.team1726.bankbox.fragment.ReviewFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.adapter.ReviewAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Review;

import java.util.ArrayList;
import java.util.List;


public class ReviewFragment extends Fragment {
    public RecyclerView recyclerView;
    String bank_name;
    BankDBHandler db;
    private List<Review> reviewArrayList = new ArrayList<>();
    private ReviewAdapter reviewAdapter;

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bank_name = bundle.getString("BANK_NAME");
        }

        db = new BankDBHandler(getContext());

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        reviewArrayList = db.getReview(bank_name);


        reviewAdapter = new ReviewAdapter(reviewArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(reviewAdapter);
        return view;

    }

    public void RefreshReview() {

        reviewArrayList.clear();
        reviewArrayList = db.getReview(bank_name);
        reviewAdapter = new ReviewAdapter(reviewArrayList);
        recyclerView.setAdapter(reviewAdapter);

    }


}
