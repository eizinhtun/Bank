package com.upper.team1726.bankbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.upper.team1726.bankbox.R;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {

    TextView tvAbout, tvMayLink, tvEiLink, tvYiLink;
    Toolbar toolbar;

    String about = "&nbsp &nbsp &nbsp <b>Quick Bank </b> application is a collection of banks in Myanmar. You can<ul><li> &nbsp find <b>nearest branches,ATMs and money exchange </b>around you using GPS System<li> &nbsp Or just search the <b>addresses filtered by townships</b><li> &nbsp Can know what banks provide what kind of services<li> &nbsp Can search<b> which accounts suit your needs.</b><<li> &nbsp Can also <b>calculate and compare the interest rates</b> of the banks<li> &nbsp Compare currency exchange rates<li> &nbsp Review the banks</ul>" +
            "<p>&nbsp &nbsp &nbsp Overall this application will help you to<b> find the branches and banks near you</b> and also help you <b>in deciding what banks and services  is most appropriate for you. </b></p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = (Toolbar) findViewById(R.id.toolbar
        );
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvAbout = (TextView) findViewById(R.id.tvAbout);
        //btnSend=(Button) findViewById(R.id.btnSendEmail);
        tvMayLink = (TextView) findViewById(R.id.tvMayLink);
        tvEiLink = (TextView) findViewById(R.id.tvEiLink);
        tvYiLink = (TextView) findViewById(R.id.tvYiLink);

        tvAbout.setText(Html.fromHtml(about));


        tvMayLink.setOnClickListener(this);
        tvEiLink.setOnClickListener(this);
        tvYiLink.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        String link = "";
        switch (tv.getId()) {
            case R.id.tvMayLink:
                link = "mayhtethlaingmh318@gmail.com";
                break;

            case R.id.tvEiLink:
                link = "eizinzinlin@gmail.com";
                break;

            case R.id.tvYiLink:
                link = "yiyi.smilelay@gmail.com";

        }


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{link});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quick Bank Application");
        intent.setPackage("com.google.android.gm");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this, "Gmail App is not installed", Toast.LENGTH_SHORT).show();
    }
}
