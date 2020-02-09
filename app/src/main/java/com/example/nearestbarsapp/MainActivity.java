package com.example.nearestbarsapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.nearestbarsapp.adapters.SectionsPagerAdapter;
import com.example.nearestbarsapp.models.BarModel;
import com.example.nearestbarsapp.retrofit.NetworkManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int RADIUS_NEARBY_BARS = 800;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    private LocationCallback locationCallback =
            new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        updateUiWithLocation(location);
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Location lastKnownLocation = getLastLocationIfAvailable();
        if (lastKnownLocation != null) updateUiWithLocation(lastKnownLocation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback);
    }

    private void updateUiWithLocation(final Location location) {
        NetworkManager.getInstance().getNearbyBars(location.getLatitude(), location.getLongitude(), RADIUS_NEARBY_BARS, new NetworkManager.DataListener<List<BarModel>>() {
            @Override
            public void onData(List<BarModel> data) {
                for (int fragmentIndex = 0; fragmentIndex < sectionsPagerAdapter.getCount(); fragmentIndex++) {
                    sectionsPagerAdapter.getItem(fragmentIndex).onUserLocationChanged(location, data);
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, getString(R.string.error_bars) + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupNavigationViews() {
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private Location getLastLocationIfAvailable() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null || !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showAlertMessageNoGps();
        } else if (!isLocationPermissionGranted()) {
            requestLocationPermission();
        } else if (!isGooglePlayServicesAvailable()) {
            finish();
        } else {
            setupPeriodicLocationUpdates();
            return getLastKnownLocationLocation(locationManager);
        }
        return null;
    }

    private void setupPeriodicLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(60000);
        locationRequest.setFastestInterval(15000);
        locationRequest.setPriority(LocationRequest.PRIORITY_NO_POWER);
        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(
                        locationRequest, locationCallback, Looper.getMainLooper());
    }

    @SuppressLint("MissingPermission")
    public Location getLastKnownLocationLocation(LocationManager locationManager) {
        Location location;
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        return location;
    }

    private void showAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.error_turn_on_gps))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    public boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    getLastLocationIfAvailable();
                }
            }
        }
    }
}