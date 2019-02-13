package com.android.guillaume.mynews.models.articleSearch;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleSearchMediaTest {

    private ArticleSearchMedia media = new ArticleSearchMedia();

    @Test
    public void setCaption() {
       this.media.setCaption("White house");
       assertEquals("White house",this.media.getCaption());
    }

    @Test
    public void setType() {
        this.media.setType("photo");
        assertEquals("photo",this.media.getType());
    }

    @Test
    public void setUrl() {
        this.media.setUrl("images/2018/08/06/t-magazine/");
        assertEquals("images/2018/08/06/t-magazine/",this.media.getUrl());
    }

    @Test
    public void setHeight() {
        this.media.setHeight(345);
        assertEquals(345,this.media.getHeight());
    }

    @Test
    public void setWidth() {
        this.media.setWidth(560);
        assertEquals(560,this.media.getWidth());
    }
}