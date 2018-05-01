package com.fourllc.donate;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.fourllc.donate.databinding.ActivityMainBinding;
import com.fourllc.donate.model.justGivingModels.pageDetails.PageDetails;
import com.fourllc.donate.remote.JustGivingApiUtils;
import com.fourllc.donate.remote.JustGivingService;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setUpUiWithJustGivingData();
        //initialize the progress bar here and set up the timer
        mViewModel.retrieveDonationTotal();
        mViewModel.createTimer();

        final Observer<Double> donationTotalObserver = total -> {
            //Placeholder TODO add code to update the progress bar
            Log.i("MainActivityObserver", "onCreate: total is " + total);
            updateMoneyProgressBar(total);
        };
        mViewModel.getDonationTotal().observe(this, donationTotalObserver);



        mBinding.donateMoneyButton.setOnClickListener(v -> {
            Uri uri = Uri.parse(JustGivingApiUtils.SDI_URL);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        mBinding.donateBloodButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DonateBloodLocationActivity.class);
            startActivity(intent);
        });
    }

    private void setUpUiWithJustGivingData() {
        JustGivingService justGivingService = JustGivingApiUtils.getJustGivingService();
        justGivingService.getPageDetails().enqueue(new Callback<PageDetails>() {
            @Override
            public void onResponse(Call<PageDetails> call, Response<PageDetails> response) {
                PageDetails pageDetails = response.body();
                //TODO update all the textViews with the data from just giving
            }

            @Override
            public void onFailure(Call<PageDetails> call, Throwable t) {

            }
        });
    }

    private void updateMoneyProgressBar(double donationTotal){

    }

}
