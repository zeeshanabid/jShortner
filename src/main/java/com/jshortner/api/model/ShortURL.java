package com.jshortner.api.model;

public class ShortURL {
    private final String url;
    private final String shortURL;

    public ShortURL(String url, String shortURL) {
        this.url      = url;
        this.shortURL = shortURL;
    }

    public String getUrl() {
        return url;
    }

    public String getShortURL() {
        return shortURL;
    }
}
