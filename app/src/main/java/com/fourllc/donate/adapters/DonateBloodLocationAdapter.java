package com.fourllc.donate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fourllc.donate.fragments.AnotherLocationFragment;
import com.fourllc.donate.fragments.NearCurrentLocationFragment;
import com.fourllc.donate.fragments.NearHomeLocationFragment;



public class DonateBloodLocationAdapter extends FragmentStatePagerAdapter {

    // TODO: 3/15/2018 use more expressive variable names
    private int numberOfTabs;

    // TODO: 3/15/2018 use more expressive parameter names
    public DonateBloodLocationAdapter(FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        // TODO: 3/15/2018 more concise expression..
        switch (position) {
            case 0:
                return new NearCurrentLocationFragment();
            case 1:
                return new NearHomeLocationFragment();
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
