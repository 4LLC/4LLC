package com.fourllc.donate.model.justGivingModels.pageDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aaronbrecher on 4/23/18.
 */


public class Media {

    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("videos")
    @Expose
    private List<Object> videos = null;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Object> getVideos() {
        return videos;
    }

    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

}
