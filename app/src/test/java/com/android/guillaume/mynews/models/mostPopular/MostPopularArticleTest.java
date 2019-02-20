package com.android.guillaume.mynews.models.mostPopular;

import com.android.guillaume.mynews.models.mostPopular.MostPopularArticle;
import com.android.guillaume.mynews.models.mostPopular.MostPopularMedia;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

//TODO: More Tests

public class MostPopularArticleTest {

    MostPopularArticle mostPopularArticle = new MostPopularArticle();

    @Test
    public void setAndGetTitle() {
        this.mostPopularArticle.setTitle("This is a new title");
        assertEquals("This is a new title",this.mostPopularArticle.getTitle());
    }

    @Test
    public void setAndGetUrl() {
        this.mostPopularArticle.setUrl("https://www.google.com");
        assertEquals("https://www.google.com",this.mostPopularArticle.getUrl());
    }

    @Test
    public void setAndGetSection() {
        this.mostPopularArticle.setSection("section");
        assertEquals("section",this.mostPopularArticle.getSection());
    }

    @Test
    public void setAndGetPublishedDate() {
        this.mostPopularArticle.setPublishedDate("2019-01_28");
        assertEquals("2019-01_28",this.mostPopularArticle.getPublishedDate());
    }

    @Test
    public void setAndGetMedia() {
        List<MostPopularMedia> mediaList = new ArrayList<>();
        this.mostPopularArticle.setMedia(mediaList);
        assertEquals(mediaList, this.mostPopularArticle.getMedia());

    }
}