package com.fourllc.donate;

import android.arch.lifecycle.ViewModel;
import android.location.Location;
import android.util.Log;

import com.fourllc.donate.BloodPlacesRecyclerAdapter;
import com.fourllc.donate.model.PlacesAnswerResponse;
import com.fourllc.donate.model.Result;
import com.fourllc.donate.remote.PlacesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by aaronbrecher on 3/5/18.
 * ViewModel for the location lists fragment the ViewModel will contain all
 * the data as well as do all non UI related tasks
 */

public class BloodLocationsViewModel extends ViewModel{
    private Location mLocation;
    private PlacesService mService;
    private BloodPlacesRecyclerAdapter mAdapter;

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        this.mLocation = location;
    }

    public PlacesService getService() {
        return mService;
    }

    public void setService(PlacesService service) {
        this.mService = service;
    }

    public BloodPlacesRecyclerAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new BloodPlacesRecyclerAdapter(null, null);
        };
        return mAdapter;
    }

    public void setAdapter(BloodPlacesRecyclerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * This method will use retrofit to query the google places API for all blood donation
     * centers near the users current location
     */
    public void getDonationLocations(){
        //create a string representation of the location to use in the query
        String location = mLocation.getLatitude() + "," + mLocation.getLongitude();
        mService.getPlaces(location).enqueue(new Callback<PlacesAnswerResponse>() {
            @Override
            public void onResponse(Call<PlacesAnswerResponse> call, Response<PlacesAnswerResponse> response) {
                if(response.isSuccessful()){
                    List<Result> locations = response.body().getResults();
                    //updates the adapter with the list of locations(results) & the current location to be used to calculate distance
                    mAdapter.updateLocations(response.body().getResults(), mLocation);
                }
                //if the response failed for now we will log the code TODO fix this so if the response failed return
                // an error code so the UI can be updated some goes for on fail
                else {
                    int statusCode = response.code();
                    Log.i(TAG, "onResponse: error response code " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<PlacesAnswerResponse> call, Throwable t) {
                Log.d(TAG, "error loading from API");
            }
        });
    }
}
