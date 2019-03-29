package com.nytimes.apibase.di;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nytimes.apibase.ApiBase;
import com.nytimes.apibase.gson.ResultTypeConverter;
import com.nytimes.apibase.services.Configuration;
import com.nytimes.apibase.services.PopularArticleService;
import com.nytimes.apibase.services.models.Results;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Praveen
 */
@Module
public class ApiBaseModule {

    private Context context;

    public ApiBaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(ApiBase.getInstance().getRetrofitLogLevel());
        return loggingInterceptor;
    }


    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(Results.class, new ResultTypeConverter());
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    @Provides
    @Singleton
    @Named("BasicHttpClient")
    OkHttpClient providesOkHttpClient(
            HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 30 seconds Connection Timeout
                .readTimeout(60, TimeUnit.SECONDS) // 60 seconds Read Timeout
                .writeTimeout(60, TimeUnit.SECONDS) // 60 seconds Write Timeout
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .build();
    }

    @Provides
    @Singleton
    @Named("RootApiRetrofit")
    Retrofit providesRootApiRetrofit(@Named("BasicHttpClient") OkHttpClient client,
                                     GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .client(client)
                .baseUrl(Configuration.BASE_URL).build();
    }


    @Provides
    @Singleton
    PopularArticleService providesArticleService(@Named("RootApiRetrofit") Retrofit retrofit) {
        return retrofit.create(PopularArticleService.class);
    }


}