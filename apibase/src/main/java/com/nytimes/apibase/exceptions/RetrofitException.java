package com.nytimes.apibase.exceptions;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Custom exception handling for {@link Retrofit} exceptions
 *
 * @author Praveen
 */
public class RetrofitException extends RuntimeException {
    private final String url;
    private final Response response;
    private final Kind kind;

    private RetrofitException(String message, String url, Response response, Kind kind,
                              Throwable exception) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
    }

    public static RetrofitException httpError(String url, Response response) {
        String message = response.code() + " " + response.message();
        return new RetrofitException(message, url, response, Kind.HTTP, null);
    }

    public static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception);
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception);
    }

    public static RetrofitException composeRetroException(Throwable e) {
        RetrofitException retrofitException;
        Response httpResponse = null;
        if (e instanceof retrofit2.HttpException) {
            httpResponse = ((retrofit2.HttpException) e).response();
        } else if (e instanceof RuntimeException && (e.getCause() instanceof HttpException)) {
            httpResponse = ((HttpException) e.getCause()).response();
        }
        if (null != httpResponse) {
            retrofitException = RetrofitException.httpError(
                    httpResponse.raw().request().url().toString(), httpResponse);
        } else if (e instanceof IOException) {
            retrofitException = RetrofitException.networkError((IOException) e);
        } else {
            retrofitException = RetrofitException.unexpectedError(e);
        }
        return retrofitException;
    }

    public static <T> T getErrorBodyAs(Class<T> type, Retrofit retrofit,
                                       @NonNull Response response) throws IOException {
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        return converter.convert(response.errorBody());
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}