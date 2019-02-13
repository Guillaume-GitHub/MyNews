package com.android.guillaume.mynews.models.articleSearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArticleSearchArticleTest {

    private ArticleSearchArticle article = new ArticleSearchArticle();

    @Test
    public void setAnGetWebUrl() {
        this.article.setWebUrl("https://wwww.nytimes.com");
        assertEquals("https://wwww.nytimes.com",this.article.getWebUrl());
    }

    @Test
    public void setAndGetSnippet() {
        this.article.setSnippet("Snippet");
        assertEquals("Snippet",this.article.getSnippet());
    }

    @Test
    public void setLeadParagraph() {
        this.article.setLeadParagraph("Some string text");
        assertEquals("Some string text",this.article.getLeadParagraph());
    }

    @Test
    public void setSource() {
        this.article.setSource("The New York Times");
        assertEquals("The New York Times",this.article.getSource());
    }

    @Test
    public void setMultimedia() {
        List<ArticleSearchMedia> media = new ArrayList<>();
        this.article.setMultimedia(media);
        assertEquals(media, this.article.getMultimedia());
    }

    @Test
    public void setPubDate() {
        this.article.setPubDate("2019-02-10");
        assertEquals("2019-02-10",this.article.getPubDate());
    }

    @Test
    public void setSectionName() {
        this.article.setSectionName("Sports");
        assertEquals("Sports",this.article.getSectionName());

    }

    @Test
    public void setSubsectionName() {
        this.article.setSubsectoinName("health");
        assertEquals("health",this.article.getSubsectionName());
    }

    @Test
    public void setId() {
        this.article.setId("6438bdj68hl9");
        assertEquals("6438bdj68hl9",this.article.getId());
    }

    @Test
    public void setUri() {
        this.article.setUri("nyt://article/54");
        assertEquals("nyt://article/54",this.article.getUri());
    }
}