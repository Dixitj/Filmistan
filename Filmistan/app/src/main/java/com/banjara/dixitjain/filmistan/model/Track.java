package com.banjara.dixitjain.filmistan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Track {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getPlaycount() {
        return playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public String getUrl() {
        return url;
    }

    public Artist getArtist() {
        return artist;
    }

    public List<Image> getImage() {
        return image;
    }

}
