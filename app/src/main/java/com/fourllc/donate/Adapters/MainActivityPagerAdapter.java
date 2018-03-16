package com.fourllc.donate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fourllc.donate.fragments.mainActivityFragments.MainActivityDetailsFragment;
import com.fourllc.donate.fragments.mainActivityFragments.MainActivityDonateBloodFragment;
import com.fourllc.donate.fragments.mainActivityFragments.MainActivityDonateMoneyFragment;

/**
 * Created by aaronbrecher on 3/16/18.
 */

public class MainActivityPagerAdapter extends FragmentStatePagerAdapter {
    private int numTabs;

    public MainActivityPagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new MainActivityDetailsFragment();
            case 1:
                return new MainActivityDonateMoneyFragment();
            case 2:
                return new MainActivityDonateBloodFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
