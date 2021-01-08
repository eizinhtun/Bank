package com.upper.team1726.bankbox.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.upper.team1726.bankbox.LoadDataTask;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;


public class LoadData extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bank_splash);
        Log.e("STATUS", "Reach First");

        try {
            Log.e("STATUS", "Reach Before db");
            BankDBHandler db = new BankDBHandler(getApplicationContext());
            Log.e("STATUS", "Reach After db");
            if (db.ischeckEmpty()) {


                new LoadDataTask(this, db).execute();


            } else {


                Intent mainIntent = new Intent(getApplicationContext(), BankSplash.class);
                startActivity(mainIntent);
                finish();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }


    }


}
