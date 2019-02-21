package com.android.guillaume.mynews.controllers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.activities.DetailActivity;
import com.android.guillaume.mynews.models.mostPopular.MostPopularArticle;
import com.android.guillaume.mynews.models.mostPopular.MostPopularResult;
import com.android.guillaume.mynews.models.topStories.TopStoriesArticle;
import com.android.guillaume.mynews.models.topStories.TopStoriesResult;
import com.android.guillaume.mynews.utils.MostPopularArticleAdapter;
import com.android.guillaume.mynews.utils.RecyclerItemClickListener;
import com.android.guillaume.mynews.views.ArticleAdapter;
import com.android.guillaume.mynews.views.RecyclerViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Callback<TopStoriesResult>{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefresh;

    public static String EXTRA_URL = "EXTRA_URL";
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private ArrayList<MostPopularArticle> listMostPopular;
    private ArrayList<TopStoriesArticle> listTopStories;
    private String apiSection;
    private boolean isReadyToRefresh = true;

    public static MainFragment newInstance(String category) {
        MainFragment frag = new MainFragment();
        Bundle args = new Bundle();
        args.putString("API_SECTION",category);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            // Restore Data from Sate
            this.apiSection = savedInstanceState.getString("API_NAME");
            this.listTopStories = savedInstanceState.getParcelableArrayList("TOPSTORIES_DATA");
            this.listMostPopular = savedInstanceState.getParcelableArrayList("MOSTPOPULAR_DATA");
            Log.d("FRAGMENT", "-------> Restore " + apiSection);

        }else{
            // Get args From bundle
            assert getArguments() != null;
            this.apiSection = getArguments().getString("API_SECTION");
            Log.d("FRAGMENT", "-------> onCreate " + apiSection);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        // Add RecyclerView
        this.configureRecyclerView();

        // Restore or Fetch data
        if (savedInstanceState != null){
            restoreData();

        }else{
            this.fetchData();
        }

        // Set click Listener
        this.addClickToRecyclerViewItem();

        //Set SwipeToRefresh
        this.configureSwipeToRefresh();

        return view;
    }

    // Start APi request to fetch data
    private void fetchData() {

        switch (apiSection){
            case "home":
                this.fetchTopStoriesArticles(apiSection);
                break;
            case "health":
                this.fetchTopStoriesArticles(apiSection);
                break;
            case "sports":
                this.fetchTopStoriesArticles(apiSection);
                break;
            case "business":
                this.fetchTopStoriesArticles(apiSection);
                break;
            case "viewed":
                this.fetchMostPopularArticles();
                break;
            default:
                break;
        }
    }

    private void restoreData(){
        if(listTopStories != null) {
           this.setDataToRecyclerView(listTopStories);
        }
        else if (listMostPopular != null){
            this.setDataToRecyclerView(listMostPopular);
        }
    }

    /************************ RECYCLERVIEW ************************/

    private void configureRecyclerView() {
        // use a linear layout manager
        // *Empty RecyclerView
        this.layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);
    }

    private void setDataToRecyclerView(List<TopStoriesArticle> articleList){
        //set an adapter
        this.adapter = new RecyclerViewAdapter(articleList,null, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
    }

    private void setDataToRecyclerView(ArrayList<MostPopularArticle> articleList){
        //set an adapter
        this.adapter = new RecyclerViewAdapter(null,articleList, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    private void addClickToRecyclerViewItem() {

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getContext(), this.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String url;
                        switch (adapter.getApiType()){
                            case 1:
                                url = adapter.getTopStoriesArticle(position).getUrl();
                                startDetailActivity(url);
                                break;
                            case 2:
                                url = adapter.getMostPopularArticle(position).getUrl();
                                startDetailActivity(url);
                                break;
                            default:
                                url = "";
                                startDetailActivity(url);
                                break;
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), adapter.getTopStoriesArticle(position).getUrl(), Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    /*********************** API REQUEST **********************/

    public void fetchTopStoriesArticles(String section) {
        ArticleAdapter articleAdapter = new ArticleAdapter();
        articleAdapter.startTopStoriesRequest(this, section);
    }

    @Override
    public void onResponse(@NonNull Call<TopStoriesResult> call, @NonNull Response<TopStoriesResult> response) {
        Log.d("TAG", "onResponse: " + String.valueOf(response.code()));
        if (response.isSuccessful()) {
            assert response.body() != null;
            listTopStories = new ArrayList<>();
            listTopStories = response.body().getResults();
            setDataToRecyclerView(listTopStories);
        }
    }

    @Override
    public void onFailure(@NonNull Call<TopStoriesResult> call, @NonNull Throwable t) {
        Log.d("TAG", "onFailure: " + Log.getStackTraceString(t));
    }


    public void fetchMostPopularArticles(){
        MostPopularArticleAdapter articleAdapter = new MostPopularArticleAdapter();
        articleAdapter.startApiRequest(apiSection);

        articleAdapter.getCall().enqueue(new Callback<MostPopularResult>() {
            @Override
            public void onResponse(@NonNull Call<MostPopularResult> call, @NonNull Response<MostPopularResult> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    listMostPopular = new ArrayList<>();
                    listMostPopular = response.body().getResults();
                    setDataToRecyclerView(listMostPopular);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MostPopularResult> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: " + Log.getStackTraceString(t));
            }
        });
    }

    /************************* METHODS *************************/

    private void startDetailActivity(String url) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra(EXTRA_URL, url);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // save data source
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("TOPSTORIES_DATA", listTopStories);
        outState.putParcelableArrayList("MOSTPOPULAR_DATA", listMostPopular);
        outState.putString("API_NAME",apiSection);
    }


    private void configureSwipeToRefresh(){
        this.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(!isReadyToRefresh){
                    swipeRefresh.setRefreshing(false);
                }else {
                    if (listMostPopular != null) listMostPopular.clear();
                    if (listTopStories != null) listTopStories.clear();
                    fetchData();
                    adapter.notifyDataSetChanged();
                    refreshData();
                    swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    // Set a delay before refresh (skip error: 429 on API Response)
    private void refreshData(){

        this.isReadyToRefresh = false;

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                isReadyToRefresh = true;
            }
        }, 60000);
    }
}
