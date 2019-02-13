package com.android.guillaume.mynews.models.articleSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArticleSearchResponse {

    @SerializedName("docs")
    @Expose
    private List<ArticleSearchArticle> docs = null;

    public List<ArticleSearchArticle> getDocs() {
        return docs;
    }

    public void setDocs(List<ArticleSearchArticle> docs) {
        this.docs = docs;
    }
}
