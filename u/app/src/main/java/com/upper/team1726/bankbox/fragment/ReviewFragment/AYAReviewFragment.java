package com.upper.team1726.bankbox.fragment.ReviewFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Review;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AYAReviewFragment extends Fragment {
    ImageButton btnSubmit;
    EditText newComment;
    Fragment fragment;

    public AYAReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        newComment = (EditText) view.findViewById(R.id.etNewComment);
        btnSubmit = (ImageButton) view.findViewById(R.id.btnSubmit);

        final Bundle bundle = new Bundle();
        bundle.putString("BANK_NAME", "AYA");

        fragment = new ReviewFragment();
        fragment.setArguments(bundle);

        if (fragment != null) {
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.review_container_body, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (newComment.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please write your comment!!", Toast.LENGTH_SHORT).show();
                } else {
                    BankDBHandler db = new BankDBHandler(getContext());
                    String review_data = newComment.getText().toString();
                    String bank_name = "AYA";
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy -HH:mm");
                    String formatteDate = dateFormat.format(c.getTime());
                    Review review = new Review(bank_name, review_data, formatteDate);
                    db.insertReview(review);
                    ((ReviewFragment) fragment).RefreshReview();

                    Toast.makeText(getContext(), "Thank you for your review ^-^", Toast.LENGTH_SHORT).show();
                    newComment.setText(""); //for clear data
                }

            }
        });

        return view;
    }

}
