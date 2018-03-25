package com.fourllc.donate.MapUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.fourllc.donate.model.PlacesLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by aaronbrecher on 3/6/18.
 */

public class LocationUtils {
    public static final int LOCATION_PERMISSION_REQUEST = 1001;

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
        //get the distance in KM (system default)
        float distance = userLocation.distanceTo(destination)/1000;
        //TODO change this to check sharedPreferences if useMiles is true
        boolean useMiles = true;
        DecimalFormat df = new DecimalFormat("#");
        if(useMiles){
            double miles = kmToMiles(distance);
            return df.format(miles) + "\nMILES";
        }else{
            return df.format(distance) + "\nKM";
        }
    }

    private static double kmToMiles(float kilometers){
        return kilometers * .621;
    }

    /**
     * This function is used to get the location object (latitude & longitude)
     * from the address
     */
    public static Location getLocationFromAddress(String strAddress, Context context) {

        Geocoder coder = new Geocoder(context);
        List<Address> addressList;
        Location location = null;

        try {
            addressList = coder.getFromLocationName(strAddress, 5);
            if (addressList == null) {
                return null;
            }
            Address address = addressList.get(0);
            location = new Location(LocationManager.GPS_PROVIDER);
            location.setLatitude(address.getLatitude());
            location.setLongitude(address.getLongitude());
        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return location;
    }

}
