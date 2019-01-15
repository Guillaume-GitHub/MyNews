package com.android.guillaume.mynews.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.guillaume.mynews.R;

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

    public void updateRecyclerViewItem(int position){
        this.articleImageView.setBackgroundColor(R.drawable.ic_launcher_background);
        this.categoryTextView.setText("Category n° " + String.valueOf(position));
        this.dateTextView.setText("Date : 01/01/2019");
        this.descriptionTextView.setText("This is a description of an article from the Nex York Time API");
    }

}
