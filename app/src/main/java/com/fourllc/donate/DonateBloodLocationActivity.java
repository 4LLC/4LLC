package com.fourllc.donate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.fourllc.donate.adapters.DonateBloodLocationAdapter;

/**
 * This class is used to display the blood donation centers based
 * on the location:
 * 1) Near the current location
 * 2) Near the home location
 * 3) Another location - location that user wants to search
 */
public class DonateBloodLocationActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout locationTabLayout;
    private ViewPager locationViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood_location);

        locationTabLayout = (TabLayout) findViewById(R.id.location_tab_layout);
        locationViewPager = (ViewPager) findViewById(R.id.location_view_pager);

        //adding the tabs to the tablayout
        locationTabLayout.addTab(locationTabLayout.newTab().setText("Near Me"));
        locationTabLayout.addTab(locationTabLayout.newTab().setText("Near Home Location"));
        locationTabLayout.addTab(locationTabLayout.newTab().setText("Search Location"));
        locationTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //creating pager adapter
        PagerAdapter pagerAdapter = new DonateBloodLocationAdapter(getSupportFragmentManager(), locationTabLayout.getTabCount());

        //adding the adapter to the view pager
        locationViewPager.setAdapter(pagerAdapter);
        locationViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(locationTabLayout));

        locationTabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        locationViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
