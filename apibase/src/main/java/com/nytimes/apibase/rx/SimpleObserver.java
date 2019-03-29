package com.nytimes.apibase.rx;


import com.nytimes.apibase.logger.Logger;

import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link DisposableObserver} and {@link io.reactivex.Observer} implementation with default methods implementation.
 *
 * @author Praveen
 */
public class SimpleObserver<T> extends DisposableObserver<T> {

    // Log tag
    private static final String TAG = SimpleObserver.class.getSimpleName();

    @Override
    public void onComplete() {
        if (!isDisposed()) {
            dispose();
        }
    }

    @Override
    public void onError(Throwable e) {
        Logger.e(TAG, "onError: " + e.toString());
    }


    @Override
    public void onNext(T o) {
        if (null == o) return;
            Logger.e(TAG, "onNext: " + o.toString());
    }
}
