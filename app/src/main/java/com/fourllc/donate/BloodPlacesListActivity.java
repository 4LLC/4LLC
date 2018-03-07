//package com.fourllc.donate;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationManager;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import com.fourllc.donate.remote.ApiUtils;
//
////TODO 1 check for internet Connection and display error message if not available
//public class BloodPlacesListActivity extends AppCompatActivity {
//
//    private static final String TAG = BloodPlacesListActivity.class.getSimpleName() ;
//    public static final int LOCATION_PERMISSION_REQUEST = 1001;
//
//    private BloodLocationsViewModel mViewModel;
//    private RecyclerView mPlacesList;
//    private LinearLayout mNoConnectionErrorView;
//    private LinearLayout mNoLocationErrorView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_blood_places_list);
//        mViewModel = ViewModelProviders.of(this)
//                .get(BloodLocationsViewModel.class);
//
//        //get a refrence to the recyclerView  & set it up
//        mPlacesList = findViewById(R.id.donation_locations_rv);
//        mPlacesList.setLayoutManager(new LinearLayoutManager(this));
//        mPlacesList.setHasFixedSize(true);
//        mPlacesList.setAdapter(mViewModel.getAdapter());
//
//        mNoConnectionErrorView = (LinearLayout)findViewById(R.id.no_connection_error_view);
//        mNoLocationErrorView = (LinearLayout)findViewById(R.id.location_error_view);
//
//        //get the retrofit instance
//        mViewModel.setService(ApiUtils.getPlacesService());
//
//        //check that we have permission to access the location if we do not request the permission
//        if (!hasPermissions()) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                    LOCATION_PERMISSION_REQUEST);
//        }else{
//            //get the users location
//            mViewModel.setLocation(getDeviceLocation());
//            //if the location is null show the noLocationErrorView
//            if(mViewModel.getLocation() == null){
//                mPlacesList.setVisibility(View.GONE);
//                mNoLocationErrorView.setVisibility(View.VISIBLE);
//            }
//            //if there is no network connection show the no network error message
//            else if (!hasNetworkConnection()){
//                mPlacesList.setVisibility(View.GONE);
//                mNoConnectionErrorView.setVisibility(View.VISIBLE);
//            }
//            //if all is good show the recyclerView and use the ViewModel to
//            //query the Places API for donation centers
//            else{
//                showListView();
//                mViewModel.getDonationLocations();
//            }
//        }
//    }
//
//    /**
//     * Callback when user either denies or allows the location permission
//     * will call a function to get the location or display a message to user
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case LOCATION_PERMISSION_REQUEST:
//                if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                    mViewModel.setLocation(getDeviceLocation());
//                    showListView();
//                    mViewModel.getDonationLocations();
//                }else {
//                    mNoLocationErrorView.setVisibility(View.VISIBLE);
//                }
//        }
//
//    }
//
//    /**
//     * function to check if there is network connection if not will reveal the error view
//     * @return true for has network false for no
//     */
//    private boolean hasNetworkConnection(){
//        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//        boolean isConnected = true;
//        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()){
//            isConnected = false;
//            //show the network unavailable view
//            mNoConnectionErrorView.setVisibility(View.VISIBLE);
//            mPlacesList.setVisibility(View.GONE);
//
//        }
//        return isConnected;
//    }
//
//    /**
//     * Function that hides both error views and reveals the recycler view
//     */
//    private void showListView(){
//        mNoConnectionErrorView.setVisibility(View.GONE);
//        mNoLocationErrorView.setVisibility(View.GONE);
//        mPlacesList.setVisibility(View.VISIBLE);
//    }
//
//    /**
//     * Simple function to check if we have location Permissions
//     * @return boolean true if permissions are granted false if not
//     */
//    private boolean hasPermissions(){
//        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    /**
//     * This function will get the device location suppressLint as we already checked
//     * permissions before calling this function
//     */
//    @SuppressLint("MissingPermission")
//    private Location getDeviceLocation(){
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        //set the ViewModel location variable
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        if(location == null) mViewModel.setLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
//        return location;
//    }
//
//}
