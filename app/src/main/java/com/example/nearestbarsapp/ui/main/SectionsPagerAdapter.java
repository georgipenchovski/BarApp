package com.example.nearestbarsapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nearestbarsapp.BarFragment;
import com.example.nearestbarsapp.MapFragment;
import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.models.Bar;

import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_bar, R.string.tab_map};
    Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BarFragment();
        } else
            return new MapFragment();
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