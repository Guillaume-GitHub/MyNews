package com.android.guillaume.mynews.controllers.activities;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.NoResultFragment;
import com.android.guillaume.mynews.controllers.fragments.ResultFragment;
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.views.ArticleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity  implements Callback<ArticleSearchResult> {

    @BindView(R.id.result_toolbar)
    Toolbar toolbar;

    private Fragment fragment;
    private String inputText;
    private List<String> filterQuery;
    private List<String> filterDate;
    private ArticleSearchResult articleSearchResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);
        this.getExtraIntentValues();
        this.configureToolBar();
        this.fetchArticleSearchArticles(queryBuilder());

    }

    /********************* UI CONTROLS ****************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureToolBar() {
        setSupportActionBar(this.toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert inputText != null;
        actionBar.setTitle(inputText);
    }


    private void configureAndShowFragment() {
        Log.d("TAG", "configureAndShowFragment: ");

        if(this.articleSearchResult != null && this.articleSearchResult.getResponse().getMeta().getHits() > 0) {


            if (this.fragment == null || !this.fragment.isVisible()) {
                this.fragment = ResultFragment.newInstance(articleSearchResult);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.result_activity_framelayout, this.fragment)
                        .commit();
            }

        }else{

            this.fragment = NoResultFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.result_activity_framelayout, this.fragment)
                    .commit();
        }
    }

    private void getExtraIntentValues(){
        Intent intent = getIntent();

        //Get Input query text if not null
        if (intent.getStringExtra("EXTRA_TEXT") != null) {
            inputText = intent.getStringExtra("EXTRA_TEXT");
        }

        //Get checkboxes values if not null
        if (intent.getStringArrayListExtra("EXTRA_BOXES") != null) {
            this.filterQuery = new ArrayList<>();
            this.filterQuery = intent.getStringArrayListExtra("EXTRA_BOXES");
        }

        //Get Date values if not null
        if (intent.getStringArrayListExtra("EXTRA_DATES") != null) {
            this.filterDate = new ArrayList<>();
            this.filterDate = intent.getStringArrayListExtra("EXTRA_DATES");
            Log.d("TAG", "getExtraIntentValues: "+ this.filterDate);
        }
    }

    // Params to set to api Request
    private HashMap<String,String> queryBuilder() {

        HashMap<String,String> map = new HashMap<>();
        // the input search value param
        String query = inputText;
        // category params
        StringBuilder stringBuilder = new StringBuilder();
        String categoryFilter;
        String filterQueryType= "section_name";
        // Sort param
        String sortType = "newest";
        //Date param
        String beginDate = "";
        String endDate = "";

        // Build filter query params
        for (String str : this.filterQuery) {
            stringBuilder.append('"');
            stringBuilder.append(str);
            stringBuilder.append('"');
            stringBuilder.append(" ");
        }
        categoryFilter = filterQueryType + ":(" + stringBuilder.toString() +")";

        //Get date params
        if (this.filterDate.size() == 2) {
            beginDate = this.filterDate.get(0);
            Log.d("TAG", "queryBuilder: ");
            endDate = this.filterDate.get(1);
            Log.d("TAG", "queryBuilder: " + endDate);
        }

        // Add all query params in HashMap
        map.put("begin_date", beginDate);
        map.put("end_date", endDate);
        map.put("fq", categoryFilter);
        map.put("q", query);
        map.put("sort", sortType);

        return map;
    }


    @Override
    public void onResponse(@NonNull Call<ArticleSearchResult> call, @NonNull Response<ArticleSearchResult> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse: " + response.code());
        assert response.body() != null;
        this.articleSearchResult = new ArticleSearchResult();
        this.articleSearchResult = response.body();
        this.configureAndShowFragment();
    }

    @Override
    public void onFailure(@NonNull  Call<ArticleSearchResult> call, @NonNull  Throwable t) {
        Log.d("TAG", "onFailure: " + Log.getStackTraceString(t));
    }

    // Fetch data from ArticleSearch API
    public void fetchArticleSearchArticles(HashMap<String, String> params) {
        ArticleAdapter searchArticleAdapter = new ArticleAdapter();
        searchArticleAdapter.startArticleSearchRequest(this, params);
    }
}
