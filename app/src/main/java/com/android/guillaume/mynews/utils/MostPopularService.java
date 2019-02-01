package com.android.guillaume.mynews.utils;

import com.android.guillaume.mynews.models.MostPopularResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MostPopularService {
    @GET("{source}/1.json?api-key=6Spkz7t019UGja59co671DFh85RELnOt")
    Call<MostPopularResult> listArticle(@Path("source")String source);
}
