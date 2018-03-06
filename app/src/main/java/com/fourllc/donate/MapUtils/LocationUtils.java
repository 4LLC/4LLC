package com.fourllc.donate.MapUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

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
}
