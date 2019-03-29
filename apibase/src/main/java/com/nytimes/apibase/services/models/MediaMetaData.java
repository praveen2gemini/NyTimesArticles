package com.nytimes.apibase.services.models;

/**
 * @author Praveen on 29/03/19.
 */
public class MediaMetaData {
    private String format;

    private String width;

    private String url;

    private String height;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ClassPojo [format = " + format + ", width = " + width + ", url = " + url + ", height = " + height + "]";
    }
}
