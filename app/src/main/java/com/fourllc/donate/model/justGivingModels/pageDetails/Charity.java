package com.fourllc.donate.model.justGivingModels.pageDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aaronbrecher on 4/23/18.
 */

public class Charity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("logoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("logoAbsoluteUrl")
    @Expose
    private String logoAbsoluteUrl;
    @SerializedName("profilePageUrl")
    @Expose
    private String profilePageUrl;
    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoAbsoluteUrl() {
        return logoAbsoluteUrl;
    }

    public void setLogoAbsoluteUrl(String logoAbsoluteUrl) {
        this.logoAbsoluteUrl = logoAbsoluteUrl;
    }

    public String getProfilePageUrl() {
        return profilePageUrl;
    }

    public void setProfilePageUrl(String profilePageUrl) {
        this.profilePageUrl = profilePageUrl;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}
