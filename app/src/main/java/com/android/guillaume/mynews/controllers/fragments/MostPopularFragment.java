package com.android.guillaume.mynews.controllers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.activities.DetailActivity;
import com.android.guillaume.mynews.models.MostPopularArticle;
import com.android.guillaume.mynews.models.MostPopularResult;
import com.android.guillaume.mynews.utils.MostPopularArticleAdapter;
import com.android.guillaume.mynews.utils.RecyclerItemClickListener;
import com.android.guillaume.mynews.views.MostPopularRecyclerAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularFragment extends Fragment implements Callback<MostPopularResult> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MostPopularRecyclerAdapter adapter;
    private List<MostPopularArticle> mostPopularArticles;

    public static MostPopularFragment newInstance(){
        MostPopularFragment fragment = new MostPopularFragment();
        fragment.mostPopularArticlesRequest();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();
        this.addClickOnRecyclerViewItem();
        return view;
    }

    /************************ RECYCLERVIEW ************************/

    private void configureRecyclerView() {
        // use a linear layout manager
        this.layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        this.mostPopularArticles = new ArrayList<>();
        this.adapter = new MostPopularRecyclerAdapter(this.mostPopularArticles, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
    }

    private void addClickOnRecyclerViewItem(){
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this.getContext(), this.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                runDetailActivity(mostPopularArticles.get(position));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    /************************ API REQUEST ************************/

    public void mostPopularArticlesRequest(){
        MostPopularArticleAdapter articleAdapter = new MostPopularArticleAdapter();
        articleAdapter.getMostPopularArticleAdapter(this,"viewed");
    }

    @Override
    public void onResponse(@NonNull Call<MostPopularResult> call, @NonNull Response<MostPopularResult> response) {
        Log.d("TAG", "onResponse: " + String.valueOf(response.code()));
        if (response.isSuccessful()) {
            assert response.body() != null;
            this.updateUi(response.body().getResults());
        }
    }

    @Override
    public void onFailure(@NonNull Call<MostPopularResult> call, @NonNull Throwable t) {
        Log.d("TAG", "onFailure: " + Log.getStackTraceString(t));
    }

    /************************ UI UPDATE ************************/

    private void updateUi(List<MostPopularArticle> articleList) {
        this.mostPopularArticles.clear();
        this.mostPopularArticles.addAll(articleList);
        this.adapter.notifyDataSetChanged();
        Log.e("TAG", "updateUI_MostPopular");
    }
    /************************* METHODS **************************/

    private void runDetailActivity(MostPopularArticle article){
        Intent intent = new Intent(this.getContext(),DetailActivity.class);
        intent.putExtra(MainFragment.EXTRA_URL,article.getUrl());
        startActivity(intent);
    }
}
