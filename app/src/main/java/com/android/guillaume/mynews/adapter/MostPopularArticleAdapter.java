package com.android.guillaume.mynews.adapter;

import com.android.guillaume.mynews.models.mostPopular.MostPopularResult;
import com.android.guillaume.mynews.utils.ApiService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostPopularArticleAdapter {

    static final String BASE = "https://api.nytimes.com/svc/mostpopular/v2/";
    private Call<MostPopularResult> call;

    public void startApiRequest(String section) {

        Retrofit retrofit = new Retrofit.Builder()
             .baseUrl(BASE)
             .addConverterFactory(GsonConverterFactory.create())
             .build();

        ApiService service = retrofit.create(ApiService.class);
        call = service.mostPopularList(section);
    }

    public Call<MostPopularResult> getCall() {
        return call;
    }
}
