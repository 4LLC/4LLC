package com.fourllc.donate.MapUtils;

import com.fourllc.donate.model.PlacesLocation;
import com.fourllc.donate.model.Result;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by aaronbrecher on 3/8/18.
 */

public class MapUtils {
    public static void setUpMapLocations(GoogleMap googleMap, List<Result> locations){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(Result location : locations){
            addMarker(location, googleMap, builder);
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 100);
        googleMap.animateCamera(cameraUpdate);
    }

    private static void addMarker(Result location, GoogleMap googleMap, LatLngBounds.Builder builder){
        //get the lat lon object from the retrofit model
        PlacesLocation placesLocation = location.getGeometry().getLocation();
        //create a LatLng object to use to add a marker
        LatLng latLng = new LatLng(placesLocation.getLat(),placesLocation.getLng());
        builder.include(latLng);
        String locationName = location.getName();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(locationName).snippet(location.getVicinity()));
        marker.setTag(location);
    }
}
