package com.example.nearestbarsapp.fragments;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.adapters.RecyclerViewAdapter;
import com.example.nearestbarsapp.base.BaseFragment;
import com.example.nearestbarsapp.models.BarItem;
import com.example.nearestbarsapp.models.BarModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;


public class BarFragment extends BaseFragment implements RecyclerViewAdapter.ItemClickListener {

    private static BarFragment instance;
    private RecyclerViewAdapter adapter;
    private boolean isUiInitiated = false;
    private Location userLocation;
    private List<BarModel> bars;

    public static BarFragment getInstance() {
        if (instance == null) instance = new BarFragment();
        return instance;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bar;
    }

    @Override
    protected View onFragmentViewCreated(View view, Bundle savedInstanceState) {
        bindElements();
        return view;
    }

    private void bindElements() {
        RecyclerView myRecyclerView = getLayoutView().findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(getContext());
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        isUiInitiated = true;
        onUserLocationChanged(userLocation, bars);
    }

    @Override
    public void onUserLocationChanged(Location location, List<BarModel> bars) {
        this.bars = bars;
        this.userLocation = location;

        if (!isUiInitiated || location == null || bars == null) return;

        adapter.setBars(getBarsSorted(location, bars));
    }

    private List<BarItem> getBarsSorted(final Location location, List<BarModel> bars) {
        final List<BarItem> sortedBars = new ArrayList<>();
        bars.forEach(new Consumer<BarModel>() {
            @Override
            public void accept(BarModel barModel) {
                Location barLocation = new Location(barModel.getName());
                barLocation.setLatitude(barModel.getGeometry().getLocation().getLat());
                barLocation.setLongitude(barModel.getGeometry().getLocation().getLng());
                sortedBars.add(new BarItem(barModel.getName(), barLocation, location.distanceTo(barLocation)));
            }
        });
        Collections.sort(sortedBars);
        return sortedBars;
    }

    @Override
    public void onItemClick(BarItem bar) {
        openGoogleMap(bar);
    }

    private void openGoogleMap(BarItem bar) {
        Uri gmmIntentUri = Uri.parse("geo:" +
                bar.getLocation().getLatitude() + "," +
                bar.getLocation().getLongitude() + "?q=" +
                bar.getLocation().getLatitude() + "," +
                bar.getLocation().getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
