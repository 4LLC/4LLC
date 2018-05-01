package com.fourllc.donate.model.justGivingModels.pageDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aaronbrecher on 4/23/18.
 */

public class Branding {

    @SerializedName("buttonColour")
    @Expose
    private String buttonColour;
    @SerializedName("buttonTextColour")
    @Expose
    private String buttonTextColour;
    @SerializedName("headerTextColour")
    @Expose
    private String headerTextColour;
    @SerializedName("thermometerBackgroundColour")
    @Expose
    private String thermometerBackgroundColour;
    @SerializedName("thermometerFillColour")
    @Expose
    private String thermometerFillColour;
    @SerializedName("thermometerTextColour")
    @Expose
    private String thermometerTextColour;

    public String getButtonColour() {
        return buttonColour;
    }

    public void setButtonColour(String buttonColour) {
        this.buttonColour = buttonColour;
    }

    public String getButtonTextColour() {
        return buttonTextColour;
    }

    public void setButtonTextColour(String buttonTextColour) {
        this.buttonTextColour = buttonTextColour;
    }

    public String getHeaderTextColour() {
        return headerTextColour;
    }

    public void setHeaderTextColour(String headerTextColour) {
        this.headerTextColour = headerTextColour;
    }

    public String getThermometerBackgroundColour() {
        return thermometerBackgroundColour;
    }

    public void setThermometerBackgroundColour(String thermometerBackgroundColour) {
        this.thermometerBackgroundColour = thermometerBackgroundColour;
    }

    public String getThermometerFillColour() {
        return thermometerFillColour;
    }

    public void setThermometerFillColour(String thermometerFillColour) {
        this.thermometerFillColour = thermometerFillColour;
    }

    public String getThermometerTextColour() {
        return thermometerTextColour;
    }

    public void setThermometerTextColour(String thermometerTextColour) {
        this.thermometerTextColour = thermometerTextColour;
    }

}
