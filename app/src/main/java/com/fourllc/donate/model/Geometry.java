package com.fourllc.donate.model;
// TODO: 3/19/2018 remove all "source header' comments. there are unnecessary noise in the source code.
// TODO: 3/19/2018 consider using lombok to generate accessor methods at compile time.
/**
 * Created by aaronbrecher on 3/3/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("location")
    @Expose
    private PlacesLocation location;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;

    public PlacesLocation getLocation() {
        return location;
    }

    public void setLocation(PlacesLocation location) {
        this.location = location;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

}