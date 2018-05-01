package com.fourllc.donate.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourllc.donate.BloodLocationsViewModel;
import com.fourllc.donate.MapUtils.MapUtils;
import com.fourllc.donate.R;
import com.fourllc.donate.model.GooglePlacesModels.Result;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

/**
 * This class is used to get the list of blood donation centers based
 * on the centers near the home location.
 */

public class CurrentLocationMapFragment extends Fragment implements OnMapReadyCallback {
    private BloodLocationsViewModel mViewModel;
    private GoogleMap mGoogleMap;
    SupportMapFragment mapFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_near_current_location_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(BloodLocationsViewModel.class);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.locations_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //set up an observer to check if the locations data in the ViewModel changes
        //if it does update the adapter
        mGoogleMap = googleMap;
//        mGoogleMap.setOnMarkerClickListener(this);
        mViewModel.getLocationsNearCurrent().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> locations) {
                //if the dataset changed update the adapter with the new results
                MapUtils.setUpMapLocations(googleMap, locations);
            }
        });
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        Log.i(TAG, "onMarkerClick: marker clicked");
//        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
//        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1000, null);
//        return false;
//    }
}
