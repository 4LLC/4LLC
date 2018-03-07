package com.fourllc.donate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.fourllc.donate.Adapters.DonateBloodLocationAdapter;

/**
 * This class is used to display the blood donation centers based
 * on the location:
 * 1) Near the current location
 * 2) Near the home location
 * 3) Another location - location that user wants to search
 */
public class DonateBloodLocationActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    //toolbar is used instead of the action bar
    private Toolbar toolbar;
    private TabLayout locationTabLayout;
    private ViewPager locationViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood_location);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                Intent intent  = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
