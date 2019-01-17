package com.android.guillaume.mynews.controllers.fragments;


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

import com.android.guillaume.mynews.models.TopStoriesResult;
import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.models.TopStoriesArticle;
import com.android.guillaume.mynews.utils.RecyclerItemClickListener;
import com.android.guillaume.mynews.utils.TopStoriesArticleAdapter;
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

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;

private List<TopStoriesArticle> articles;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        this.configureRecyclerView();
        this.addClickToRecyclerViewItem();
        this.getArticleFromHttpRequest();
        return view;
    }

    private void configureRecyclerView(){

        // use a linear layout manager
        this.articles = new ArrayList<>();
        this.layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        this.adapter = new RecyclerViewAdapter(this.articles, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);

    }

    private void getArticleFromHttpRequest() {
        TopStoriesArticleAdapter articleAdapter = new TopStoriesArticleAdapter();
        articleAdapter.startHttpRequest(this,"home");
    }


    private void updateUi(List<TopStoriesArticle> articleList){
        this.articles.clear();
        this.articles.addAll(articleList);
        this.adapter.notifyDataSetChanged();
        Log.e("TAG", "updateUI");
    }

    private void addClickToRecyclerViewItem(){
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TopStoriesArticle article = adapter.getArticle(position);
                        Toast.makeText(getContext(), article.getUrl(), Toast.LENGTH_SHORT).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
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
}
