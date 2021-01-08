package com.upper.team1726.bankbox.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.fragment.ReviewFragment.AGDReviewFragment;
import com.upper.team1726.bankbox.fragment.ReviewFragment.AYAReviewFragment;
import com.upper.team1726.bankbox.fragment.ReviewFragment.CBReviewFragment;
import com.upper.team1726.bankbox.fragment.ReviewFragment.KBZReviewFragment;
import com.upper.team1726.bankbox.fragment.ReviewFragment.YomaReviewFragment;

import java.util.ArrayList;
import java.util.List;


public class ReviewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

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
        adapter.addFragment(new KBZReviewFragment(), "KBZ");
        adapter.addFragment(new AGDReviewFragment(), "AGD");
        adapter.addFragment(new CBReviewFragment(), "CB");
        adapter.addFragment(new AYAReviewFragment(), "AYA");
        adapter.addFragment(new YomaReviewFragment(), "Yoma");

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



