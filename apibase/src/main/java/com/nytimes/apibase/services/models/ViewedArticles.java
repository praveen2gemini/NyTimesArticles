package com.nytimes.apibase.services.models;


import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewedArticles {

    private String copyright;

    private ArrayList<Results> results;

    @SerializedName("num_results")
    private String numResults;

    private String status;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public String getNumResults() {
        return numResults;
    }

    public void setNumResults(String numResults) {
        this.numResults = numResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewedArticles [copyright = " + copyright + ", results = " + results + ", numResults = " + numResults + ", status = " + status + "]";
    }
}
			
	