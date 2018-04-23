package com.fourllc.donate.remote;

import com.fourllc.donate.model.justGivingModels.Donation;
import com.fourllc.donate.model.justGivingModels.DonationTotal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by aaronbrecher on 4/10/18.
 */

public interface JustGivingService {
    @Headers({
            "x-api-key:" + JustGivingApiUtils.API_SECRET_KEY,
            "Accept: application/json"
    })
    @GET(JustGivingApiUtils.API_DONATION_BY_ID + "{donationId}")
    Call<Donation>getDonationById(@Path("donationId") String id);

    @Headers({
            "x-api-key:" + JustGivingApiUtils.API_SECRET_KEY,
            "Accept: application/json"
    })
    @GET(JustGivingApiUtils.API_DONATION_TOTAL)
    Call<DonationTotal>getDonationsTotal();
}
