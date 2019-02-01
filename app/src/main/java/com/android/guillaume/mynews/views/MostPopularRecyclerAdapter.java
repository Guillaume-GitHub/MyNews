package com.android.guillaume.mynews.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.models.MostPopularArticle;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class MostPopularRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private  List<MostPopularArticle> mostPopularArticles;
    private  RequestManager glide;

    public MostPopularRecyclerAdapter(List<MostPopularArticle> articlesList, RequestManager glide) {
        this.mostPopularArticles = articlesList;
        this.glide = glide;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.updateMostPopularView(this.mostPopularArticles.get(position), this.glide);
    }

    @Override
    public int getItemCount() {
        return this.mostPopularArticles.size();
    }
}
