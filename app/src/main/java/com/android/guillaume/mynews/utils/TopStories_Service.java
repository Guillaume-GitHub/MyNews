package com.android.guillaume.mynews.utils;

import com.android.guillaume.mynews.models.TopStoriesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TopStories_Service {
    @GET("{section}.json?api-key=6Spkz7t019UGja59co671DFh85RELnOt")
    Call<TopStoriesResult> listArticle(@Path("section") String section);

}

