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

/**
 * Created by aaronbrecher on 3/6/18.
 */

public class LocationUtils {
    public static final int LOCATION_PERMISSION_REQUEST = 1001;
    private static Location mlastLocation;
    public static boolean hasPermissions(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * This function will get the device location suppressLint as we already checked
     * permissions before calling this function
     */
    @SuppressLint("MissingPermission")
    public static Location getDeviceLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //set the ViewModel location variable
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null)
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        return location;
    }

//    @SuppressLint("MissingPermission")
//    public static void getDeviceLocation(Context context){
//        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(context);
//        locationProviderClient.getLastLocation()
//                .addOnCompleteListener(new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        if(task.isSuccessful() && task.getResult() != null){
//                            mlastLocation = task.getResult();
//                        }
//                    }
//                });
//    }

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
        float distance = userLocation.distanceTo(destination)/1000;
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(distance) + "KM";
    }

}
