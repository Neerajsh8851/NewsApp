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


    public ArrayList<Article> getArticles() {
        return articles;
    }
}
