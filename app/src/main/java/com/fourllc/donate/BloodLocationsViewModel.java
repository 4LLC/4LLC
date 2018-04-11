package com.fourllc.donate;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import android.util.Log;

import com.fourllc.donate.model.PlacesAnswerResponse;
import com.fourllc.donate.model.Result;
import com.fourllc.donate.remote.GoogleApiUtils;
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

    //Finals to be used to determine which dataset to update
    private static final int LOCATION_TYPE_SEARCH = 1;
    public static final int LOCATION_TYPE_CURRENT = 2;

    private Location mCurrentLocation;
    private Location mSearchLocation;
    private PlacesService mService;
    private MutableLiveData<List<Result>> mNearCurrentLocation;
    private MutableLiveData<List<Result>> mNearSearchLocation;
    //TODO add a variable to save a location on the map to use to focus on that
    //location when the user clicks on the corresponding list card will then use
    //that data to focus on the marker when we load the mapview. We then need to
    //empty that variable when the mapview goes out of focus


    public Location getSearchLocation() {
        return mSearchLocation;
    }

    public void setSearchLocation(Location searchLocation) {
        mSearchLocation = searchLocation;
        this.getDonationLocations(mSearchLocation, LOCATION_TYPE_SEARCH);
    }

    public Location getCurrentLocation() {
        return mCurrentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.mCurrentLocation = location;
        this.getDonationLocations(location, LOCATION_TYPE_CURRENT);
    }

    public PlacesService getService() {
        if(mService == null) mService = GoogleApiUtils.getPlacesService();
        return mService;
    }

    public void setService(PlacesService service) {
        this.mService = service;
    }

    public MutableLiveData<List<Result>> getLocationsNearCurrent(){
        if (mNearCurrentLocation == null){
            mNearCurrentLocation = new MutableLiveData<List<Result>>();
        }
        return mNearCurrentLocation;
    }

    public MutableLiveData<List<Result>> getLocationsNearSearch(){
        if(mNearSearchLocation == null){
            mNearSearchLocation = new MutableLiveData<List<Result>>();
        }
        return mNearSearchLocation;
    }

    /**
     * This method will use retrofit to query the google places API for all blood donation
     * centers near the users current location
     */
    public void getDonationLocations(Location location, int locationType){
        //create a string representation of the location to use in the query
        String locationString = location.getLatitude() + "," + location.getLongitude();
        this.getService().getPlaces(locationString).enqueue(new Callback<PlacesAnswerResponse>() {
            @Override
            public void onResponse(Call<PlacesAnswerResponse> call, Response<PlacesAnswerResponse> response) {
                if(response.isSuccessful()){
                    List<Result> locations = response.body().getResults();
                    //update the livedata set will update the correct set depending on the locationType param
                    if(locationType == LOCATION_TYPE_CURRENT) mNearCurrentLocation.setValue(locations);
                    else if (locationType == LOCATION_TYPE_SEARCH) mNearSearchLocation.setValue(locations);
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
