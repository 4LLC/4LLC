package com.fourllc.donate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourllc.donate.model.Result;

import java.util.List;

/**
 * Created by aaronbrecher on 3/4/18.
 */

public class BloodPlacesRecyclerAdapter extends RecyclerView.Adapter<BloodPlacesRecyclerAdapter.ViewHolder>{
    private List<Result> mLocations;

    public BloodPlacesRecyclerAdapter(List<Result> locations) {
        mLocations = locations;
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

    }

    @Override
    public int getItemCount() {
        if (mLocations == null) {
            return 0;
        }
        return mLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
