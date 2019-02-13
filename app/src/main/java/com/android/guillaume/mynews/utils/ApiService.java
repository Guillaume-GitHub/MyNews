package com.android.guillaume.mynews.utils;

import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.models.mostPopular.MostPopularResult;
import com.android.guillaume.mynews.models.topStories.TopStoriesResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {

    String key = "6Spkz7t019UGja59co671DFh85RELnOt";

    /*********************** TOPSTORIES API ********************/

    @GET("{section}.json?api-key=" + key)
    Call<TopStoriesResult> topStoriesList(@Path("section") String section);

    /*********************** MOSTPOPULAR API ********************/

    @GET("{source}/1.json?api-key=" + key)
    Call<MostPopularResult> mostPopularList(@Path("source") String source);

    /*********************** ARTICLESEARCH API ******************/
    @GET("articlesearch.json?api-key=" + key)
    Call<ArticleSearchResult> articleSearchList(@QueryMap Map<String,String> params);
}