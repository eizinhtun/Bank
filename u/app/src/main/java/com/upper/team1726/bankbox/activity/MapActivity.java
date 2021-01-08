package com.upper.team1726.bankbox.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.ATM;
import com.upper.team1726.bankbox.model.Branch;
import com.upper.team1726.bankbox.model.Exchange;

import java.util.ArrayList;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ArrayList<LatLng> MarkerPoints = new ArrayList<>();
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    ArrayList<Integer> arroundBranch = new ArrayList<>();
    ArrayList<Integer> arroundATM = new ArrayList<>();
    ArrayList<Integer> arroundExchange = new ArrayList<>();

    ArrayList<Branch> BranchArrayList = new ArrayList<>();
    ArrayList<ATM> ATMArrayList = new ArrayList<>();
    ArrayList<Exchange> ExchangeArrayList = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> bank = new ArrayList<>();


    int BranchSize;
    int ATMSize;
    int ExchangeSize;
    float latitude = 0, longitude = 0;

    BankDBHandler db;
    Color color;
    private GoogleMap mMap;

    public static PointF calculateDerivedPosition(PointF point,
                                                  double range, double bearing) {
        double EarthRadius = 6371000; // m

        double latA = Math.toRadians(point.x);
        double lonA = Math.toRadians(point.y);
        double angularDistance = range / EarthRadius;
        double trueCourse = Math.toRadians(bearing);

        double lat = Math.asin(
                Math.sin(latA) * Math.cos(angularDistance) +
                        Math.cos(latA) * Math.sin(angularDistance)
                                * Math.cos(trueCourse));

        double dlon = Math.atan2(
                Math.sin(trueCourse) * Math.sin(angularDistance)
                        * Math.cos(latA),
                Math.cos(angularDistance) - Math.sin(latA) * Math.sin(lat));

        double lon = ((lonA + dlon + Math.PI) % (Math.PI * 2)) - Math.PI;

        lat = Math.toDegrees(lat);
        lon = Math.toDegrees(lon);

        PointF newPoint = new PointF((float) lat, (float) lon);

        return newPoint;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new BankDBHandler(this);
        setContentView(R.layout.activity_route);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        latitude = intent.getExtras().getFloat("Lat");
        longitude = intent.getExtras().getFloat("Log");
        Log.e("Lat", " " + latitude);
        Log.e("Long", "" + longitude);
//
//
//        PointF center = new PointF(latitude,longitude);

        PointF center = new PointF(latitude, longitude);
        final double mult = 1;
        double radius = 10000;//mult = 1.1; is more reliable
        PointF p1 = calculateDerivedPosition(center, mult * radius, 0);
        PointF p2 = calculateDerivedPosition(center, mult * radius, 90);
        PointF p3 = calculateDerivedPosition(center, mult * radius, 180);
        PointF p4 = calculateDerivedPosition(center, mult * radius, 270);

//        //MY EDIT one
        LatLng arroundPt;
//        MarkerPoints.add(p1);
//

        String tableBranch = "tb_branch";
        arroundBranch = db.getArroundLocation(tableBranch, p1, p2, p3, p4);

        Log.e("db1", "point");

        if (!arroundBranch.isEmpty()) {

            Log.e("db2", "point");
            for (int i = 0; i < arroundBranch.size(); i++) {
                BranchArrayList.add(db.getBranchInfo(arroundBranch.get(i)));
            }

        }


        Log.e("db3", "point");

        String tableATM = "tb_atm";
        arroundATM = db.getArroundLocation(tableATM, p1, p2, p3, p4);

        if (!arroundATM.isEmpty()) {

            for (int i = 0; i < arroundATM.size(); i++) {
                ATMArrayList.add(db.getATMInfo(arroundATM.get(i)));
            }
            Log.e("db4", "point");

        }


        String tableExchange = "tb_exchange";
        arroundExchange = db.getArroundLocation(tableExchange, p1, p2, p3, p4);

        Log.e("db5", "point");

        if (!arroundExchange.isEmpty()) {
            Log.e("db6", "point");

            for (int i = 0; i < arroundExchange.size(); i++) {
                ExchangeArrayList.add(db.getExchangeInfo(arroundExchange.get(i)));
            }

        }

        Log.e("status", "after db6");


        BranchSize = BranchArrayList.size();
        Log.e("db7", "a" + BranchSize);
        for (int j = 0; j < BranchArrayList.size(); j++) {
            Log.e("db7", "point");
            arroundPt = new LatLng(BranchArrayList.get(j).getBranchLatitude(), BranchArrayList.get(j).getBranchLongitude());
            MarkerPoints.add(arroundPt);
            name.add(BranchArrayList.get(j).getBranchName());
            bank.add(BranchArrayList.get(j).getBankName());
        }

        ATMSize = ATMArrayList.size();

        for (int i = 0; i < ATMArrayList.size(); i++) {
            Log.e("db8", "point");
            arroundPt = new LatLng(ATMArrayList.get(i).getAtmLatitude(), ATMArrayList.get(i).getAtmLongitude());
            MarkerPoints.add(arroundPt);
            name.add(ATMArrayList.get(i).getAtmName() + " ATM");
            bank.add(ATMArrayList.get(i).getBankName());
        }

        ExchangeSize = ExchangeArrayList.size();
        for (int i = 0; i < ExchangeArrayList.size(); i++) {
            arroundPt = new LatLng(ExchangeArrayList.get(i).getExchangeLatitude(), ExchangeArrayList.get(i).getExchangeLongitude());
            MarkerPoints.add(arroundPt);
            name.add(ExchangeArrayList.get(i).getExchangeName() + " Exchange");
            bank.add(ExchangeArrayList.get(i).getBankName());
        }

    }

    private void BranchcreateMarker(String title, String snippet, double lat, double lng) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(title)
                .snippet(snippet)
                //.icon(BitmapDescriptorFactory.defaultMarker(R.drawable.b1);
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }

    private void ATMcreateMarker(String title, String snippet, double lat, double lng) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    private void ExchangecreateMarker(String title, String snippet, double lat, double lng) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }


