package com.fourllc.donate.model.justGivingModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationTotal {
    @SerializedName("DonationsTotal")
    @Expose
    private Double donationsTotal;
    @SerializedName("TotalEstimatedTaxReclaim")
    @Expose
    private Double totalEstimatedTaxReclaim;
    @SerializedName("ThirdPartyReference")
    @Expose
    private String thirdPartyReference;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("NumberOfDonations")
    @Expose
    private Integer numberOfDonations;

    public Double getDonationsTotal() {
        return donationsTotal;
    }

    public void setDonationsTotal(Double donationsTotal) {
        this.donationsTotal = donationsTotal;
    }

    public Double getTotalEstimatedTaxReclaim() {
        return totalEstimatedTaxReclaim;
    }

    public void setTotalEstimatedTaxReclaim(Double totalEstimatedTaxReclaim) {
        this.totalEstimatedTaxReclaim = totalEstimatedTaxReclaim;
    }

    public String getThirdPartyReference() {
        return thirdPartyReference;
    }

    public void setThirdPartyReference(String thirdPartyReference) {
        this.thirdPartyReference = thirdPartyReference;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getNumberOfDonations() {
        return numberOfDonations;
    }

    public void setNumberOfDonations(Integer numberOfDonations) {
        this.numberOfDonations = numberOfDonations;
    }

}
