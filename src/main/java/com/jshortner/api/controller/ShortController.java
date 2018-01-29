package com.jshortner.api.controller;

import com.jshortner.api.exception.NotFoundException;
import com.jshortner.api.model.ShortURL;
import com.jshortner.api.model.Shortener;
import com.jshortner.api.request.ShortRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ShortController {
    private final AtomicLong          counter = new AtomicLong();
    private final Map<String, String> urls    = new HashMap<>();

    @RequestMapping(path = "/shorten", method = RequestMethod.POST)
    public ShortURL shortenURL(@RequestBody ShortRequest request) {
        String hash = Shortener.shorten(counter.incrementAndGet());
        this.urls.put(hash, request.getUrl());
        return new ShortURL(request.getUrl(), "/" + hash);
    }

    @RequestMapping(path = "/{hash}", method = RequestMethod.GET)
    public RedirectView redirectURL(@PathVariable String hash) throws NotFoundException {
        if (!this.urls.containsKey(hash)) {
            throw new NotFoundException();
        }
        RedirectView rv = new RedirectView(this.urls.get(hash));
        rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return rv;
    }
}
