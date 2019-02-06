package com.android.guillaume.mynews.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

// FIXME: Uncomment for use

public class MostPopularArticle implements Parcelable {

    /******************  VAR  *******************/

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("section")
    @Expose
    private String section;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("published_date")
    @Expose
    private String publishedDate;

    @SerializedName("media")
    @Expose
    private List<MostPopularMedia> media = null;
/*
    @SerializedName("adx_keywords")
    @Expose
    private String adxKeywords;

    @SerializedName("column")
    @Expose
    private Object column;

    @SerializedName("byline")
    @Expose
    private String byline;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("abstract")
    @Expose
    private String _abstract;

    @SerializedName("source")
    @Expose
    private String source;

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("asset_id")
    @Expose
    private Long assetId;

    @SerializedName("views")
    @Expose
    private Integer views;

    @SerializedName("des_facet")
    @Expose
    private List<String> desFacet = null;

    @SerializedName("org_facet")
    @Expose
    private List<String> orgFacet = null;

    @SerializedName("per_facet")
    @Expose
    private List<String> perFacet = null;

    @SerializedName("geo_facet")
    @Expose
    private List<String> geoFacet = null;

    @SerializedName("uri")
    @Expose
    private String uri;
*/
    /******************  METHODS  *******************/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<MostPopularMedia> getMedia() {
        return media;
    }

    public void setMedia(List<MostPopularMedia> media) {
        this.media = media;
    }

/*
    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public Object getColumn() {
        return column;
    }

    public void setColumn(Object column) {
        this.column = column;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
*/
    /************************ PARCELABLE METHODS *********************/

    protected MostPopularArticle(Parcel in) {
        url = in.readString();
        section = in.readString();
        title = in.readString();
        publishedDate = in.readString();
    }

    public static final Creator<MostPopularArticle> CREATOR = new Creator<MostPopularArticle>() {
        @Override
        public MostPopularArticle createFromParcel(Parcel in) {
            return new MostPopularArticle(in);
        }

        @Override
        public MostPopularArticle[] newArray(int size) {
            return new MostPopularArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(section);
        dest.writeString(title);
        dest.writeString(publishedDate);
    }
}
