package com.android.guillaume.mynews.utils;

import com.android.guillaume.mynews.models.MostPopularResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostPopularArticleAdapter {

    static final String BASE = "https://api.nytimes.com/svc/mostpopular/v2/";

    public void getMostPopularArticleAdapter(Callback<MostPopularResult> callback, String section) {

        Retrofit retrofit = new Retrofit.Builder()
             .baseUrl(BASE)
             .addConverterFactory(GsonConverterFactory.create())
             .build();

        MostPopularService service = retrofit.create(MostPopularService.class);

        Call<MostPopularResult> call = service.listArticle(section);
        call.enqueue(callback);
    }
}
