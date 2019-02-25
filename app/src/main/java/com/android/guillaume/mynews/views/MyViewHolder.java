package com.android.guillaume.mynews.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchArticle;
import com.android.guillaume.mynews.models.mostPopular.MostPopularArticle;
import com.android.guillaume.mynews.models.topStories.TopStoriesArticle;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recycler_view_image_item)
    ImageView articleImageView;
    @BindView(R.id.recycler_view_category_item)
    TextView categoryTextView;
    @BindView(R.id.recycler_view_date_item)
    TextView dateTextView;
    @BindView(R.id.recycler_view_description_item)
    TextView descriptionTextView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateRecyclerViewItem(TopStoriesArticle article, RequestManager glide) {
        this.updateImageView(article, glide);
        this.updateCategoryTextView(article);
        this.updateDescriptionTextView(article);
        this.updateDateTextView(article);
    }


    private void updateImageView(TopStoriesArticle article, RequestManager glide) {
        if (article.getMultimediaCount() != 0)
            glide.load(article.getMultimedia().get(1).getUrl()).into(this.articleImageView);
        else
            this.articleImageView.setBackground(itemView.getResources().getDrawable(R.drawable.brand_logo));
    }

    private void updateCategoryTextView(TopStoriesArticle article) {

        if (!article.getSubsection().isEmpty())
            this.categoryTextView.setText(article.getSection() + " > " + article.getSubsection());
        else
            this.categoryTextView.setText(article.getSection());
    }

    private void updateDescriptionTextView(TopStoriesArticle article) {
        assert article.getTitle() != null;
        this.descriptionTextView.setText(article.getTitle());
    }

    private void updateDateTextView(TopStoriesArticle article) {
        this.dateTextView.setText(article.getPublishedDate());
    }

    public void updateMostPopularView(MostPopularArticle mostPopularArticle, RequestManager glide) {
        glide.load(mostPopularArticle.getMedia().get(0).getMediaMetadata().get(2).getUrl()).apply(RequestOptions.centerCropTransform()).into(this.articleImageView);
        this.descriptionTextView.setText(mostPopularArticle.getTitle());
        this.categoryTextView.setText(mostPopularArticle.getSection());
        this.dateTextView.setText(mostPopularArticle.getPublishedDate());
    }

    public void updateArticleSearchView(ArticleSearchArticle articleSearchArticle, RequestManager glide) {

        String url;

        if (articleSearchArticle.getMultimedia().size() != 0) {
            url = "https://static01.nyt.com/" + articleSearchArticle.getMultimedia().get(0).getUrl();
            glide.load(url).apply(RequestOptions.centerCropTransform()).into(this.articleImageView);
        }else{
            this.articleImageView.setBackground(itemView.getResources().getDrawable(R.drawable.brand_logo));
        }

        this.descriptionTextView.setText(articleSearchArticle.getLeadParagraph());
        this.categoryTextView.setText(articleSearchArticle.getSectionName());
        this.dateTextView.setText(articleSearchArticle.getPubDate());
    }

}
