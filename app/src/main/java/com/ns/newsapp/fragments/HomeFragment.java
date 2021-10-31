package com.ns.newsapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ns.newsapp.ApiUtility;
import com.ns.newsapp.R;
import com.ns.newsapp.adapters.ArticleAdapter;
import com.ns.newsapp.data.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    private String mApiKey = "407d8cb4960a4ec582bd0ee52735e787";
    private ArticleAdapter mAdapter;
    private RecyclerView mRecView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mRecView = v.findViewById(R.id.recycler_view);
        mAdapter = new ArticleAdapter(new ArrayList<>());
        mRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecView.setAdapter(mAdapter);

        fetchNews();

        return v;
    }

    private void fetchNews() {
       ApiUtility.getApiInterface().getNews("in", 100, mApiKey).enqueue(new Callback<News>() {
           @Override
           public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
               if (response.isSuccessful()) {
                   assert response.body() != null;
                   mAdapter.getLocalDataSet().addAll(response.body().getArticles());
                   mAdapter.notifyDataSetChanged();
               }
           }

           @Override
           public void onFailure(Call<News> call, Throwable t) {

           }
       });
    }


}