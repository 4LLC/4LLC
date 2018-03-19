package com.fourllc.donate.model;
// TODO: 3/19/2018 remove all "source header' comments. there are unnecessary noise in the source code.
// TODO: 3/19/2018 consider using lombok to generate accessor methods at compile time.
/**
 * Created by aaronbrecher on 3/3/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewport {

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

}
