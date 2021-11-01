package com.ns.newsapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ns.newsapp.ApiUtility;
import com.ns.newsapp.OnArticleClickListener;
import com.ns.newsapp.R;
import com.ns.newsapp.adapters.ArticleAdapter;
import com.ns.newsapp.data.Article;
import com.ns.newsapp.data.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final boolean d = true;
    private static final String TAG = "MainAc";

    private final String mApiKey = "407d8cb4960a4ec582bd0ee52735e787";
    private ArticleAdapter mAdapter;
    private RecyclerView mRecView;
    private SearchView mSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearch = findViewById(R.id.search_bar);
        mRecView = findViewById(R.id.recycler_view);

        OnArticleClickListener listener = new OnArticleClickListener() {
            @Override
            public void onArticleClick(View v, String url) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        };

        mAdapter = new ArticleAdapter(new ArrayList<>(), listener);

        mRecView.setLayoutManager(new LinearLayoutManager(this));
        mRecView.setAdapter(mAdapter);

        fetchNews();
        mSearch.setOnQueryTextListener(this);
    }


    /**
     * Fetch News Articles form newsApi.org
     */
    private void fetchNews() {
        ApiUtility.getApiInterface().getNews("in", 100, mApiKey).enqueue(new Callback<News>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    mAdapter.getLocalDataSet().addAll(response.body().getArticles());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {

            }
        });
    }


    private void searchNews(final String q) {
        ApiUtility.getApiInterface().search(q,"in",100, mApiKey).enqueue(new Callback<News>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                if (!response.isSuccessful()) return;
                ArrayList<Article> data = mAdapter.getLocalDataSet();
                data.clear();

                assert response.body() != null;
                data.addAll(response.body().getArticles());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (d) Log.d(TAG, "onQueryTextSubmit: query = " + query);
        searchNews(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (d) Log.d(TAG, "onQueryTextSubmit: query = " + newText);
        return false;
    }
}