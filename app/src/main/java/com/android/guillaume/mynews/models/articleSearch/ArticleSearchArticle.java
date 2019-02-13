package com.android.guillaume.mynews.models.articleSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleSearchArticle {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("multimedia")
    @Expose
    private List<ArticleSearchMedia> multimedia = null;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsectoinName")
    @Expose
    private String subsectionName;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("uri")
    @Expose
    private String uri;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ArticleSearchMedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<ArticleSearchMedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectoinName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
