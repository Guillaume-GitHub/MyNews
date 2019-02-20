package com.android.guillaume.mynews.models.articleSearch;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleSearchMetaTest {

    private ArticleSearchMeta meta = new ArticleSearchMeta();

    @Test
    public void getAndSetHits() {
        this.meta.setHits(32);
        assertEquals(32,this.meta.getHits(),0.001);
    }

    @Test
    public void getAndSetOffset() {
        this.meta.setOffset(45);
        assertEquals(45,this.meta.getOffset(),0.001);
    }

    @Test
    public void getAndSetTime() {
        this.meta.setTime(112);
        assertEquals(112,this.meta.getTime(),0.001);
    }
}