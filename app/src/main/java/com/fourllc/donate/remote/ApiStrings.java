package com.fourllc.donate.remote;

/**
 * All Constants related to the API calls
 */

public class ApiStrings {
    //base url for the places API - need to add type=json...
    public static final String BASE_URL = "https://maps.googleapis.com";
    //api key for the places api is a free key son leaving open for now
    public static final String API_KEY = "AIzaSyCke80jFwmig6ULI7EduxmczFmy4SZo90k";

    public static final String SEARCH_TERM = "/maps/api/place/nearbysearch/json?&radius=40000&keyword=blood+donation+center&key=";
}
