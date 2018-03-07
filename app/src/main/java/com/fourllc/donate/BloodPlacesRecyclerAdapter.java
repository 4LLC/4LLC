package com.fourllc.donate;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fourllc.donate.MapUtils.LocationUtils;
import com.fourllc.donate.model.OpeningHours;
import com.fourllc.donate.model.PlacesLocation;
import com.fourllc.donate.model.Result;

import java.text.DecimalFormat;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by aaronbrecher on 3/4/18.
 */

public class BloodPlacesRecyclerAdapter extends RecyclerView.Adapter<BloodPlacesRecyclerAdapter.ViewHolder>{
    private List<Result> mLocations;
    private Location mCurrentLocation;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(Result placeLocation);
    }
    /**
     * The constructor for the adapter accepts 2 parameters
     * @param locations the list of locations from the retrofit api request see models for all elements
     *                  root element for the list is the Result class
     * @param currentLocation an Android Location object for the users location to be used to determine distance
     */
    public BloodPlacesRecyclerAdapter(List<Result> locations, Location currentLocation, OnItemClickListener listener) {
        mLocations = locations;
        mCurrentLocation = currentLocation;
        mListener = listener;
    }

    @Override
    public BloodPlacesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.blood_location_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BloodPlacesRecyclerAdapter.ViewHolder holder, int position) {
        Result currentPlace = mLocations.get(position);
        bindTextView(holder.mName, currentPlace.getName());
        bindTextView(holder.mAdress, currentPlace.getVicinity());
        bindTextView(holder.mHours, getHoursInfo(currentPlace.getOpeningHours()));
        bindTextView(holder.mRating, String.valueOf(currentPlace.getRating()));
        //get the distance to the location will use the android Location class to get distance
        String distance = LocationUtils.getDistance(mCurrentLocation, currentPlace.getGeometry().getLocation());
        bindTextView(holder.mDistance, distance);
    }

    @Override
    public int getItemCount() {
        if (mLocations == null) {
            return 0;
        }
        return mLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mDistance;
        private TextView mName;
        private TextView mAdress;
        private TextView mHours;
        private TextView mRating;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.blood_location_name);
            mAdress = itemView.findViewById(R.id.blood_location_address);
            mHours = itemView.findViewById(R.id.blood_location_hours);
            mDistance = itemView.findViewById(R.id.blood_location_distance);
            mRating = itemView.findViewById(R.id.blood_location_rating);
            itemView.setOnClickListener(this);
        }

        /**
         * onClick send the Result Object (which has all the location information) to
         * the listener to launch a map intent
         * @param view
         */
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Result result = BloodPlacesRecyclerAdapter.this.mLocations.get(position);
            mListener.onItemClick(result);
        }
    }

    /**
     * Simple function to bind supplied String to a textView
     * @param textView the text view to be bound
     * @param string the string to set the text value
     */
    private void bindTextView(TextView textView, String string){
        textView.setText(string);
    }

    /**
     * simple function to determine the string to display for the hours of
     * the location
     * @param openingHours the opening hours object as defined in the model (can be null)
     * @return string to display if there is info will be "open" or "closed" otherwise will be "hours not available"
     */
    private String getHoursInfo(OpeningHours openingHours){
        String openNow;
        if(openingHours != null){
            openNow = openingHours.getOpenNow() ? "Open Now" : "Closed";
        }else openNow = "Opening hours not available";
        return openNow;
    }

    /**
     * function to update the adapter with a new List of locations and the current location
     * @param locations - A list of Locations from places API
     * @param location - The current User location
     */
    public void updateLocations(List<Result> locations, Location location){
        mLocations = locations;
        mCurrentLocation = location;
        notifyDataSetChanged();
    }
}
