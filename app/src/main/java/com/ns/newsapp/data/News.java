package com.ns.newsapp.data;

import java.util.ArrayList;

public class News {
    String status;
    int totalResults;
    ArrayList<Article> articles;


    public News(String status, int totalResults, ArrayList<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }


    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
