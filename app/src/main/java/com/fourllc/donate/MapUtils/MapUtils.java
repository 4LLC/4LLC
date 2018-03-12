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
 * Utility Class for setting up the mapView and doing related
 * functions
 */

public class MapUtils {
    /**
     * Setup the map with all the markers for the nearby locations. Will also set the camera
     * to include all the marked locations
     * @param googleMap a refrence to the GoogleMap
     * @param locations the list of nearby locations
     */
    public static void setUpMapLocations(GoogleMap googleMap, List<Result> locations){
        //make sure that there actually are locations before setting up map
        //setting up with no locations will cause a runtime error
        if(locations.size() > 1) {
            //setup a builder to create the bounds of all locations to place the camera
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            //iterate through all the locations and add a marker for each one
            for(Result location : locations){
                addMarker(location, googleMap, builder);
            }
            //build the bounds object and set the camera to contain those bounds TODO check there are points(markers)
            LatLngBounds bounds = builder.build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 100);
            googleMap.animateCamera(cameraUpdate);
        }
    }

    /**
     * Add the marker to the map
     * @param location the location(Result) object which will give the latlng of place
     * @param googleMap the map to set the marker on
     * @param builder the builder to create the bounds for the camera
     */
    private static void addMarker(Result location, GoogleMap googleMap, LatLngBounds.Builder builder){
        //get the lat lon object from the retrofit model
        PlacesLocation placesLocation = location.getGeometry().getLocation();
        //create a LatLng object to use to add a marker
        LatLng latLng = new LatLng(placesLocation.getLat(),placesLocation.getLng());
        builder.include(latLng);
        String locationName = location.getName();
        //add the marker to the map setting the name and address to be shown when marker is clicked
        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(locationName).snippet(location.getVicinity()));
        //add all the location data to the marker - will use to compare the marker
        marker.setTag(location);
    }
}
