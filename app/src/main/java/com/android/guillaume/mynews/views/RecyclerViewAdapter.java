package com.android.guillaume.mynews.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.models.TopStoriesArticle;
import com.bumptech.glide.RequestManager;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private List<TopStoriesArticle> topStoriesArticles;
    private RequestManager glide;

    public RecyclerViewAdapter(List<TopStoriesArticle> articleList, RequestManager glide) {
            this.topStoriesArticles = articleList;
            this.glide = glide;
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
        myViewHolder.updateRecyclerViewItem(this.topStoriesArticles.get(position), this.glide);
    }

    @Override
    public int getItemCount() {
        return topStoriesArticles.size();
    }

    public TopStoriesArticle getTopStoriesArticle(int position) {
      return topStoriesArticles.get(position);
    }
}
