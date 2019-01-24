package com.android.guillaume.mynews.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TopStoriesResultTest {

    private TopStoriesResult result = new TopStoriesResult();
    private final String stringTest = "returnOK";

    @Test
    public void getStatus() {
        result.setStatus(stringTest);
        assertEquals("returnOK", result.getStatus());
    }

    @Test
    public void setStatus() {
        result.setStatus(stringTest);
        assertEquals("returnOK", result.getStatus());
    }

    @Test
    public void getCopyright() {
        result.setCopyright(stringTest);
        assertEquals("returnOK", result.getCopyright());
    }

    @Test
    public void setCopyright() {
        result.setCopyright(stringTest);
        assertEquals("returnOK", result.getCopyright());
    }

    @Test
    public void getSection() {
        result.setSection(stringTest);
        assertEquals("returnOK", result.getSection());
    }

    @Test
    public void setSection() {
        result.setSection(stringTest);
        assertEquals("returnOK", result.getSection());
    }

    @Test
    public void getLastUpdated() {
        result.setLastUpdated(stringTest);
        assertEquals("returnOK", result.getLastUpdated());
    }

    @Test
    public void setLastUpdated() {
        result.setLastUpdated(stringTest);
        assertEquals("returnOK", result.getLastUpdated());
    }

    @Test
    public void getNumResults() {
        result.setNumResults(10);
        assertEquals(10,result.getNumResults(),000.1);
    }

    @Test
    public void setNumResults() {
        result.setNumResults(10);
        assertEquals(10,result.getNumResults(),000.1);
    }

    @Test
    public void getResults() {
        List<TopStoriesArticle> articleList = new ArrayList<>();
        result.setResults(articleList);
        assertEquals(articleList, result.getResults());
    }

    @Test
    public void setResults() {
        List<TopStoriesArticle> articleList = new ArrayList<>();
        result.setResults(articleList);
        assertEquals(articleList, result.getResults());
    }
}