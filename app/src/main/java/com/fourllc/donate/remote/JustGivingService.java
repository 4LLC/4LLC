package com.fourllc.donate.remote;

import com.fourllc.donate.model.justGivingModels.Donation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aaronbrecher on 4/10/18.
 */

public interface JustGivingService {
    @GET(JustGivingApiUtils.API_DONATION_BY_ID + "/{donationId}")
    Call<Donation>getDonationById(@Path("donationId") String id);
}
