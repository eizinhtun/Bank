package com.upper.team1726.bankbox.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.fragment.IndividualBankFragment.AccountFragment;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton calculator, suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewpager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        calculator = (ImageButton) findViewById(R.id.accountCalculator);
        suggestion = (ImageButton) findViewById(R.id.accountChoose);

        calculator.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, InterestActivity.class);

                startActivity(intent);
            }
        });

        suggestion.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                showDialogBox();
            }
        });

    }

    public void showDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Need a suggestion to choose account?");
        builder.setMessage("If you can't decide which account to choose, let's try a suggestion for you.");
        // LayoutInflater inflater=this.getLayoutInflater();
        //final View dialogView=inflater.inflate(R.layout.custom_suggest_dialog,null);

        builder
                .setPositiveButton("GO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), AccountSuggestion.class);

                        startActivity(intent);

                    }
                })
                .setNegativeButton(" CANCEL ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();

    }


    private void setupViewpager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        BankDBHandler db = new BankDBHandler(this);
        ArrayList<String> bank_list = db.getAllBankName();
        bank_list.remove(0);
        bank_list.remove(3);
//        adapter.addFragment(new AccountFragment("KBZ"), "KBZ");
//        adapter.addFragment(new AccountFragment("AGD"), "AGD");
//        adapter.addFragment(new AccountFragment("CB"), "CB");
//        adapter.addFragment(new AccountFragment("AYA"), "AYA");
//        adapter.addFragment(new AccountFragment("YOMA"), "Yoma");

        for (int i = 0; i < bank_list.size(); i++) {


            Bundle bundle = new Bundle();
            bundle.putString("BANK_NAME", bank_list.get(i));

            Fragment accountFragment = new AccountFragment();
            accountFragment.setArguments(bundle);
            adapter.addFragment(accountFragment, bank_list.get(i));

        }

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



