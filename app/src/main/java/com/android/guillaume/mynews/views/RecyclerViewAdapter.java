package com.android.guillaume.mynews.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.models.MostPopularArticle;
import com.android.guillaume.mynews.models.TopStoriesArticle;
import com.bumptech.glide.RequestManager;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<TopStoriesArticle> topStoriesArticles;
    private List<MostPopularArticle> mostPopularArticles;
    private RequestManager glide;
    private int apiType;

    public RecyclerViewAdapter(List<TopStoriesArticle> storiesList, List<MostPopularArticle> popularList, RequestManager glide) {
        if (storiesList != null){
            this.topStoriesArticles = storiesList;
            this.glide = glide;
            this.apiType = 1;

        } else if (popularList != null) {
            this.mostPopularArticles = popularList;
            this.glide = glide;
            this.apiType = 2;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        switch (apiType){
            case 1:
                myViewHolder.updateRecyclerViewItem(this.topStoriesArticles.get(position),this.glide);
                break;
            case 2:
                myViewHolder.updateMostPopularView(this.mostPopularArticles.get(position), this.glide);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {

        switch (apiType){
            case 1:
                return topStoriesArticles.size();
            case 2:
                return mostPopularArticles.size();
            default:
                return 0;
        }
    }

    public TopStoriesArticle getTopStoriesArticle(int position) {
      return topStoriesArticles.get(position);
    }

    public MostPopularArticle getMostPopularArticle(int position){
        return mostPopularArticles.get(position);
    }

    public int getApiType() {
        return apiType;
    }
}
