package com.example.nearestbarsapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.fragments.BarFragment;
import com.example.nearestbarsapp.fragments.MapFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BarFragment();
            case 1:
                return new MapFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_bar);
            case 1:
                return mContext.getString(R.string.fragment_map);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}