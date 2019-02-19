package com.android.guillaume.mynews.models.articleSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArticleSearchResponse {

    @SerializedName("docs")
    @Expose
    private List<ArticleSearchArticle> docs = null;
    @SerializedName("meta")
    @Expose
    private ArticleSearchMeta meta;

    public List<ArticleSearchArticle> getDocs() {
        return docs;
    }

    public void setDocs(List<ArticleSearchArticle> docs) {
        this.docs = docs;
    }

    public ArticleSearchMeta getMeta() {
        return meta;
    }

    public void setMeta(ArticleSearchMeta meta) {
        this.meta = meta;
    }
}
