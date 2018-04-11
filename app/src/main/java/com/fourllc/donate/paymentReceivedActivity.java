package com.fourllc.donate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fourllc.donate.model.justGivingModels.Donation;
import com.fourllc.donate.remote.GoogleApiUtils;
import com.fourllc.donate.remote.JustGivingApiUtils;
import com.fourllc.donate.remote.JustGivingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class paymentReceivedActivity extends AppCompatActivity {

    private JustGivingService justGivingService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_received);

        justGivingService = JustGivingApiUtils.getJustGivingService();

        Intent intent = getIntent();
        Uri data = intent.getData();
        String donationId = data.getQueryParameter(JustGivingApiUtils.DONATION_ID_PARAMETER);
        justGivingService.getDonationById(donationId).enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {
                if(response.isSuccessful()){
                    setUpUiWithDonation(response.body());
                }else {
                    int statusCode = response.code();
                    Log.i("API ERROR", "onResponse: error code is " +statusCode);
                }
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {
                Log.i("REQUEST FAILURE", "onFailure: request failed for the donation id");
            }
        });
    }

    private void setUpUiWithDonation(Donation donation) {

    }
}
