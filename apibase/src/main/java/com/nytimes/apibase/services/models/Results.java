package com.nytimes.apibase.services.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Results implements Parcelable {

    @SerializedName("per_facet")
    private ArrayList<String> perFacet;


    @SerializedName("org_facet")
    private ArrayList<String> orgFacet;


    @SerializedName("column")
    private String column;

    private String section;

    @SerializedName("abstract")
    private String abstractDetails;

    private String source;

    @SerializedName("asset_id")
    private String assetId;

    private ArrayList<Media> media;

    private String type;

    private String title;


    @SerializedName("des_facet")
    private ArrayList<String> desFacet;

    private String uri;

    private String url;

    @SerializedName("adx_keywords")
    private String adxKeywords;


    @SerializedName("geo_facet")
    private ArrayList<String> geoFacet;

    private String id;

    private String byline;

    @SerializedName("published_date")
    private String publishedDate;

    private String views;

    protected Results(Parcel in) {
        perFacet = in.createStringArrayList();
        orgFacet = in.createStringArrayList();
        column = in.readString();
        section = in.readString();
        abstractDetails = in.readString();
        source = in.readString();
        assetId = in.readString();
        type = in.readString();
        title = in.readString();
        desFacet = in.createStringArrayList();
        uri = in.readString();
        url = in.readString();
        adxKeywords = in.readString();
        geoFacet = in.createStringArrayList();
        id = in.readString();
        byline = in.readString();
        publishedDate = in.readString();
        views = in.readString();
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    public String getColumn() {
        return column;
    }

    public ArrayList<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(ArrayList<String> perFacet) {
        this.perFacet = perFacet;
    }

    public ArrayList<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(ArrayList<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAbstract() {
        return abstractDetails;
    }

    public void setAbstract(String abstractDetails) {
        this.abstractDetails = abstractDetails;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(ArrayList<String> desFacet) {
        this.desFacet = desFacet;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public ArrayList<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(ArrayList<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    @NonNull
    @Override
    public String toString() {
        return "Results [perFacet = " + perFacet + ", orgFacet = " + orgFacet + ", column = " + column + ", section = " + section + ", abstract = " + abstractDetails + ", source = " + source + ", assetId = " + assetId + ", media = " + media + ", type = " + type + ", title = " + title + ", desFacet = " + desFacet + ", uri = " + uri + ", url = " + url + ", adxKeywords = " + adxKeywords + ", geoFacet = " + geoFacet + ", id = " + id + ", byline = " + byline + ", publishedDate = " + publishedDate + ", views = " + views + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(perFacet);
        dest.writeStringList(orgFacet);
        dest.writeString(column);
        dest.writeString(section);
        dest.writeString(abstractDetails);
        dest.writeString(source);
        dest.writeString(assetId);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeStringList(desFacet);
        dest.writeString(uri);
        dest.writeString(url);
        dest.writeString(adxKeywords);
        dest.writeStringList(geoFacet);
        dest.writeString(id);
        dest.writeString(byline);
        dest.writeString(publishedDate);
        dest.writeString(views);
    }
}
	