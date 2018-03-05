package com.fourllc.donate;

import android.Manifest;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fourllc.donate.model.PlacesAnswerResponse;
import com.fourllc.donate.model.Result;
import com.fourllc.donate.remote.ApiUtils;
import com.fourllc.donate.remote.BloodLocationsViewModel;
import com.fourllc.donate.remote.PlacesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//TODO 1 check for internet Connection and display error message if not available
public class BloodPlacesListActivity extends AppCompatActivity {

    private static final String TAG = BloodPlacesListActivity.class.getSimpleName() ;
    public static final int LOCATION_PERMISSION_REQUEST = 1001;

    private BloodLocationsViewModel mViewModel;
    private RecyclerView mPlacesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_places_list);
        mViewModel = ViewModelProviders.of(this)
                .get(BloodLocationsViewModel.class);

        //get a refrence to the recyclerView  & set it up
        mPlacesList = findViewById(R.id.donation_locations_rv);
        mPlacesList.setLayoutManager(new LinearLayoutManager(this));
        mPlacesList.setHasFixedSize(true);
        mPlacesList.setAdapter(mViewModel.getAdapter());

        //get the retrofit instance
        mViewModel.setService(ApiUtils.getPlacesService());

        //check that we have permission to access the location if we do not request the permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST);
        }else{
            getDeviceLocationOrInformUser();
        }
    }

    /**
     * Callback when user either denies or allows the location permission
     * will call a function to get the location or display a message to user
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST:
                this.getDeviceLocationOrInformUser();
                return;
        }

    }

    /**
     * This function will get the device location or in case the permissions was denied
     * show the user a message that the feature was disabled due denied permissions
     */
    private void getDeviceLocationOrInformUser(){
        //check again that we have permissions and if so get the location information
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //set the ViewModel location variable
            mViewModel.setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            //TODO 2 check if GPS is not available if so use the network location
            if (mViewModel.getLocation() != null){
                //call ViewModel getDonationLocations to get all nearby blood centers this will update the UI
                // by changing the adapter's List
                mViewModel.getDonationLocations();
            }else{
                Log.d(TAG, "getDeviceLocationOrInformUser: location was null!");
            }
        }
        //if request was not granted explain that this functionality will not work
        else{
            //TODO inform user that this will not work without the location services
        }
    }

}
