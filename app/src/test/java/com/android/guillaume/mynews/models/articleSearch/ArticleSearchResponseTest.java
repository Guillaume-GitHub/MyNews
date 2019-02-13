package com.android.guillaume.mynews.models.articleSearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArticleSearchResponseTest {

    private ArticleSearchResponse response = new ArticleSearchResponse();

    @Test
    public void setAndGetDocs() {
        List<ArticleSearchArticle> article = new ArrayList<>();
        response.setDocs(article);
        assertEquals(article,response.getDocs());
    }
}