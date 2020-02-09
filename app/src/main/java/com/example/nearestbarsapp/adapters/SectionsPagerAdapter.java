package com.example.nearestbarsapp.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.base.BaseFragment;
import com.example.nearestbarsapp.fragments.BarFragment;
import com.example.nearestbarsapp.fragments.MapFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public BaseFragment getItem(int position) {
        switch (position) {
            case 0:
                return BarFragment.getInstance();
            case 1:
                return MapFragment.getInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.fragment_bar);
            case 1:
                return context.getString(R.string.fragment_map);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}