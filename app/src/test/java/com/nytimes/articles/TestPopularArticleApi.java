package com.nytimes.articles;

import android.content.Context;

import com.nytimes.apibase.ApiBase;
import com.nytimes.apibase.BuildConfig;
import com.nytimes.apibase.services.PopularArticleService;
import com.nytimes.apibase.services.models.ViewedArticles;
import com.nytimes.articles.dashboard.widget.ArticleType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

/**
 * @author Praveen
 *
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestPopularArticleApi {

    private PopularArticleService articleService;

    @Before
    public void setUp() {
        ApiBase.with(Mockito.mock(Context.class), com.nytimes.articles.BuildConfig.DEBUG);
        articleService = ApiBase.getInstance().getPopularArticleService();
    }

    @Test
    public void testPopularArticleOne() {
        ViewedArticles viewedArticles = articleService
                .fetchViewedPopularArticles(ArticleType.ARTICLE_1.getPeriod(), BuildConfig.NY_APP_ID)
                .blockingLast();
        assertNotNull(viewedArticles);
    }


    @Test
    public void testPopularArticleSeven() {
        ViewedArticles viewedArticles = articleService
                .fetchViewedPopularArticles(ArticleType.ARTICLE_7.getPeriod(), BuildConfig.NY_APP_ID)
                .blockingLast();
        assertNotNull(viewedArticles);
    }


    @Test
    public void testPopularArticleThirty() {
        ViewedArticles viewedArticles = articleService
                .fetchViewedPopularArticles(ArticleType.ARTICLE_30.getPeriod(), BuildConfig.NY_APP_ID)
                .blockingLast();
        assertNotNull(viewedArticles);
    }
}