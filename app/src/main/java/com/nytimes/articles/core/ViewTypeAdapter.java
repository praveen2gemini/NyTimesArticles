package com.nytimes.articles.core;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * An abstraction of RecyclerView.Adapter to provide android.widget.Adapter like
 * interface.
 *
 * @author Praveen
 */
public abstract class ViewTypeAdapter<V extends View, T>
        extends RecyclerView.Adapter<ViewTypeAdapter.ViewHolder<V>> {

    // Log tag
    private static final String TAG = ViewTypeAdapter.class.getSimpleName();

    @NonNull
    @Override
    public ViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        V v = createView(viewType);
        return new ViewHolder<>(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<V> holder, int position) {
        View v = holder.itemView;
        if (v instanceof Recyclable) {
            preRecycleData((V) v, position);
            ((Recyclable<T>) v).recycle(getValue(position));
        } else {
            throw new RuntimeException("View should implement Recyclable");
        }
        bind((V) v, position);
    }

    /**
     * Creates a new view instance.
     *
     * @param viewType
     * @return
     */
    protected abstract V createView(int viewType);

    /**
     * Returns the object that represents the view at the specified position.
     *
     * @param position
     * @return
     */
    protected abstract T getValue(int position);

    /**
     * Perform optional view binding.
     *
     * @param v
     * @param position
     */
    protected void bind(V v, int position) {

    }

    /**
     * Stores optional view position in adapter.
     *
     * @param v
     * @param position
     */
    protected void preRecycleData(V v, int position) {

    }

    /**
     * Generic ViewHolder
     */
    public static class ViewHolder<V extends View> extends RecyclerView.ViewHolder {
        public ViewHolder(V itemView) {
            super(itemView);
        }
    }

}
