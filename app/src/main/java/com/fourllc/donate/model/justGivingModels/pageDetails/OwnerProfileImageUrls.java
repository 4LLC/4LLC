package com.fourllc.donate.model.justGivingModels.pageDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aaronbrecher on 4/23/18.
 */

public class OwnerProfileImageUrls {

    @SerializedName("OriginalSize")
    @Expose
    private String originalSize;
    @SerializedName("Size150x150Face")
    @Expose
    private String size150x150Face;

    public String getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(String originalSize) {
        this.originalSize = originalSize;
    }

    public String getSize150x150Face() {
        return size150x150Face;
    }

    public void setSize150x150Face(String size150x150Face) {
        this.size150x150Face = size150x150Face;
    }
}
