package com.nytimes.articles.core;

/**
 * @author Praveen
 */
public interface Recyclable<T> {
    void recycle(T t);
}
