package com.fourllc.donate.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fourllc.donate.BloodLocationsViewModel;
import com.fourllc.donate.Adapters.BloodPlacesRecyclerAdapter;
import com.fourllc.donate.MapUtils.LocationUtils;
import com.fourllc.donate.NetworkingUtils.NetworkingUtils;
import com.fourllc.donate.R;
import com.fourllc.donate.databinding.ActivityBloodPlacesListBinding;
import com.fourllc.donate.model.PlacesLocation;
import com.fourllc.donate.model.Result;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * This class is used to get the list of blood donation centers based
 * on the current location
 */

public class CurrentLocationListFragment extends Fragment implements BloodPlacesRecyclerAdapter.OnItemClickListener {

    private FragmentActivity mActivity;
    private BloodLocationsViewModel mViewModel;
    private BloodPlacesRecyclerAdapter mAdapter;
    private ActivityBloodPlacesListBinding binding;
    private Location mLocation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mViewModel = ViewModelProviders.of(mActivity).get(BloodLocationsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_blood_places_list, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set up recyclerView using dataBinding object
        binding.donationLocationsRv.setLayoutManager(new LinearLayoutManager(mActivity));
        binding.donationLocationsRv.setHasFixedSize(true);

        //initialize the adapter to an empty adapter and set it up for the recyclerView
        mAdapter = new BloodPlacesRecyclerAdapter(null, null, this);
        binding.donationLocationsRv.setAdapter(mAdapter);

        //set up an observer to check if the locations data in the ViewModel changes
        //if it does update the adapter
        mViewModel.getLocationsNearCurrent().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> locations) {
                //if the dataset changed update the adapter with the new results
                mAdapter.updateLocations(locations, mViewModel.getCurrentLocation());
            }
        });

        //check that we have permission to access the location if we do not request the permission
        if (!LocationUtils.hasPermissions(mActivity)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LocationUtils.LOCATION_PERMISSION_REQUEST);
        }else{
            //setUpUi();
            getDeviceLocation(mActivity);
        }

        /**
         * Set up the try again button to request the location again from the user
         */
        binding.locationTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!LocationUtils.hasPermissions(mActivity)){
                    ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            LocationUtils.LOCATION_PERMISSION_REQUEST);
                } else{
                    getDeviceLocation(mActivity);
                }
            }
        });

    }

    /**
     * Function to set up the UI will either show error messages or will update
     * the ViewModel current location which in turn will update the list
     */
    private void setUpUi(){

        //if the location is null show the noLocationErrorView
        if(mLocation == null){
            binding.listViewLayout.setVisibility(View.GONE);
            binding.locationErrorView.setVisibility(View.VISIBLE);
        }
        //if there is no network connection show the no network error message
        else if (!NetworkingUtils.hasNetworkConnection(mActivity)){
            binding.listViewLayout.setVisibility(View.GONE);
            binding.noConnectionErrorView.setVisibility(View.VISIBLE);
        }
        //if all is good show the recyclerView and use the ViewModel to
        //query the Places API for donation centers
        else{
            mViewModel.setCurrentLocation(mLocation);
            showListView();
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
                    getDeviceLocation(mActivity);
                }else {
                    binding.locationErrorView.setVisibility(View.VISIBLE);
                }
        }

    }

    /**
     * This function will get the location using the new GooglePlayServices
     * Location API. On recieving the location will set up the UI
     * @param context
     */
    @SuppressLint("MissingPermission")
    private void getDeviceLocation(Context context){
        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        locationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Log.i(TAG, "onComplete: " + task.isSuccessful() + task.getResult());
                        if(task.isSuccessful() && task.getResult() != null){
                            mLocation = task.getResult();
                            CurrentLocationListFragment.this.setUpUi();
                        }
                    }
                });
    }

    /**
     * Function that hides both error views and reveals the recycler view
     */
    private void showListView(){
        binding.noConnectionErrorView.setVisibility(View.GONE);
        binding.locationErrorView.setVisibility(View.GONE);
        binding.listViewLayout.setVisibility(View.VISIBLE);
    }

    /**
     * on click function to launch google maps for the specific location
     * clicking the location will pull up a map zoomed into the Donation Center
     * for now this is commented out because this function disables the title, snippet,
     * and directions button that by default shows on click on marker
     * @param placeLocation The Result Object that has all the location Information
     */
    @Override
    public void onItemClick(Result placeLocation) {
        PlacesLocation location = placeLocation.getGeometry().getLocation();
        String latLon = location.getLat() + "," + location.getLng();
        String query = "geo:" + latLon + "?q=" + placeLocation.getVicinity();
        Uri intentUri = Uri.parse(query);
        Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
        Log.i(TAG, "onItemClick: " + query);
        startActivity(intent);
    }
}
