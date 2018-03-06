package com.fourllc.donate.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fourllc.donate.BloodLocationsViewModel;
import com.fourllc.donate.MapUtils.LocationUtils;
import com.fourllc.donate.NetworkingUtils.NetworkingUtils;
import com.fourllc.donate.R;
import com.fourllc.donate.remote.ApiUtils;

/**
 * This class is used to get the list of blood donation centers based
 * on the current location
 */

public class NearCurrentLocationFragment extends Fragment {

    private FragmentActivity mActivity;
    private BloodLocationsViewModel mViewModel;
    private RecyclerView mPlacesList;
    private LinearLayout mNoConnectionErrorView;
    private LinearLayout mNoLocationErrorView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mViewModel = ViewModelProviders.of(mActivity).get(BloodLocationsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_blood_places_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get a reference to the recyclerView  & set it up
        mPlacesList = view.findViewById(R.id.donation_locations_rv);
        mPlacesList.setLayoutManager(new LinearLayoutManager(mActivity));
        mPlacesList.setHasFixedSize(true);
        mPlacesList.setAdapter(mViewModel.getAdapter());

        //get references to the error views
        mNoConnectionErrorView = (LinearLayout)view.findViewById(R.id.no_connection_error_view);
        mNoLocationErrorView = (LinearLayout)view.findViewById(R.id.location_error_view);

        //get the retrofit instance
        mViewModel.setService(ApiUtils.getPlacesService());

        //check that we have permission to access the location if we do not request the permission
        if (!LocationUtils.hasPermissions(mActivity)) {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LocationUtils.LOCATION_PERMISSION_REQUEST);
        }else{
            //get the users location and set the ViewModel's location
            mViewModel.setLocation(LocationUtils.getDeviceLocation(mActivity));
            //if the location is null show the noLocationErrorView
            if(mViewModel.getLocation() == null){
                mPlacesList.setVisibility(View.GONE);
                mNoLocationErrorView.setVisibility(View.VISIBLE);
            }
            //if there is no network connection show the no network error message
            else if (!NetworkingUtils.hasNetworkConnection(mActivity)){
                mPlacesList.setVisibility(View.GONE);
                mNoConnectionErrorView.setVisibility(View.VISIBLE);
            }
            //if all is good show the recyclerView and use the ViewModel to
            //query the Places API for donation centers
            else{
                showListView();
                mViewModel.getDonationLocations();
            }
        }

    }

    /**
     * Callback when user either denies or allows the location permission
     * will call a function to get the location or display a message to user
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LocationUtils.LOCATION_PERMISSION_REQUEST:
                if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    showListView();
                    mViewModel.getDonationLocations();
                }else {
                    mNoLocationErrorView.setVisibility(View.VISIBLE);
                }
        }

    }

    /**
     * Function that hides both error views and reveals the recycler view
     */
    private void showListView(){
        mNoConnectionErrorView.setVisibility(View.GONE);
        mNoLocationErrorView.setVisibility(View.GONE);
        mPlacesList.setVisibility(View.VISIBLE);
    }
}
