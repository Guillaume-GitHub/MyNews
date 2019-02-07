package com.android.guillaume.mynews.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

//TODO: More Tests

public class MostPopularResultTest {

    private MostPopularResult mostPopularResult = new MostPopularResult();

    @Test
    public void setAndGetStatus() {
        this.mostPopularResult.setStatus("OK");
        assertEquals("OK",this.mostPopularResult.getStatus());
    }

    @Test
    public void setAndGetResults() {
        ArrayList<MostPopularArticle> mostPopularArticleList = new ArrayList<>();
        this.mostPopularResult.setResults(mostPopularArticleList);
        assertEquals(mostPopularArticleList, this.mostPopularResult.getResults());
    }
}