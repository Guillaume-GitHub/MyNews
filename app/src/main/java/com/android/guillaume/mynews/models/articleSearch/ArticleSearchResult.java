package com.android.guillaume.mynews.models.articleSearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleSearchResult implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private ArticleSearchResponse response;

    protected ArticleSearchResult(Parcel in) {
        status = in.readString();
        copyright = in.readString();
    }

    public static final Creator<ArticleSearchResult> CREATOR = new Creator<ArticleSearchResult>() {
        @Override
        public ArticleSearchResult createFromParcel(Parcel in) {
            return new ArticleSearchResult(in);
        }

        @Override
        public ArticleSearchResult[] newArray(int size) {
            return new ArticleSearchResult[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(copyright);
    }
}

