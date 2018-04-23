package com.fourllc.donate;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;

import com.fourllc.donate.model.justGivingModels.DonationTotal;
import com.fourllc.donate.remote.JustGivingApiUtils;
import com.fourllc.donate.remote.JustGivingService;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * Created by aaronbrecher on 4/20/18.
 */

public class MainActivityViewModel extends ViewModel {

    public static final String PROGRESS_QUERY = "api-query-progress";
    public static final long DELAY_TIME = 60000;

    private MutableLiveData<Double> mDonationTotal;
    private Timer mTimer;

    public MutableLiveData<Double> getDonationTotal(){
        if(mDonationTotal == null){
            mDonationTotal = new MutableLiveData<Double>();
        }
        return mDonationTotal;
    }

    /**
     * Retrofit call to retrieve the total donations - will be used to populate progress bar
     */
    public void retrieveDonationTotal() {
        JustGivingService justGivingService = JustGivingApiUtils.getJustGivingService();
        justGivingService.getDonationsTotal().enqueue(new Callback<DonationTotal>() {
            @Override
            public void onResponse(Call<DonationTotal> call, Response<DonationTotal> response) {
                Log.d(TAG, "onResponse: donationsTotal = " + response.body().getDonationsTotal());
                Double totalAmount = response.body().getDonationsTotal();
                if(!Objects.equals(mDonationTotal.getValue(), totalAmount)){
                    mDonationTotal.postValue(totalAmount);
                }
            }

            @Override
            public void onFailure(Call<DonationTotal> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    /**
     * Create a timer which will execute the API query on its own thread
     * every X amount of minutes
     */
    public void createTimer(){
        mTimer = new Timer(PROGRESS_QUERY);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //do api call and return result to handler
                retrieveDonationTotal();
                Log.d(TAG, "run: Timer Run");
            }
        };
        Log.d(TAG, "createTimer: Timer Created");
        mTimer.schedule(task, DELAY_TIME);
    }


}
