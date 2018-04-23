package com.fourllc.donate;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fourllc.donate.remote.JustGivingApiUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button donateBloodButton = (Button) findViewById(R.id.donateBloodButton);
        Button donateMoneyButton = (Button) findViewById(R.id.donateMoneyButton);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        //initialize the progress bar here and set up the timer
        mViewModel.retrieveDonationTotal();
        mViewModel.createTimer();
        final Observer<Double> donationTotalObserver = total -> {
            //Placeholder TODO add code to update the progress bar
            Log.i("MainActivityObserver", "onCreate: total is " + total);
        };
        mViewModel.getDonationTotal().observe(this, donationTotalObserver);



        donateMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(JustGivingApiUtils.SDI_URL);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        donateBloodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DonateBloodLocationActivity.class);
                startActivity(intent);
            }
        });
    }

}
