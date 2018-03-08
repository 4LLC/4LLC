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

    private int noOfTabs;

    public DonateBloodLocationAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);

        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CurrentLocationListFragment currentLocationFragment = new CurrentLocationListFragment();
                return currentLocationFragment;
            case 1:
                CurrentLocationMapFragment homeLocationFragment = new CurrentLocationMapFragment();
                return homeLocationFragment;
            case 2:
                AnotherLocationFragment anotherLocationFragment = new AnotherLocationFragment();
                return anotherLocationFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
