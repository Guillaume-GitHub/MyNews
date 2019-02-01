package com.android.guillaume.mynews.controllers.fragments;


import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.activities.DetailActivity;
import com.android.guillaume.mynews.models.MostPopularArticle;
import com.android.guillaume.mynews.models.MostPopularResult;
import com.android.guillaume.mynews.models.TopStoriesArticle;
import com.android.guillaume.mynews.models.TopStoriesResult;
import com.android.guillaume.mynews.utils.MostPopularArticleAdapter;
import com.android.guillaume.mynews.utils.RecyclerItemClickListener;
import com.android.guillaume.mynews.utils.TopStoriesArticleAdapter;
import com.android.guillaume.mynews.views.RecyclerViewAdapter;
import com.android.guillaume.mynews.views.ViewPagerAdapter;
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

    public static String EXTRA_URL = "EXTRA_URL";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private List<TopStoriesArticle> articles;


    public static MainFragment newInstance(String category) {
        MainFragment fragment = new MainFragment();
        fragment.getArticleFromHttpRequest(category);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, view);
            this.configureRecyclerView();
            this.addClickToRecyclerViewItem();
        return view;
    }

    /************************ RECYCLERVIEW ************************/

    private void configureRecyclerView() {
        // use a linear layout manager
        this.layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        this.articles = new ArrayList<>();
        this.adapter = new RecyclerViewAdapter(this.articles, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
    }

    private void addClickToRecyclerViewItem() {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getContext(), this.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startDetailActivity(adapter.getTopStoriesArticle(position).getUrl());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), adapter.getTopStoriesArticle(position).getUrl(), Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    /*********************** API REQUEST **********************/

    public void getArticleFromHttpRequest(String section) {
        TopStoriesArticleAdapter articleAdapter = new TopStoriesArticleAdapter();
        articleAdapter.startHttpRequest(this, section);
    }

    @Override
    public void onResponse(@NonNull Call<TopStoriesResult> call, @NonNull Response<TopStoriesResult> response) {
        Log.d("TAG", "onResponse: " + String.valueOf(response.code()));
        if (response.isSuccessful()) {
            assert response.body() != null;
            this.updateUi(response.body().getResults());
        }
    }

    @Override
    public void onFailure(@NonNull Call<TopStoriesResult> call, @NonNull Throwable t) {
        Log.d("TAG", "onFailure: " + Log.getStackTraceString(t));
    }


    /************************ UI UPDATE ************************/


    private void updateUi(List<TopStoriesArticle> articleList) {
        this.articles.clear();
        this.articles.addAll(articleList);
        this.adapter.notifyDataSetChanged();
        Log.e("TAG", "updateUI");
    }
    /************************* METHODS *************************/

    private void startDetailActivity(String url) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra(EXTRA_URL, url);
        startActivity(intent);
    }
}
