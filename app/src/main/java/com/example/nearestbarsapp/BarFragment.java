package com.example.nearestbarsapp;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearestbarsapp.POJO.Bar;

import java.util.ArrayList;
import java.util.List;


public class BarFragment extends BaseFragment {

    private RecyclerView myRecyclerView;
    private List<Bar> barList;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bar;

    }

    @Override
    protected View onFragmentViewCreated(View view, Bundle savedInstanceState) {
        barList = new ArrayList<>();
        barList.add(new Bar("Bar", "1875" + "m"));

        myRecyclerView = view.findViewById(R.id.modern_recycler_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), barList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }
}
