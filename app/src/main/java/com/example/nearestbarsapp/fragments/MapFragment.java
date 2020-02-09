package com.example.nearestbarsapp.fragments;

import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.base.BaseFragment;
import com.example.nearestbarsapp.models.BarModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private static MapFragment instance;
    private GoogleMap googleMap;
    private boolean isUiInitiated = false;
    private Location userLocation;
    private List<BarModel> bars;

    public static MapFragment getInstance() {
        if (instance == null) instance = new MapFragment();
        return instance;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_map;
    }

    @Override
    protected View onFragmentViewCreated(View view, Bundle savedInstanceState) {
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        isUiInitiated = true;
        onUserLocationChanged(userLocation, bars);
    }

    @Override
    public void onUserLocationChanged(Location location, List<BarModel> bars) {
        this.bars = bars;
        this.userLocation = location;

        if (!isUiInitiated || location == null || bars == null) return;

        googleMap.clear();
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));

        for (BarModel bar : bars) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng latLng = new LatLng(bar.getGeometry().getLocation().getLat(), bar.getGeometry().getLocation().getLng());
            markerOptions.position(latLng);
            markerOptions.title(bar.getName());
            markerOptions.snippet(bar.getVicinity());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            googleMap.addMarker(markerOptions);
        }
    }
}
