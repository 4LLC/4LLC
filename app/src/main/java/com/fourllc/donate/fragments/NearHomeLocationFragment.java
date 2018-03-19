package com.fourllc.donate.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class NearHomeLocationFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    // TODO: 3/15/2018  if this is only used in onCreate then it should be a local variable.
    private BloodLocationsViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(BloodLocationsViewModel.class);
        setUpSharedPreferences();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_near_home_location, container, false);
    }


    private void setUpSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        loadHomeAddressFromPreferences(sharedPreferences);
    }

    // TODO: 3/15/2018 what does this method do?
    private void loadHomeAddressFromPreferences(SharedPreferences sharedPreferences) {
        String homeAddress = sharedPreferences.getString(getString(R.string.pref_home_address_key), getString(R.string.pref_home_address_default));
        //mViewModel.setmHomeAddress(homeAddress);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.pref_home_address_key))) {
            setUpSharedPreferences();
        }
    }
}