//
//    private String getUrl(LatLng origin, LatLng dest) {
//
//        // Origin of route
//        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
//
//        // Destination of route
//        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
//
//
//        // Sensor enabled
//        String sensor = "sensor=false";
//
//        // Building the parameters to the web service
//        String parameters = str_origin + "&" + str_dest + "&" + sensor;
//
//        // Output format
//        String output = "json";
//
//        // Building the url to the web service
//        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
//
//
//        return url;
//    }

    /**
     * A method to download json data from url
     */
//    private String downloadUrl(String strUrl) throws IOException {
//        String data = "";
//        InputStream iStream = null;
//        HttpURLConnection urlConnection = null;
//        try {
//            URL url = new URL(strUrl);
//
//            // Creating an http connection to communicate with url
//            urlConnection = (HttpURLConnection) url.openConnection();
//
//            // Connecting to url
//            urlConnection.connect();
//
//            // Reading data from url
//            iStream = urlConnection.getInputStream();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
//
//            StringBuffer sb = new StringBuffer();
//
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//            data = sb.toString();
//            Log.d("downloadUrl", data.toString());
//            br.close();
//
//        } catch (Exception e) {
//            Log.d("Exception", e.toString());
//        } finally {
//            iStream.close();
//            urlConnection.disconnect();
//        }
//        return data;
//    }
//
//    // Fetches data from url passed
//    private class FetchUrl extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... url) {
//
//            // For storing data from web service
//            String data = "";
//
//            try {
//                // Fetching the data from web service
//                data = downloadUrl(url[0]);
//                Log.d("Background Task data", data.toString());
//            } catch (Exception e) {
//                Log.d("Background Task", e.toString());
//            }
//            return data;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
////            ParserTask parserTask = new ParserTask();
////
////            // Invokes the thread for parsing the JSON data
////            parserTask.execute(result);
//
//        }
//    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        for (LatLng location : MarkerPoints) {
//            createMarker("TESTING",location.latitude, location.longitude);
//        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng location;
        for (int i = 0; i < MarkerPoints.size(); i++) {
            Log.e("MARKERPOINTS SIZE", "" + MarkerPoints.size());

            location = MarkerPoints.get(i);
            if (i < BranchSize) {
                Log.e("BRANCH SIZE", "" + BranchSize);
                BranchcreateMarker(name.get(i), bank.get(i), location.latitude, location.longitude);
            } else if (i < BranchSize + ATMSize) {
                int cal = BranchSize - 1 + ATMSize;
                Log.e("ATM SIZE", "" + ATMSize);
                Log.e("CAL SIZE", "" + cal);
                ATMcreateMarker(name.get(i), bank.get(i), location.latitude, location.longitude);
            } else {
                ExchangecreateMarker(name.get(i), bank.get(i), location.latitude, location.longitude);
            }

        }

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        // Setting onclick event listener for the map
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng point) {
//
//                // Already two locations
////                if (MarkerPoints.size() > 1) {
////                    MarkerPoints.clear();
////                    mMap.clear();
////                }
//
//                // Adding new item to the ArrayList
//                MarkerPoints.add(point);
//
//                // Creating MarkerOptions
//                MarkerOptions options = new MarkerOptions();
//
//                // Setting the position of the marker
//                options.position(point);
//
//                /**
//                 * For the start location, the color of marker is GREEN and
//                 * for the end location, the color of marker is RED.
//                 */
//
//                for(int i=0;i<MarkerPoints.size();i++) {
//                   // if (MarkerPoints.size() == 1) {
////                    if(i<BranchSize-1){
////                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
////                    }
////
////                    //my edit
////                  else if (i>BranchSize-1 && i<BranchSize+ATMSize-1) {
////                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
////                }
////
////
////                    else {
////                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
////                    }
//
//
////                // my edition
////                else{
////                    for(int i=0;i<MarkerPoints.size();i++)
////                    {
////                        mMap.addMarker(options);
////                    }
//                    // }
//
//                    // Add new marker to the Google Map Android API V2
//                    mMap.addMarker(options);
//                }
//
//                // Checks, whether start and end locations are captured
////                if (MarkerPoints.size() >= 2) {
////                    LatLng origin = MarkerPoints.get(0);
////                    LatLng dest = MarkerPoints.get(1);
////
////                    // Getting URL to the Google Directions API
////                    String url = getUrl(origin, dest);
////                    Log.d("onMapClick", url.toString());
////                    FetchUrl FetchUrl = new FetchUrl();
////
////                    // Start downloading json data from Google Directions API
////                    FetchUrl.execute(url);
////                    //move map camera
////                    mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
////                    mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
////                }
//
//            }
//        });

    }

    /**
     * A class to parse the Google Places in JSON format
     */
    // private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>>> {

    // Parsing the data in non-ui thread
