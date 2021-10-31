package com.ns.newsapp;

import com.ns.newsapp.data.Article;
import com.ns.newsapp.data.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String baseUrl = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<News> getNews(
      @Query("country") String country,
      @Query("pageSize") int pageSize,
      @Query("ApiKey") String ApiKey
    );


    @GET("everything")
    Call<News> search(
            @Query("q") String keyword,
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("ApiKey") String ApiKey
    );



}
