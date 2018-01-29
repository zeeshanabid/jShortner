package com.jshortner.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jshortner.api.request.ShortRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortController.class)
public class ShortControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shortenURL() throws Exception {
        ShortRequest r = new ShortRequest();
        r.setUrl("http://google.com");
        this.mvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(r)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'url':'http://google.com', 'shortURL':'/X1O'}"));

        r.setUrl("http://bing.com");
        this.mvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(r)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'url':'http://bing.com', 'shortURL':'/4Q0'}"));
    }

    @Test
    public void redirectURL() throws Exception {
        ShortRequest r = new ShortRequest();
        r.setUrl("http://google.com");
        this.mvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(r)));

        this.mvc.perform(get("/X1O"))
                .andExpect(status().isMovedPermanently())
                .andExpect(redirectedUrl("http://google.com"));

    }
}
