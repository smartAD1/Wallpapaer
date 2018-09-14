package com.example.wallpaper.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/8/1.
 */

public class ProfileImage {
    @SerializedName("small")
    private String small;
    @SerializedName("medium")
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
