package com.fourllc.donate.model.justGivingModels.pageDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aaronbrecher on 4/23/18.
 */

public class PageDetails {

    @SerializedName("pageId")
    @Expose
    private String pageId;
    @SerializedName("activityCharityCreated")
    @Expose
    private Boolean activityCharityCreated;
    @SerializedName("activityType")
    @Expose
    private String activityType;
    @SerializedName("activityId")
    @Expose
    private String activityId;
    @SerializedName("attribution")
    @Expose
    private String attribution;
    @SerializedName("eventName")
    @Expose
    private String eventName;
    @SerializedName("eventId")
    @Expose
    private Integer eventId;
    @SerializedName("currencySymbol")
    @Expose
    private String currencySymbol;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("pageShortName")
    @Expose
    private String pageShortName;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("imageCount")
    @Expose
    private String imageCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("ownerProfileImageUrls")
    @Expose
    private OwnerProfileImageUrls ownerProfileImageUrls;
    @SerializedName("consumerId")
    @Expose
    private Integer consumerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("showEventDate")
    @Expose
    private String showEventDate;
    @SerializedName("eventDate")
    @Expose
    private String eventDate;
    @SerializedName("showExpiryDate")
    @Expose
    private String showExpiryDate;
    @SerializedName("expiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("fundraisingTarget")
    @Expose
    private String fundraisingTarget;
    @SerializedName("totalRaisedPercentageOfFundraisingTarget")
    @Expose
    private String totalRaisedPercentageOfFundraisingTarget;
    @SerializedName("totalRaisedOffline")
    @Expose
    private String totalRaisedOffline;
    @SerializedName("totalRaisedOnline")
    @Expose
    private String totalRaisedOnline;
    @SerializedName("totalRaisedSms")
    @Expose
    private String totalRaisedSms;
    @SerializedName("grandTotalRaisedExcludingGiftAid")
    @Expose
    private String grandTotalRaisedExcludingGiftAid;
    @SerializedName("totalEstimatedGiftAid")
    @Expose
    private String totalEstimatedGiftAid;
    @SerializedName("branding")
    @Expose
    private Branding branding;
    @SerializedName("charity")
    @Expose
    private Charity charity;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("story")
    @Expose
    private String story;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("smsCode")
    @Expose
    private Object smsCode;
    @SerializedName("companyAppealId")
    @Expose
    private Integer companyAppealId;
    @SerializedName("rememberedPersonSummary")
    @Expose
    private RememberedPersonSummary rememberedPersonSummary;
    @SerializedName("pageSummaryWhat")
    @Expose
    private Object pageSummaryWhat;
    @SerializedName("pageSummaryWhy")
    @Expose
    private Object pageSummaryWhy;
    @SerializedName("teams")
    @Expose
    private List<Object> teams = null;
    @SerializedName("pageSummary")
    @Expose
    private String pageSummary;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Boolean getActivityCharityCreated() {
        return activityCharityCreated;
    }

    public void setActivityCharityCreated(Boolean activityCharityCreated) {
        this.activityCharityCreated = activityCharityCreated;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPageShortName() {
        return pageShortName;
    }

    public void setPageShortName(String pageShortName) {
        this.pageShortName = pageShortName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImageCount() {
        return imageCount;
    }

    public void setImageCount(String imageCount) {
        this.imageCount = imageCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public OwnerProfileImageUrls getOwnerProfileImageUrls() {
        return ownerProfileImageUrls;
    }

    public void setOwnerProfileImageUrls(OwnerProfileImageUrls ownerProfileImageUrls) {
        this.ownerProfileImageUrls = ownerProfileImageUrls;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowEventDate() {
        return showEventDate;
    }

    public void setShowEventDate(String showEventDate) {
        this.showEventDate = showEventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getShowExpiryDate() {
        return showExpiryDate;
    }

    public void setShowExpiryDate(String showExpiryDate) {
        this.showExpiryDate = showExpiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFundraisingTarget() {
        return fundraisingTarget;
    }

    public void setFundraisingTarget(String fundraisingTarget) {
        this.fundraisingTarget = fundraisingTarget;
    }

    public String getTotalRaisedPercentageOfFundraisingTarget() {
        return totalRaisedPercentageOfFundraisingTarget;
    }

    public void setTotalRaisedPercentageOfFundraisingTarget(String totalRaisedPercentageOfFundraisingTarget) {
        this.totalRaisedPercentageOfFundraisingTarget = totalRaisedPercentageOfFundraisingTarget;
    }

    public String getTotalRaisedOffline() {
        return totalRaisedOffline;
    }

    public void setTotalRaisedOffline(String totalRaisedOffline) {
        this.totalRaisedOffline = totalRaisedOffline;
    }

    public String getTotalRaisedOnline() {
        return totalRaisedOnline;
    }

    public void setTotalRaisedOnline(String totalRaisedOnline) {
        this.totalRaisedOnline = totalRaisedOnline;
    }

    public String getTotalRaisedSms() {
        return totalRaisedSms;
    }

    public void setTotalRaisedSms(String totalRaisedSms) {
        this.totalRaisedSms = totalRaisedSms;
    }

    public String getGrandTotalRaisedExcludingGiftAid() {
        return grandTotalRaisedExcludingGiftAid;
    }

    public void setGrandTotalRaisedExcludingGiftAid(String grandTotalRaisedExcludingGiftAid) {
        this.grandTotalRaisedExcludingGiftAid = grandTotalRaisedExcludingGiftAid;
    }

    public String getTotalEstimatedGiftAid() {
        return totalEstimatedGiftAid;
    }

    public void setTotalEstimatedGiftAid(String totalEstimatedGiftAid) {
        this.totalEstimatedGiftAid = totalEstimatedGiftAid;
    }

    public Branding getBranding() {
        return branding;
    }

    public void setBranding(Branding branding) {
        this.branding = branding;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Object getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(Object smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getCompanyAppealId() {
        return companyAppealId;
    }

    public void setCompanyAppealId(Integer companyAppealId) {
        this.companyAppealId = companyAppealId;
    }

    public RememberedPersonSummary getRememberedPersonSummary() {
        return rememberedPersonSummary;
    }

    public void setRememberedPersonSummary(RememberedPersonSummary rememberedPersonSummary) {
        this.rememberedPersonSummary = rememberedPersonSummary;
    }

    public Object getPageSummaryWhat() {
        return pageSummaryWhat;
    }

    public void setPageSummaryWhat(Object pageSummaryWhat) {
        this.pageSummaryWhat = pageSummaryWhat;
    }

    public Object getPageSummaryWhy() {
        return pageSummaryWhy;
    }

    public void setPageSummaryWhy(Object pageSummaryWhy) {
        this.pageSummaryWhy = pageSummaryWhy;
    }

    public List<Object> getTeams() {
        return teams;
    }

    public void setTeams(List<Object> teams) {
        this.teams = teams;
    }

    public String getPageSummary() {
        return pageSummary;
    }

    public void setPageSummary(String pageSummary) {
        this.pageSummary = pageSummary;
    }

}
