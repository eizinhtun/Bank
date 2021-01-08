package com.upper.team1726.bankbox.activity;

/**
 * Created by MICRO on 10/24/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.fragment.MapFragment.ATMMapFragment;
import com.upper.team1726.bankbox.fragment.MapFragment.BranchMapFragment;
import com.upper.team1726.bankbox.fragment.MapFragment.ExchangeMapFragment;

import java.util.ArrayList;
import java.util.List;


public class ArroundAllActivity extends AppCompatActivity implements LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public float lat = 0;
    public float log = 0;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    ImageButton map;
    TabLayout tabLayout;
    ViewPager viewPager;
    double latitude = 0;
    double longitude = 0;
    Context context;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arround_all);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //
        setSupportActionBar(toolbar);

//        final int THREE_SECONDS = 10*1000;
//        final ProgressDialog dlg = new ProgressDialog(this);
//        dlg.setMessage("Searching");
//        dlg.setCancelable(false);
//        dlg.setProgress(THREE_SECONDS);
//        dlg.show();
        // showProgress(context);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Creating an empty criteria object

        //Criteria criteria = new Criteria();
        //// Getting the name of the provider that meets the criteria
        //String provider = locationManager.getBestProvider(criteria,false);
        //Location location = locationManager.getLastKnownLocation(provider);

        if (checkLocationPermission()) {
            Log.i("REQUEST LOCATION ", "WORK");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

            progressDialog = new ProgressDialog(ArroundAllActivity.this);
            progressDialog.setMessage("Loading.....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        map = (ImageButton) findViewById(R.id.map);


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArroundAllActivity.this, MapActivity.class);
                intent.putExtra("Lat", lat);
                intent.putExtra("Log", log);
                Log.e("latcall", " " + lat);
                Log.e("logcall", "" + log);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Request Permission")
                        .setMessage("Required Location Access Permission !")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(ArroundAllActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        Log.i("REQUEST LOCATION ", "WORK");
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

                        progressDialog = new ProgressDialog(ArroundAllActivity.this);
                        progressDialog.setMessage("Loading.....");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


//    public void showProgress(View view) {
//        final int THREE_SECONDS = 10*1000;
//        final ProgressDialog dlg = new ProgressDialog(this);
//        dlg.setMessage("Doing something...");
//        dlg.setCancelable(false);
//        dlg.show();
//        new Handler() {
//            public void postDelayed(Runnable runnable, int three_seconds) {
//            }
//
//            @Override
//            public void publish(LogRecord record) {
//
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public void close() throws SecurityException {
//
//            }
//        }.postDelayed(new Runnable() {
//            public void run() {
//                dlg.dismiss();
//            }
//        }, THREE_SECONDS);
//    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
//            lat = (float) location.getLatitude();
//            log = (float) location.getLongitude();
//            Log.e("lat"," "+lat);
//            Log.e("log"," "+log);
//            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());

            //locationManager.removeUpdates(this);
            lat = (float) location.getLatitude();
            log = (float) location.getLongitude();

            Log.e("LAT ", " " + lat);
            Log.e("LOG ", " " + log);

            progressDialog.dismiss();
            switch (viewPager.getCurrentItem()) {
                case 0:
                    ((BranchMapFragment) adapter.getItem(0)).searchNearestLocation(ArroundAllActivity.this, lat, log);
                    ((ATMMapFragment) adapter.getItem(1)).searchNearestLocation(ArroundAllActivity.this, lat, log);//MY
                    break;
                case 1:
                    ((ATMMapFragment) adapter.getItem(1)).searchNearestLocation(ArroundAllActivity.this, lat, log);//MY
                    break;
                case 2:
                    ((ExchangeMapFragment) adapter.getItem(2)).searchNearestLocation(ArroundAllActivity.this, lat, log);//MY
                    ((ATMMapFragment) adapter.getItem(1)).searchNearestLocation(ArroundAllActivity.this, lat, log);//MY
            }

            locationManager.removeUpdates(this);
        }
    }

    protected void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putFloat("Latitude", lat);
        bundle.putFloat("Longitude", log);


        Fragment branchFragment = new BranchMapFragment();
        branchFragment.setArguments(bundle);
        adapter.addFragment(branchFragment, "BRANCH");


        Fragment ATMFragment = new ATMMapFragment();
        ATMFragment.setArguments(bundle);
        adapter.addFragment(ATMFragment, "ATM");

        Fragment ExchangeFragment = new ExchangeMapFragment();
        ExchangeFragment.setArguments(bundle);
        adapter.addFragment(ExchangeFragment, "EXCHANGE");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        protected final List<Fragment> mFragmentList = new ArrayList<>();
        protected final List<String> mFragmentTitleList = new ArrayList<>();

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
