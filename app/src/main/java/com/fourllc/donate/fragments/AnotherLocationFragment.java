package com.fourllc.donate.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fourllc.donate.BloodLocationsViewModel;
import com.fourllc.donate.MapUtils.LocationUtils;
import com.fourllc.donate.MapUtils.MapUtils;
import com.fourllc.donate.R;
import com.fourllc.donate.model.Result;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

/**
 * This class is used to get the list of blood donation centers based
 * on the location entered by the user.
 */

public class AnotherLocationFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "AnothreLocationFragment";
    private FragmentActivity mActivity;
    private BloodLocationsViewModel mViewModel;

    /**
     * Autocomplete places
     */
    private SupportPlaceAutocompleteFragment places;
    private AutocompleteFilter typeFilter;

    /**
     * Google Maps
     */
    private GoogleMap mGoogleMap;
    private SupportMapFragment mapFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();
        mViewModel = ViewModelProviders.of(mActivity).get(BloodLocationsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_another_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        places = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.search_locations_map);

        typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();

        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Location location = LocationUtils.getLocationFromAddress(place.getName().toString(), mActivity);
                if(location != null) {
                    mViewModel.setSearchLocation(location);
                }
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, status.getStatusMessage());
            }
        });

        places.setFilter(typeFilter);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mViewModel.getLocationsNearSearch().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> locations) {
                //if the dataset changed update the adapter with the new results
                MapUtils.setUpMapLocations(googleMap, locations);
            }
        });
    }
}
