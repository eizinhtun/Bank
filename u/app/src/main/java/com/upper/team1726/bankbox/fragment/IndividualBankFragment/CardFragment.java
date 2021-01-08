package com.upper.team1726.bankbox.fragment.IndividualBankFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.TopGravityDrawable;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CardFragment extends Fragment implements View.OnClickListener {

    TextView tvCard, tvInfo;
    LinearLayout linearLayout;
    int bank_id;
    String bankName;
    BankDBHandler db;
    ArrayList<TextView> infoArray = new ArrayList<>();
    CardView card;
    TopGravityDrawable iconUp, iconDown;

    public CardFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        db = new BankDBHandler(getContext());
        Bundle bundle = getArguments();
        if (bundle != null) {
            bank_id = bundle.getInt("BANK_ID");
            bankName = db.getBankName(bank_id);

        }
        Log.e("inside card fragment:", bankName);


        linearLayout = (LinearLayout) view.findViewById(R.id.cardLayout);
        linearLayout.setPadding(16, 32, 16, 16);


        prepareTextView();


        return view;
    }


    private void prepareTextView() {

        try {
            JSONArray arrJSON = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);
                if (bankName.equals(objJSON.getString("Bank"))) {

                    card = new CardView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(8, 0, 8, 16);
                    card.setLayoutParams(params);
                    card.setRadius(5);


                    tvCard = new TextView(getContext());
                    tvCard.setId(infoArray.size());
                    tvCard.setText(objJSON.getString("Card"));
                    tvCard.setTextSize(15);
                    tvCard.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

                    final Bitmap bitmapUp = BitmapFactory.decodeResource(getResources(), R.drawable.uparrow);
                    final Bitmap bitmapDown = BitmapFactory.decodeResource(getResources(), R.drawable.downarrow);
                    iconUp = new TopGravityDrawable(getResources(), bitmapUp);
                    iconDown = new TopGravityDrawable(getResources(), bitmapDown);
                    tvCard.setCompoundDrawablesWithIntrinsicBounds(null, null, iconDown, null);


                    card.addView(tvCard);


                    LinearLayout.LayoutParams paramsInfo = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    paramsInfo.setMargins(16, 100, 16, 16);
//                    tvAccount.setLayoutParams(params);
//                    tvAccount.setBackgroundResource(R.drawable.textborder);
//                    tvAccount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow, 0);


                    //linearLayout.addView(tvAccount);


                    tvInfo = new TextView(getContext());
                    tvInfo.setId(infoArray.size());
                    tvInfo.setText(Html.fromHtml(objJSON.getString("Description")));
                    tvInfo.setTypeface(Typeface.SANS_SERIF);
                    tvInfo.setVisibility(View.GONE);
                    tvInfo.setLayoutParams(paramsInfo);
                    tvCard.setBackgroundResource(R.drawable.textborder);
                    card.addView(tvInfo);
                    linearLayout.addView(card);
                    infoArray.add(tvInfo);

                    tvCard.setOnClickListener(this);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String loadJSONFromAsset() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = getActivity().getAssets().open("BankCard.json");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            Log.d("RESPONSE ", stringBuilder.toString());

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    @Override
    public void onClick(View v) {


        int id = v.getId();
        TextView tvCard = (TextView) v;

        TextView txtView = infoArray.get(id);

        if (txtView.getVisibility() == View.VISIBLE) {
            txtView.setVisibility(View.GONE);
            tvCard.setCompoundDrawablesWithIntrinsicBounds(null, null, iconDown, null);
        } else {
            txtView.setVisibility(View.VISIBLE);
            tvCard.setCompoundDrawablesWithIntrinsicBounds(null, null, iconUp, null);
        }


    }
}


