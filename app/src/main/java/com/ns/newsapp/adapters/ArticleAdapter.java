package com.ns.newsapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ns.newsapp.R;
import com.ns.newsapp.data.Article;

import java.util.ArrayList;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {


    private ArrayList<Article> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;
        private final TextView mTime;
        private final TextView mDescription;
        private final TextView mSource;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            mTitle = view.findViewById(R.id.title);
            mTime = view.findViewById(R.id.time);
            mDescription = view.findViewById(R.id.description);
            mSource = view.findViewById(R.id.source);
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

        public void setTime(final String text) {
            mTime.setText(text);
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public ArticleAdapter(ArrayList<Article> dataSet) {
        localDataSet = dataSet;
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
        viewHolder.setTitle(article.title);
        viewHolder.setTime(article.publishedAt);
        viewHolder.setSource(article.source.name);
        viewHolder.setDescription(article.description);
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
