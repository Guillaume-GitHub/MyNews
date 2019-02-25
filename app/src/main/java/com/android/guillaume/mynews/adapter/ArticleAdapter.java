package com.android.guillaume.mynews.adapter;

import android.util.Log;

import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.models.topStories.TopStoriesResult;
import com.android.guillaume.mynews.utils.ApiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleAdapter {

   private static final String BASE = "https://api.nytimes.com/svc/topstories/v2/";
   private static final String BASE_ARTICLESEARCH = "https://api.nytimes.com/svc/search/v2/";

    public void startTopStoriesRequest(Callback<TopStoriesResult> callback, String section) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<TopStoriesResult> call = service.topStoriesList(section);
        call.enqueue(callback);
    }

    public void startArticleSearchRequest(Callback<ArticleSearchResult> callback, HashMap<String, String> params) {
        Log.d("TAG", "startArticleSearchRequest: " + BASE_ARTICLESEARCH + params);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ARTICLESEARCH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ArticleSearchResult> call = service.articleSearchList(params);
        call.enqueue(callback);
    }
}

