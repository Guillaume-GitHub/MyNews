package com.android.guillaume.mynews.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.views.ArticleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobReceiver extends BroadcastReceiver implements Callback<ArticleSearchResult> {

    private String TAG = this.getClass().getSimpleName();
    private String query;
    private ArrayList<String> filterQuery;
    private HashMap<String,String> map = new HashMap<>();
    private Context context;
    private String beginDate;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        // Job
        this.doingJob(intent);
    }

    @Override
    public void onResponse(@NonNull Call<ArticleSearchResult> call, @NonNull Response<ArticleSearchResult> response) {
        if (response.isSuccessful()) {
            assert response.body() != null;
            NotificationBuilder notificationBuilder = new NotificationBuilder(context);
            notificationBuilder.sendNotification(response.body());
        }
    }

    @Override
    public void onFailure(@NonNull Call<ArticleSearchResult> call, @NonNull Throwable t) {
        Log.d(TAG, "onFailure: " + Log.getStackTraceString(t));
    }

    private void fetchData(HashMap<String,String> params){
        ArticleAdapter articleAdapter = new ArticleAdapter();
        articleAdapter.startArticleSearchRequest(this, params);
    }

    // JOb params
    private void doingJob(Intent intent){
        this.query = intent.getStringExtra("QUERY");
        this.filterQuery = intent.getStringArrayListExtra("FILTER");

        this.initializeDates();

        // category params
        StringBuilder stringBuilder = new StringBuilder();
        String categoryFilter;
        String filterQueryType = "section_name";

        // Build filter query params
        for (String str : this.filterQuery) {
            stringBuilder.append('"');
            stringBuilder.append(str);
            stringBuilder.append('"');
            stringBuilder.append(" ");
        }
        categoryFilter = filterQueryType + ":(" + stringBuilder.toString() +")";

        // Add all query params in HashMap
        this.map.put("begin_date",beginDate);
        this.map.put("fq", categoryFilter);
        this.map.put("q", query);

        this.fetchData(this.map);
    }

    // Define date params for searchArticle query (day = J)
    private void initializeDates(){
        Calendar begin = Calendar.getInstance();

        String myFormat ="yyyyMMdd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);

        beginDate = dateFormat.format(begin.getTime());
    }
}
