package com.example.wallpaper.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/8/1.
 */

public class Photo {
    //@SerializedName字串轉換
    @SerializedName("id")
    private String id;
    @SerializedName("description")
    private String description;
    @SerializedName("urls")
    private PhotoUrl url = new PhotoUrl();
    @SerializedName("user")
    private  User user = new User();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoUrl getUrl() {
        return url;
    }

    public void setUrl(PhotoUrl url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
