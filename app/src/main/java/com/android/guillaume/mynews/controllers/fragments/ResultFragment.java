package com.android.guillaume.mynews.controllers.fragments;


import android.content.Intent;
import android.os.Bundle;
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
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchArticle;
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.utils.RecyclerItemClickListener;
import com.android.guillaume.mynews.views.RecyclerArticleSearchAdapter;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultFragment extends Fragment  {

    private static final String ARG_PARAM = "param";
    public static String EXTRA_URL = "EXTRA_URL";

    private List<ArticleSearchArticle> articleSearchList;
    private ArticleSearchResult articleSearchResult = new ArticleSearchResult();
    private RecyclerArticleSearchAdapter adapter;

    @BindView(R.id.recycler_view_result)
    RecyclerView recyclerView;


    public ResultFragment() {
        // Required empty public constructor
    }


    public static ResultFragment newInstance(ArticleSearchResult requestResult) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM, requestResult);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.articleSearchResult = getArguments().getParcelable(ARG_PARAM);
            this.articleSearchList = articleSearchResult.getResponse().getDocs();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "onCreateView: ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        ButterKnife.bind(this,view);

        this.configureRecyclerView();
        this.setDataToRecyclerView(this.articleSearchList);
        this.addClickToRecyclerViewItem();

        return view;
    }

    // Define RecyclerView
    private void configureRecyclerView() {
        Log.d("TAG", "configureRecyclerView: ");
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);
    }

    // Add Data to recyclerView
    private void setDataToRecyclerView(List<ArticleSearchArticle> articleList){
        Log.d("TAG", "setDataToRecyclerView: ");
        //set an adapter
        this.adapter = new RecyclerArticleSearchAdapter( articleList, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
    }

    // Add ClickListener on each RecyclerView items
    private void addClickToRecyclerViewItem() {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getContext(), this.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String url = adapter.getArticleSearchArcticle(position).getWebUrl();
                        startDetailActivity(url);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), adapter.getArticleSearchArcticle(position).getWebUrl(), Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    // Run DetailActivity to show article details
    private void startDetailActivity(String url) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra(EXTRA_URL, url);
        startActivity(intent);
    }
}
