package com.fourllc.donate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fourllc.donate.fragments.AnotherLocationFragment;
import com.fourllc.donate.fragments.CurrentLocationListFragment;
import com.fourllc.donate.fragments.CurrentLocationMapFragment;

/**
 * This class is used to define a pager adapter for
 * the tabs swiping feature.
 */

public class DonateBloodLocationAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public DonateBloodLocationAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);

        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new CurrentLocationListFragment();
            case 1:
                return new CurrentLocationMapFragment();
            case 2:
                return new AnotherLocationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
