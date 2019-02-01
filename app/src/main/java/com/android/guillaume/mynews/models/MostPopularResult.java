package com.android.guillaume.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

// FIXME: Uncomment for use

public class MostPopularResult {

    /******************  VAR  *******************/


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("results")
    @Expose
    private List<MostPopularArticle> results = null;
/*
    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("num_results")
    @Expose
    private Integer numResults;
*/

    /******************  METHODS  *******************/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MostPopularArticle> getResults() {
        return results;
    }

    public void setResults(List<MostPopularArticle> results) {
        this.results = results;
    }

/*
    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }
*/
}
