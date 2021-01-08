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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.fragment.IndividualBankFragment.AddressFragment;
import com.upper.team1726.bankbox.fragment.IndividualBankFragment.CardFragment;
import com.upper.team1726.bankbox.fragment.IndividualBankFragment.ServiceChargeFragment;

import java.util.ArrayList;
import java.util.List;


public class SpecificBankActivity extends AppCompatActivity {


    AutoCompleteTextView auto_etSearch;
    Spinner sp_CitySearch;
    ArrayList<String> bank_list = null;
    ArrayList<String> auto_citytown = null;
    String searchData = null;
    int bankIndex;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int bank_id;
    private BankDBHandler db;
    private ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_bank);

        //get bank id
        Intent intent = getIntent();
        bank_id = intent.getExtras().getInt("BANK_ID");
        db = new BankDBHandler(this);
        Log.e("specific bank id", String.valueOf(bank_id));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(db.getBankName(bank_id));

        btnSearch = (ImageButton) findViewById(R.id.btn_dia_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SpecificBankActivity.this);
                LayoutInflater inflater = SpecificBankActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.customsearch_dialog, null);

                BankDBHandler mydb = new BankDBHandler(getApplicationContext());
                //get data for autocomplete

                auto_citytown = mydb.getAllTownship();
                auto_citytown.addAll(mydb.getAllCity());


                Log.e("Auto_complete count", String.valueOf(auto_citytown.size()));
                ArrayAdapter auto_adapter = new ArrayAdapter(SpecificBankActivity.this, android.R.layout.simple_dropdown_item_1line, auto_citytown);
                auto_etSearch = (AutoCompleteTextView) dialogView.findViewById(R.id.auto_etSearch);

                auto_etSearch.setAdapter(auto_adapter);
                auto_etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        searchData = auto_etSearch.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                bank_list = mydb.getAllBankName();
                bank_list.remove(4);//remove MEB

                for (int i = 0; i < bank_list.size(); i++) {
                    if (db.getBankName(bank_id).equals(bank_list.get(i))) {
                        bankIndex = i;
                    }
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SpecificBankActivity.this, android.R.layout.simple_dropdown_item_1line, bank_list);
                sp_CitySearch = (Spinner) dialogView.findViewById(R.id.sp_searchBank);
                sp_CitySearch.setAdapter(arrayAdapter);
                sp_CitySearch.setSelection(bankIndex);

                builder.setView(dialogView)
                        .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //searchCityTownship()
                                searchData = auto_etSearch.getText().toString();
                                String searchBankData = sp_CitySearch.getSelectedItem().toString();
                                Log.e("Search data", searchData);
                                Log.e("Search Bank data", searchBankData);
                                Intent intent = new Intent(getApplicationContext(), SearchBank.class);
                                intent.putExtra("SEARCH_DATA", searchData);
                                intent.putExtra("SEARCH_BANK_DATA", searchBankData);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton(" Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }

        });


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        Log.e("Inside OnCreate", "specificBankActiviey");
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        Bundle bundle = new Bundle();
        bundle.putInt("BANK_ID", bank_id);

        Fragment addressFragment = new AddressFragment();
        addressFragment.setArguments(bundle);
        adapter.addFrag(addressFragment, "Address");

//        Fragment accountFragment = new AccountFragment();
//        accountFragment.setArguments(bundle);
//        adapter.addFrag(accountFragment, "Account");


        Fragment cardFragment = new CardFragment();
        cardFragment.setArguments(bundle);
        adapter.addFrag(cardFragment, "Card");

        Fragment serviceChargeFragment = new ServiceChargeFragment();
        serviceChargeFragment.setArguments(bundle);
        adapter.addFrag(serviceChargeFragment, "Charges");


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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
