package com.fourllc.donate.model;
// TODO: 3/19/2018 remove all "source header' comments. there are unnecessary noise in the source code.
// TODO: 3/19/2018 consider using lombok to generate accessor methods at compile time.
/**
 * Created by aaronbrecher on 3/3/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Northeast {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}