package com.example.nearestbarsapp;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private SupportMapFragment mapFragment;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_map;
    }

    @Override
    protected View onFragmentViewCreated(View view, Bundle savedInstanceState) {
        initMap();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private void initMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}
