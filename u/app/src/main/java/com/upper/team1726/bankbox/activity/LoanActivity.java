package com.upper.team1726.bankbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.fragment.LoanFragment.AGDLoanFragment;
import com.upper.team1726.bankbox.fragment.LoanFragment.AYALoanFragment;
import com.upper.team1726.bankbox.fragment.LoanFragment.CBLoanFragment;
import com.upper.team1726.bankbox.fragment.LoanFragment.KBZLoanFragment;
import com.upper.team1726.bankbox.fragment.LoanFragment.YomaLoanFragment;

import java.util.ArrayList;
import java.util.List;

public class LoanActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewpager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        calculator = (ImageButton) findViewById(R.id.loanCalculator);

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanActivity.this, LoanCalculatorActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupViewpager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new KBZLoanFragment(), "KBZ");
        adapter.addFragment(new AGDLoanFragment(), "AGD");
        adapter.addFragment(new CBLoanFragment(), "CB");
        adapter.addFragment(new AYALoanFragment(), "AYA");
        adapter.addFragment(new YomaLoanFragment(), "Yoma");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {

            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}



