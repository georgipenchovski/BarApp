package com.example.nearestbarsapp.base;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nearestbarsapp.models.BarModel;

import java.util.List;

public abstract class BaseFragment extends Fragment {

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutRes(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFragmentViewCreated(view, savedInstanceState);
    }

    protected abstract int getLayoutRes();

    protected abstract View onFragmentViewCreated(View view, Bundle savedInstanceState);

    protected View getLayoutView() {
        return view;
    }

    public abstract void onUserLocationChanged(Location location, List<BarModel> bars);
}