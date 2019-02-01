package com.android.guillaume.mynews.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

//TODO: More Tests

public class MostPopularMediaTest {

    MostPopularMedia mostPopularMedia = new MostPopularMedia();

    @Test
    public void setAndGetMediaMetadata() {
        List<MostPopularMediaMeta> mostPopularMediaMetas = new ArrayList<>();
        this.mostPopularMedia.setMediaMetadata(mostPopularMediaMetas);
        assertEquals(mostPopularMediaMetas, mostPopularMedia.getMediaMetadata());
    }
}