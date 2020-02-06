package com.example.nearestbarsapp.fragments;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearestbarsapp.MainActivity;
import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.adapters.RecyclerViewAdapter;
import com.example.nearestbarsapp.base.BaseFragment;
import com.example.nearestbarsapp.models.Bar;



import java.util.ArrayList;
import java.util.List;


public class BarFragment extends BaseFragment implements RecyclerViewAdapter.ItemClickListener {

    private RecyclerView myRecyclerView;
    private List<Bar> barList;
    RecyclerViewAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bar;
    }

    @Override
    protected View onFragmentViewCreated(View view, Bundle savedInstanceState) {
        bindElements();
        Location location = ((MainActivity) getActivity()).getLocation();
        System.out.println(location.getLatitude());
        System.out.println(location.getLongitude());
        return view;
    }

    private void bindElements() {
        barList = new ArrayList<>();
        barList.add(new Bar("Bar 1", "1" + "m"));
        barList.add(new Bar("Bar 2", "151" + "m"));
        barList.add(new Bar("Bar 3", "75" + "m"));

        myRecyclerView = getLayoutView().findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(getContext(), barList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(Bar bar) {
        openGoogleMap();
    }

    private void openGoogleMap() {
        Uri gmmIntentUri = Uri.parse("geo:42.6978,23.3217?q=bars");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
