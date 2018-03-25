package com.fourllc.donate.remote;


import com.fourllc.donate.model.PlacesAnswerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This will be the interface for all the api queries to be used
 * by retrofit. Will define here all the entry points to the places api
 */

/**
 * this end point will query google places for blood donations within a 5KM
 * radius of the supplied location
 * @Param location - will be the supplied location to search nearby
 */
public interface PlacesService {
    @GET(ApiStrings.SEARCH_TERM + ApiStrings.API_KEY)
    Call<PlacesAnswerResponse> getPlaces(@Query("location") String location);
}
