package com.android.guillaume.mynews.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopStoriesArticle implements Parcelable {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("material_type_facet")
    @Expose
    private String materialTypeFacet;
    @SerializedName("kicker")
    @Expose
    private String kicker;
    @SerializedName("des_facet")
    @Expose
    private List<String> desFacet = null;
    @SerializedName("org_facet")
    @Expose
    private List<String> orgFacet = null;
    @SerializedName("per_facet")
    @Expose
    private List<Object> perFacet = null;
    @SerializedName("geo_facet")
    @Expose
    private List<String> geoFacet = null;
    @SerializedName("multimedia")
    @Expose
    private List<TopStoriesMedia> multimedia = null;
    @SerializedName("short_url")
    @Expose
    private String shortUrl;


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
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

    public List<Object> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<Object> perFacet) {
        this.perFacet = perFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }
    public List<TopStoriesMedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<TopStoriesMedia> multimedia) {
        this.multimedia = multimedia;
    }

    public int getMultimediaCount() {
        return multimedia.size();
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }


    /******************** PARCELABLE METHODS ********************/

    protected TopStoriesArticle(Parcel in) {
        section = in.readString();
        subsection = in.readString();
        title = in.readString();
        _abstract = in.readString();
        url = in.readString();
        byline = in.readString();
        itemType = in.readString();
        updatedDate = in.readString();
        createdDate = in.readString();
        publishedDate = in.readString();
        materialTypeFacet = in.readString();
        kicker = in.readString();
        desFacet = in.createStringArrayList();
        orgFacet = in.createStringArrayList();
        geoFacet = in.createStringArrayList();
        shortUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(section);
        parcel.writeString(subsection);
        parcel.writeString(title);
        parcel.writeString(_abstract);
        parcel.writeString(url);
        parcel.writeString(byline);
        parcel.writeString(itemType);
        parcel.writeString(updatedDate);
        parcel.writeString(createdDate);
        parcel.writeString(publishedDate);
        parcel.writeString(materialTypeFacet);
        parcel.writeString(kicker);
        parcel.writeStringList(desFacet);
        parcel.writeStringList(orgFacet);
        parcel.writeStringList(geoFacet);
        parcel.writeString(shortUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TopStoriesArticle> CREATOR = new Creator<TopStoriesArticle>() {
        @Override
        public TopStoriesArticle createFromParcel(Parcel in) {
            return new TopStoriesArticle(in);
        }

        @Override
        public TopStoriesArticle[] newArray(int size) {
            return new TopStoriesArticle[size];
        }
    };

}

