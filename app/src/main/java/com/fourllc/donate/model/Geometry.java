package com.fourllc.donate.model;


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