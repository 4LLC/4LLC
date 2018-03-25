package com.fourllc.donate.remote;
/**
 * This class contains the basic building blocks for the retrofit interface as well
 * a function to return an instance of the retrofit interface.
 */

public class ApiUtils {

    public static PlacesService getPlacesService(){
        return RetrofitClient.getClient(ApiStrings.BASE_URL).create(PlacesService.class);
    }
}
