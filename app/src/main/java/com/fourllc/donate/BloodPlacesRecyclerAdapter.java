package com.fourllc.donate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fourllc.donate.model.Result;

import java.util.List;

/**
 * Created by aaronbrecher on 3/4/18.
 */

public class BloodPlacesRecyclerAdapter extends RecyclerView.Adapter<BloodPlacesRecyclerAdapter.ViewHolder>{

    public BloodPlacesRecyclerAdapter(List<Result> resultList) {
    }

    @Override
    public BloodPlacesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BloodPlacesRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
