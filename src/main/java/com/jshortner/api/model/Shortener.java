package com.jshortner.api.model;

import org.hashids.Hashids;

public final class Shortener {
    private static Shortener shortener = null;

    private final Hashids hashids = new Hashids("This is a jShortner service", 3);

    private Shortener() {}

    public static synchronized final String shorten(Long id) {
        return getShortener().hashids.encode(id);
    }

    private static Shortener getShortener() {
        if (shortener == null) {
            shortener = new Shortener();
        }
        return shortener;
    }
}
