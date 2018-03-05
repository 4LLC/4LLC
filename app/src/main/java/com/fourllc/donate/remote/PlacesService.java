package com.fourllc.donate.remote;


import com.fourllc.donate.model.PlacesAnswerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aaronbrecher on 3/3/18.
 * This will be the interface for all the api queries to be used
 * by retrofit. Will define here all the entry points to the places api
 */

/**
 * this end point will query google places for blood donations within a 5KM
 * radius of the supplied location
 * @Param location - will be the supplied location to search nearby
 */
public interface PlacesService {
    @GET("/maps/api/place/nearbysearch/json?&radius=40000&keyword=blood+donation+center&key=" + ApiUtils.API_KEY)
    Call<PlacesAnswerResponse> getPlaces(@Query("location") String location);
}
