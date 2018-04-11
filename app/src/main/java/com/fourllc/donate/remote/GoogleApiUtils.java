package com.fourllc.donate.remote;
/**
 * Created by aaronbrecher on 3/3/18.
 * This class contains the basic building blocks for the retrofit interface as well
 * a function to return an instance of the retrofit interface.
 */

public class GoogleApiUtils {
    /**
     * Static strings for the google maps api
     */
    private static final String GOOGLE_API_BASE_URL = "https://maps.googleapis.com";
    public static final String GOOGLE_API_KEY = "AIzaSyCke80jFwmig6ULI7EduxmczFmy4SZo90k";
    private static final String PLACES_PATH_BASE = "/maps/api/place/nearbysearch/json?";
    private static final String PLACES_PATH_RADIUS = "&radius=40000";
    private static final String PLACES_PATH_KEYWORD = "&keyword=blood+donation+center";
    private static final String PLACES_PATH_API_KEY = "&key=" + GOOGLE_API_KEY;
    public static final String PLACES_PATH = PLACES_PATH_BASE + PLACES_PATH_RADIUS + PLACES_PATH_KEYWORD + PLACES_PATH_API_KEY;

    public static PlacesService getPlacesService(){
        return RetrofitClient.getClient(GOOGLE_API_BASE_URL).create(PlacesService.class);
    }


}
