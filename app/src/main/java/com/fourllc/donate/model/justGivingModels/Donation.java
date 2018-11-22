package com.fourllc.donate.model.justGivingModels;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by aaronbrecher on 4/10/18.
 */

public class Donation {
    private String amount;
    private String currencyCode;
    private String donationDate;
    private String donationRef;
    private String donorDisplayName;
    private String donorLocalAmount;
    private String donorLocalCurrencyCode;
    private Integer id;
    private String image;
    private String message;
    private String source;
    private String status;
    private String thirdPartyReference;
    private Integer charityId;
    private Integer eventId;
    private String pageShortName;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDonationDate() {
        long unixDate = Long.parseLong(donationDate.substring(6, donationDate.length()-7)) ;
        return convertAndFormatDate(unixDate);
    }

    private String convertAndFormatDate(long unixDate){
        TimeZone local = TimeZone.getDefault();
        int offset = local.getOffset(unixDate);
        Date date = new Date(unixDate + offset);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss a", Locale.getDefault());
        return dateFormatter.format(date);
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonationRef() {
        return donationRef;
    }

    public void setDonationRef(String donationRef) {
        this.donationRef = donationRef;
    }

    public String getDonorDisplayName() {
        return donorDisplayName;
    }

    public void setDonorDisplayName(String donorDisplayName) {
        this.donorDisplayName = donorDisplayName;
    }

    public String getDonorLocalAmount() {
        DecimalFormat df = new DecimalFormat("#.##");
        Float num = Float.parseFloat(donorLocalAmount);
        return df.format(num);
    }

    public void setDonorLocalAmount(String donorLocalAmount) {
        this.donorLocalAmount = donorLocalAmount;
    }

    public String getDonorLocalCurrencyCode() {
        return donorLocalCurrencyCode;
    }

    public void setDonorLocalCurrencyCode(String donorLocalCurrencyCode) {
        this.donorLocalCurrencyCode = donorLocalCurrencyCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThirdPartyReference() {
        return thirdPartyReference;
    }

    public void setThirdPartyReference(String thirdPartyReference) {
        this.thirdPartyReference = thirdPartyReference;
    }

    public Integer getCharityId() {
        return charityId;
    }

    public void setCharityId(Integer charityId) {
        this.charityId = charityId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getPageShortName() {
        return pageShortName;
    }

    public void setPageShortName(String pageShortName) {
        this.pageShortName = pageShortName;
    }
}