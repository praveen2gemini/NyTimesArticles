package com.nytimes.apibase.services.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Results {

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
}
	