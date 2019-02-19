package com.android.guillaume.mynews.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.views.ArticleAdapter;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobReceiver extends BroadcastReceiver implements Callback<ArticleSearchResult> {

    private static String TAG = "NEWSAPP";
    private String query;
    private String filterQuery;
    private HashMap<String,String> map = new HashMap<>();
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl;
        wl = pm.newWakeLock(pm.PARTIAL_WAKE_LOCK,TAG);
        wl.acquire(5000);

        // Job
        this.doingJob(intent);

        wl.release();
    }

    @Override
    public void onResponse(Call<ArticleSearchResult> call, Response<ArticleSearchResult> response) {
        NotificationBuilder notificationBuilder = new NotificationBuilder(context);
        notificationBuilder.sendNotification(response.body());
    }

    @Override
    public void onFailure(Call<ArticleSearchResult> call, Throwable t) {

    }

    private void fetchData(){
        ArticleAdapter articleAdapter = new ArticleAdapter();
        articleAdapter.startArticleSearchRequest(this, map);
    }

    private void doingJob(Intent intent){
        this.query = intent.getStringExtra("QUERY");
        this.filterQuery = intent.getStringExtra("FILTER");

        // Add all query params in HashMap
        this.map.put("fq", filterQuery);
        this.map.put("q", query);

        this.fetchData();
    }

}
