package com.nytimes.apibase.di;

import com.nytimes.apibase.ApiBase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger 2 component for API.
 *
 * @author Praveen
 */
@Component(modules = {ApiBaseModule.class})
@Singleton
public interface ApiBaseComponent {
    void inject(ApiBase apiBase);
}
