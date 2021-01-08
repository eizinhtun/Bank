package com.upper.team1726.bankbox.fragment.MapFragment;


import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team1726.bankbox.R;
import com.upper.team1726.bankbox.activity.ArroundAllActivity;
import com.upper.team1726.bankbox.adapter.BranchMapAdapter;
import com.upper.team1726.bankbox.dbhandler.BankDBHandler;
import com.upper.team1726.bankbox.model.Branch;

import java.util.ArrayList;


public class BranchMapFragment extends Fragment {
    Context context;

    private RecyclerView recyclerView;
    private ArrayList<Branch> branchArrayList = new ArrayList<>();
    private BranchMapAdapter branchAdapter;
    private ArrayList<Integer> arround = new ArrayList<>();
    private TextView emptyView;


    public BranchMapFragment() {
        // Required empty public constructor
    }

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        emptyView = (TextView) view.findViewById(R.id.emptyView);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(branchAdapter);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        ArroundAllActivity activity = (ArroundAllActivity) getActivity();
        searchNearestLocation(getActivity(), activity.lat, activity.log);
    }

    public void searchNearestLocation(Context context, float lat, float log) {

        Log.e("Lattitudeatm", "" + lat);
        Log.e("Lattitudeatm", "" + log);

        BankDBHandler DB = new BankDBHandler(context);


        PointF center = new PointF(lat, log);
        final double mult = 1;
        double radius = 10000;// mult = 1.1; is more reliable
        PointF p1 = calculateDerivedPosition(center, mult * radius, 0);
        PointF p2 = calculateDerivedPosition(center, mult * radius, 90);
        PointF p3 = calculateDerivedPosition(center, mult * radius, 180);
        PointF p4 = calculateDerivedPosition(center, mult * radius, 270);

        String tableName = "tb_branch";
        arround = DB.getArroundLocation(tableName, p1, p2, p3, p4);


        ArrayList<Branch> branchArrayList = new ArrayList<>();
        for (int i = 0; i < arround.size(); i++) {

            branchArrayList.add(DB.getBranchInfo(arround.get(i)));

        }
        DB.close();
        if (branchArrayList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        branchAdapter = new BranchMapAdapter(branchArrayList);
        recyclerView.setAdapter(branchAdapter);

    }

}


