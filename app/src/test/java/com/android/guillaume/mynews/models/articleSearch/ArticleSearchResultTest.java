package com.android.guillaume.mynews.models.articleSearch;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleSearchResultTest {

    private ArticleSearchResult articleSearchResult = new ArticleSearchResult();

    @Test
    public void setAndGetStatus() {
        this.articleSearchResult.setStatus("OK");
        assertEquals("OK",this.articleSearchResult.getStatus());
    }

    @Test
    public void setAndGetCopyright() {
        this.articleSearchResult.setCopyright("New York Times");
        assertEquals("New York Times",this.articleSearchResult.getCopyright());
    }

    @Test
    public void setAndGetResponse() {
        ArticleSearchResponse response = new ArticleSearchResponse();
        this.articleSearchResult.setResponse(response);
        assertEquals(response,this.articleSearchResult.getResponse());
    }
}