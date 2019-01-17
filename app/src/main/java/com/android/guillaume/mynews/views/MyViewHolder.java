package com.android.guillaume.mynews.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.models.TopStoriesArticle;
import com.bumptech.glide.RequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recycler_view_image_item)ImageView articleImageView;
    @BindView(R.id.recycler_view_category_item) TextView categoryTextView;
    @BindView(R.id.recycler_view_date_item) TextView dateTextView;
    @BindView(R.id.recycler_view_description_item) TextView descriptionTextView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateRecyclerViewItem(TopStoriesArticle article, RequestManager glide){
        this.updateImageView(article,glide);
        this.updateCategoryTextView(article);
        this.updateDescriptionTextView(article);
        this.updateDateTextView(article);
    }


    private void updateImageView(TopStoriesArticle article, RequestManager glide){

        if (article.getMultimediaCount() != 0)
            glide.load(article.getMultimedia().get(1).getUrl()).into(this.articleImageView);
        else
            this.articleImageView.setBackgroundColor(itemView.getResources().getColor(R.color.colorLightGrey));
    }

    private void updateCategoryTextView(TopStoriesArticle article){

        if (!article.getSubsection().isEmpty())
            this.categoryTextView.setText(article.getSection() + " > " + article.getSubsection());
        else
            this.categoryTextView.setText(article.getSection());
    }

    private void updateDescriptionTextView(TopStoriesArticle article){
        this.descriptionTextView.setText(article.getTitle());
    }

    public void updateDateTextView(TopStoriesArticle article){
        this.dateTextView.setText(article.getPublishedDate());
    }
}
