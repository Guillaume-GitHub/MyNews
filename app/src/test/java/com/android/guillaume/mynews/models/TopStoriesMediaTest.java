package com.android.guillaume.mynews.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopStoriesMediaTest {

    private TopStoriesMedia media = new TopStoriesMedia();

    @Test
    public void getUrl() {
        String url = "https://www.google.fr";
        media.setUrl(url);
        assertEquals(url, media.getUrl());
    }

    @Test
    public void setUrl() {
        String url = "https://www.google.fr";
        media.setUrl(url);
        assertEquals(url, media.getUrl());
    }

    @Test
    public void getFormat() {
        String format = "Standard";
        media.setFormat(format);
        assertEquals(format, media.getFormat());
    }

    @Test
    public void setFormat() {
        String format = "Standard";
        media.setFormat(format);
        assertEquals(format, media.getFormat());
    }

    @Test
    public void getHeight() {
        int height = 100;
        media.setHeight(height);
        assertEquals(height,media.getHeight(),000.1);
    }

    @Test
    public void setHeight() {
        int height = 100;
        media.setHeight(height);
        assertEquals(height,media.getHeight(),000.1);
    }

    @Test
    public void getWidth() {
        int height = 100;
        media.setHeight(height);
        assertEquals(height,media.getHeight(),000.1);
    }

    @Test
    public void setWidth() {
        int width = 50;
        media.setWidth(width);
        assertEquals(width,media.getWidth(),000.1);
    }

    @Test
    public void getType() {
        String type = "image";
        media.setType(type);
        assertEquals(type, media.getType());
    }

    @Test
    public void setType() {
        String type = "image";
        media.setType(type);
        assertEquals(type, media.getType());
    }

    @Test
    public void getSubtype() {
        String subType = "photo";
        media.setSubtype(subType);
        assertEquals(subType, media.getSubtype());
    }

    @Test
    public void setSubtype() {
        String subType = "photo";
        media.setSubtype(subType);
        assertEquals(subType, media.getSubtype());
    }

    @Test
    public void getCaption() {
        String caption = "caption";
        media.setCaption(caption);
        assertEquals(caption, media.getCaption());
    }

    @Test
    public void setCaption() {
        String caption = "caption";
        media.setCaption(caption);
        assertEquals(caption, media.getCaption());
    }

    @Test
    public void getCopyright() {
        String copyright = "copyright";
        media.setCopyright(copyright);
        assertEquals(copyright, media.getCopyright());
    }

    @Test
    public void setCopyright() {
        String copyright = "copyright";
        media.setCopyright(copyright);
        assertEquals(copyright, media.getCopyright());
    }
}