package com.android.guillaume.mynews.models.mostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MostPopularResult {

    /******************  VAR  *******************/


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("results")
    @Expose
    private ArrayList<MostPopularArticle> results = null;
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

    public ArrayList<MostPopularArticle> getResults() {
        return results;
    }

    public void setResults(ArrayList<MostPopularArticle> results) {
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
