package com.ns.newsapp.adapters;


import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ns.newsapp.OnArticleClickListener;
import com.ns.newsapp.R;
import com.ns.newsapp.activities.WebActivity;
import com.ns.newsapp.data.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {


    private final ArrayList<Article> localDataSet;
    private final OnArticleClickListener onArticleClick;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;
        private final TextView mTime;
        private final TextView mDescription;
        private final TextView mSource;
        private final ImageView mImage;
        private final CardView cardView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            mTitle = view.findViewById(R.id.title);
            mTime = view.findViewById(R.id.time);
            mDescription = view.findViewById(R.id.description);
            mSource = view.findViewById(R.id.source);
            mImage = view.findViewById(R.id.image);
            cardView = view.findViewById(R.id.card_view);
        }

        public void setTitle(final String text) {
            mTitle.setText(text);
        }

        public void setDescription(final String text) {
            mDescription.setText(text);
        }

        public void setSource(final String text) {
            mSource.setText(text);
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public ArticleAdapter(ArrayList<Article> dataSet, OnArticleClickListener onArticleClick) {
        localDataSet = dataSet;
        this.onArticleClick = onArticleClick;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_layout, viewGroup, false);
        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        // bind the model to the view
        Article article = localDataSet.get(position);

        // calculate time ago
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        CharSequence ago = "";

        try {
            long time = Objects.requireNonNull(sdf.parse(article.publishedAt)).getTime();
            long now = System.currentTimeMillis();
            ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            article.publishedAt = ago.toString();
        }catch (Exception e) {

        }

        viewHolder.setTitle(article.title);
        viewHolder.mTime.setText(ago);
        viewHolder.setSource(article.source.name);
        viewHolder.setDescription(article.description);
        Glide.with(viewHolder.mImage.getContext()).load(article.urlToImage).into(viewHolder.mImage);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArticleClick.onArticleClick(v, article.url);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


    public ArrayList<Article> getLocalDataSet() {
        return localDataSet;
    }
}
