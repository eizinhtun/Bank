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
import android.util.Log;
import android.view.MenuItem;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.fragment.ATMFragment;
import com.upper.team1726.bankbox.fragment.BranchFragment;
import com.upper.team1726.bankbox.fragment.ExchangeFragment;

import java.util.ArrayList;
import java.util.List;


public class SearchBank extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bank);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewpager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewpager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Intent intent = getIntent();
        String searchData = intent.getExtras().getString("SEARCH_DATA");
        String searchBankData = intent.getExtras().getString("SEARCH_BANK_DATA");
        Log.e("Search data1", searchData);
        Log.e("Search Bankdata1", searchBankData);

        //set data
        Bundle bundle = new Bundle();
        bundle.putString("SEARCH_lOCATION", searchData);
        bundle.putString("SEARCH_BANKDATA", searchBankData);

        Fragment branchFragment = new BranchFragment();
        branchFragment.setArguments(bundle);
        adapter.addFragment(branchFragment, "Branch");


        Fragment atmFragnment = new ATMFragment();
        atmFragnment.setArguments(bundle);
        adapter.addFragment(atmFragnment, "ATM");

        Fragment exchangeFragment = new ExchangeFragment();
        exchangeFragment.setArguments(bundle);
        adapter.addFragment(exchangeFragment, "Exchange");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

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



