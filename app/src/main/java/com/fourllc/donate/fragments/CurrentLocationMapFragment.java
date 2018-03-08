package com.fourllc.donate.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourllc.donate.BloodLocationsViewModel;
import com.fourllc.donate.R;

/**
 * This class is used to get the list of blood donation centers based
 * on the centers near the home location.
 */

public class CurrentLocationMapFragment extends Fragment{
    private BloodLocationsViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(BloodLocationsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_near_current_location_map, container, false);
    }


}
