package com.nytimes.apibase.services.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Media {
    private String copyright;

    @SerializedName("media-metadata")
    private ArrayList<MediaMetaData> mediaMetaData;

    private String subtype;

    private String caption;

    private String type;

    @SerializedName("approved_for_syndication")
    private String approvedForSyndication;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArrayList<MediaMetaData> getMediaMetaData() {
        return mediaMetaData;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(String approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    @NonNull
    @Override
    public String toString() {
        return "Media [copyright = " + copyright + ", media-metadata = " + mediaMetaData + ", subtype = " + subtype + ", caption = " + caption + ", type = " + type + ", approvedForSyndication = " + approvedForSyndication + "]";
    }
}
			
			