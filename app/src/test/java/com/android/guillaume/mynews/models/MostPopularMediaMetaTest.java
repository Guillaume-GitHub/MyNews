package com.android.guillaume.mynews.models;

import org.junit.Test;

import static org.junit.Assert.*;

//TODO: More Tests

public class MostPopularMediaMetaTest {

    MostPopularMediaMeta mostPopularMediaMeta = new MostPopularMediaMeta();

    @Test
    public void setAndGetUrl() {
        this.mostPopularMediaMeta.setUrl("https://www.google.com");
        assertEquals("https://www.google.com", this.mostPopularMediaMeta.getUrl());
    }
}