package com.fourllc.donate;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fourllc.donate.model.PlacesAnswerResponse;
import com.fourllc.donate.model.Result;
import com.fourllc.donate.remote.ApiUtils;
import com.fourllc.donate.remote.PlacesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodPlacesListActivity extends AppCompatActivity {

    private static final String TAG = BloodPlacesListActivity.class.getSimpleName() ;
    private String mLocation;
    private PlacesService mService;
    private List<Result> mResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_places_list);

        //get the retrofit instance
        mService = ApiUtils.getPlacesService();

        //check that we have permission to access the location if we do not request the permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }else{
            getDeviceLocationOrInformUser();
        }
    }

    private void getDeviceLocationOrInformUser(){
        //check again that we have permissions and if so get the location information
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //save the location as a comma seperated lat,lon to use by the places api
            mLocation = location.getLatitude() + "," + location.getLongitude();
        }
        //if request was not granted explain that this functionality will not work
        else{
            //TODO inform user that this will not work without the location services
        }
    }

    private void getLocations(){
        mService.getPlaces(mLocation).enqueue(new Callback<PlacesAnswerResponse>() {
            @Override
            public void onResponse(Call<PlacesAnswerResponse> call, Response<PlacesAnswerResponse> response) {
                if(response.isSuccessful()){
                    //TODO this is temp change this to update the adapter
                    mResults = response.body().getResults();
                } else {
                    int statusCode = response.code();
                    Log.d(TAG, "onResponse: error response code " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<PlacesAnswerResponse> call, Throwable t) {

            }
        });
    }
}
