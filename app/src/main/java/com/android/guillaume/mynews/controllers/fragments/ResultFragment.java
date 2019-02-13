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

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchArticle;
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;
import com.android.guillaume.mynews.views.RecyclerArticleSearchAdapter;
import com.android.guillaume.mynews.views.ArticleAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private HashMap<String, String> mParam1;
    private String mParam2;

    //
    private List<ArticleSearchArticle> articleSearchList;

    //
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
        args.putSerializable(ARG_PARAM1, params);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = (HashMap<String, String>) getArguments().getSerializable(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Log.d("TAG", "onCreate: " + mParam1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "onCreateView: ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        ButterKnife.bind(this,view);

        this.configureRecyclerView();
        this.fetchArticleSearchArticles(mParam1);

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
        RecyclerArticleSearchAdapter adapter = new RecyclerArticleSearchAdapter( articleList, Glide.with(this));
        this.recyclerView.setAdapter(adapter);
    }

    public void fetchArticleSearchArticles(HashMap<String, String> params) {
        ArticleAdapter searchArticleAdapter = new ArticleAdapter();
        searchArticleAdapter.startArticleSearchRequest(this, params);
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
}
