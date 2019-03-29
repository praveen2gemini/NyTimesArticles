package com.nytimes.apibase.services;

import com.nytimes.apibase.services.models.ViewedArticles;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * ArticleService Web Service
 *
 * @author Praveen
 */

public interface PopularArticleService {

    @GET("viewed/{period}.json")
    Observable<ViewedArticles> fetchViewedPopularArticles(@Path("period") String period, @Query("api-key") String apiKey);
}