package com.android.guillaume.mynews.controllers.fragments;


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
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchArticle;
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.utils.RecyclerItemClickListener;
import com.android.guillaume.mynews.views.RecyclerArticleSearchAdapter;
import com.android.guillaume.mynews.views.ArticleAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment implements Callback<ArticleSearchResult> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "param";
    public static String EXTRA_URL = "EXTRA_URL";

    // TODO: Rename and change types of parameters
    private HashMap<String, String> listParams;

    private List<ArticleSearchArticle> articleSearchList;
    private RecyclerArticleSearchAdapter adapter;

    @BindView(R.id.recycler_view_result)
    RecyclerView recyclerView;


    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param params Parameter 1.
     * @return A new instance of fragment ResultFragment.
     */

    public static ResultFragment newInstance(HashMap<String, String> params) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        Log.d("TAG", "newInstance: "+params);
        args.putSerializable(ARG_PARAM, params);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            listParams = (HashMap<String, String>) getArguments().getSerializable(ARG_PARAM);
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
        this.addClickToRecyclerViewItem();
        this.fetchArticleSearchArticles(listParams);

        return view;
    }


    private void configureRecyclerView() {
        Log.d("TAG", "configureRecyclerView: ");
        // use a linear layout manager
        // *Empty RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);
    }

    private void setDataToRecyclerView(List<ArticleSearchArticle> articleList){
        Log.d("TAG", "setDataToRecyclerView: ");
        //set an adapter
        this.adapter = new RecyclerArticleSearchAdapter( articleList, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
    }

    public void fetchArticleSearchArticles(HashMap<String, String> params) {
        ArticleAdapter searchArticleAdapter = new ArticleAdapter();
        searchArticleAdapter.startArticleSearchRequest(this, params);
    }

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

    @Override
    public void onResponse(@NonNull Call<ArticleSearchResult> call, @NonNull  Response<ArticleSearchResult> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse: " + response.code());
        assert response.body() != null;
        this.articleSearchList = new ArrayList<>();
        this.articleSearchList= response.body().getResponse().getDocs();
        setDataToRecyclerView(this.articleSearchList);
    }

    @Override
    public void onFailure(@NonNull  Call<ArticleSearchResult> call, @NonNull  Throwable t) {
        Log.d("TAG", "onFailure: " + Log.getStackTraceString(t));
    }

    private void startDetailActivity(String url) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra(EXTRA_URL, url);
        startActivity(intent);
    }
}
