package com.upper.team1726.bankbox.activity;

/**
 * Created by USER on 10/30/2017.
 */

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upper.team1726.bankbox.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class locator extends Fragment implements OnMapReadyCallback {

    private WebView contactWebView;

    public locator() {


//        Intent i=getIntent();
//        double latitude=i.getExtras.getDouble("LATITUDE");
//        double longitude=i.getExtras.getD("LONGITUDE");

    }

    public static String StreamToString(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
        }
        return writer.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_locator, container, false);
        Bundle b = getArguments();

        double latitude = b.getDouble("LAT");
        double longitude = b.getDouble("LONG");
        Log.e("Latitude11", "" + latitude);
        Log.e("Longitude11", "" + longitude);

        getActivity().setTitle("Contact Us");
        contactWebView = (WebView) view.findViewById(R.id.map);
        contactWebView.setBackgroundColor(0);
        if (Build.VERSION.SDK_INT >= 11) {
            contactWebView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        }
        contactWebView.getSettings().setBuiltInZoomControls(true);
        AssetManager mgr = getContext().getAssets();
        String filename = null;
        try {
            filename = "contact.html";
            System.out.println("filename : " + filename);
            InputStream in = mgr.open(filename, AssetManager.ACCESS_BUFFER);
            String sHTML = StreamToString(in);
            in.close();
            contactWebView.loadDataWithBaseURL(null, sHTML, "text/html", "utf-8", null);
            //singleContent.setText(Html.fromHtml(sHTML));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
//        Intent i=getIntent();
//        double latitude=i.getExtras.getDouble("LATITUDE");
//        double longitude=i.getExtras.getD("LONGITUDE");

        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }
}