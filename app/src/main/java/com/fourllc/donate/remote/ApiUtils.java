package com.fourllc.donate.remote;
/**
 * Created by aaronbrecher on 3/3/18.
 * This class contains the basic building blocks for the retrofit interface as well
 * a function to return an instance of the retrofit interface.
 */

// TODO: 3/15/2018   externalize these constants
public class ApiUtils {
    //base url for the places API - need to add type=json...
    public static final String BASE_URL = "https://maps.googleapis.com";
    //api key for the places api is a free key son leaving open for now
    public static final String API_KEY = "AIzaSyCke80jFwmig6ULI7EduxmczFmy4SZo90k";

    public static PlacesService getPlacesService(){
        return RetrofitClient.getClient(BASE_URL).create(PlacesService.class);
    }
}