//        @Override
//        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
//
//            JSONObject jObject;
//            List<List<HashMap<String, String>>> routes = null;
//
//            try {
//                jObject = new JSONObject(jsonData[0]);
//                Log.d("ParserTask",jsonData[0].toString());
//                DataParser parser = new DataParser();
//                Log.d("ParserTask", parser.toString());
//
//                // Starts parsing data
//                routes = parser.parse(jObject);
//                Log.d("ParserTask","Executing routes");
//                Log.d("ParserTask",routes.toString());
//
//            } catch (Exception e) {
//                Log.d("ParserTask",e.toString());
//                e.printStackTrace();
//            }
//            return routes;
//        }

    // Executes in UI thread, after the parsing process
    //   @Override
//        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
//            ArrayList<LatLng> points;
//            PolylineOptions lineOptions = null;
//
//            // Traversing through all the routes
//            for (int i = 0; i<result.size(); i++) {
//                points = new ArrayList<>();
//                lineOptions = new PolylineOptions();
//
//                // Fetching i-th route
//                List<HashMap<String, String>> path = result.get(i);
//
//                // Fetching all the points in i-th route
//                for (int j = 0; j < path.size();j++) {
//                    HashMap<String, String> point = path.get(j);
//
//                    double lat = Double.parseDouble(point.get("lat"));
//                    double lng = Double.parseDouble(point.get("lng"));
//                    LatLng position = new LatLng(lat, lng);
//
//                    points.add(position);
//                }
//
//                // Adding all the points in the route to LineOptions
//                lineOptions.addAll(points);
//                lineOptions.width(10);
//                lineOptions.color(Color.RED);
//
//                Log.d("onPostExecute","onPostExecute lineoptions decoded");
//
//            }
//
//            // Drawing polyline in the Google Map for the i-th route
//            if(lineOptions != null) {
//                mMap.addPolyline(lineOptions);
//            }
//            else {
//                Log.d("onPostExecute","without Polylines drawn");
//            }
//        }
//    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
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

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
}