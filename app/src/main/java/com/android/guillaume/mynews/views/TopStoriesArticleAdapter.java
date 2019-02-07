package com.android.guillaume.mynews.views;

import com.android.guillaume.mynews.models.TopStoriesResult;
import com.android.guillaume.mynews.utils.TopStories_Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopStoriesArticleAdapter {

    static final String BASE = "https://api.nytimes.com/svc/topstories/v2/";

    public void startApiRequest(Callback<TopStoriesResult> callback, String section) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TopStories_Service service = retrofit.create(TopStories_Service.class);

        Call<TopStoriesResult> call = service.listArticle(section);
        call.enqueue(callback);
    }
}

