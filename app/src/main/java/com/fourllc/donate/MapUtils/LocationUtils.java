package com.fourllc.donate.MapUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.fourllc.donate.model.PlacesLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.DecimalFormat;



public class LocationUtils {
    public static final int LOCATION_PERMISSION_REQUEST = 1001;

    public static boolean hasPermissions(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    // TODO: 3/19/2018 instead or suppressing the compiler error, how about checking for permissions here...
    /**
     * This function will get the device location suppressLint as we already checked
     * permissions before calling this function
     */
    @SuppressLint("MissingPermission")
    public static Location getDeviceLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //set the ViewModel location variable
        if (locationManager != null) {
            Location  location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return location != null ? location : locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        return null;
    }


    /**
     * Method to calculate the distance from users location to the current donation center
     * @param userLocation Location object with lat/lon of the user
     * @param donationLocation - The location object from the model with the lat/lon of donation center (this is NOT and android
     *                         location and will be converted in this function!!)
     * @return the distance between the two locations as a string formatted to one decimal point
     */
    public static String getDistance(Location userLocation, PlacesLocation donationLocation) {
        Location destination = new Location("");
        destination.setLatitude(donationLocation.getLat());
        destination.setLongitude(donationLocation.getLng());
        // TODO: 3/15/2018  why write a method to calculate kmToMiles and not one to calculate distance...

        float distance = userLocation.distanceTo(destination)/1000;
        // TODO: 3/15/2018 useMiles is always true.
        // TODO: 3/15/2018 the decimalFormat doesn't change based on the value of useMiles, therefore it should
        // be outside of the decision block..
        boolean useMiles = true;
        if(useMiles){
            double miles = kmToMiles(distance);
            DecimalFormat df = new DecimalFormat("#");
            return df.format(miles) + "\nMILES";
        }else{
            DecimalFormat df = new DecimalFormat("#");
            return df.format(distance) + "\nKM";
        }
    }

    private static double kmToMiles(float kilometers){
        return kilometers * .621;
    }

}
