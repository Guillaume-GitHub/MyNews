package com.android.guillaume.mynews.models.articleSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleSearchResult {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private ArticleSearchResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArticleSearchResponse getResponse() {
        return response;
    }

    public void setResponse(ArticleSearchResponse response) {
        this.response = response;
    }
}

