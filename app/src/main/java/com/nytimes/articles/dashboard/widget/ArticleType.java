package com.nytimes.articles.dashboard.widget;

public enum ArticleType {

    ARTICLE_1("1"), ARTICLE_7("7"), ARTICLE_30("30");

    private String period;

    ArticleType(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }
}
