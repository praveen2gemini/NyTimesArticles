package com.nytimes.articles.dashboard.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.nytimes.apibase.services.models.Results;
import com.nytimes.articles.core.ViewTypeAdapter;
import com.nytimes.articles.dashboard.widget.SingleArticleView;

import java.util.ArrayList;

/**
 * @author Praveen on 29/03/19.
 */
public abstract class PopularArticleAdapter extends ViewTypeAdapter<SingleArticleView, Results>
        implements View.OnClickListener, Filterable {

    private final ArrayList<Results> popularArticles = new ArrayList<>();
    private final ArrayList<Results> filteredPopularArticles = new ArrayList<>();
    private Context context;

    public PopularArticleAdapter(@Nullable Context context) {
        if (null == context) {
            throw new NullPointerException("Context cannot be NULL/Invalid!");
        }
        this.context = context;
    }

    public void upadtePopularArticles(ArrayList<Results> popularArticles) {
        this.popularArticles.clear();
        this.filteredPopularArticles.clear();
        this.popularArticles.addAll(popularArticles);
        this.filteredPopularArticles.addAll(popularArticles);
        notifyDataSetChanged();
    }

    @Override
    protected SingleArticleView createView(int viewType) {
        SingleArticleView articleView = new SingleArticleView(context);
        articleView.setOnClickListener(this);
        return articleView;
    }


    @Override
    protected void preRecycleData(SingleArticleView singleArticleView, int position) {
        super.preRecycleData(singleArticleView, position);
        singleArticleView.setPosition(position);
    }

    @Override
    protected Results getValue(int position) {
        return filteredPopularArticles.get(position);
    }

    @Override
    public int getItemCount() {
        return filteredPopularArticles.size();
    }

    @Override
    public void onClick(View v) {
        int position = ((SingleArticleView) v).getPosition();
        onArticleSelected(position, getValue(position));
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                ArrayList<Results> filteredList = new ArrayList<>();
                if (charString.isEmpty()) {
                    filteredList.addAll(popularArticles);
                } else {
                    for (Results row : popularArticles) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())
                                || row.getByline().contains(charString)) {
                            filteredList.add(row);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                filteredPopularArticles.clear();
                filteredPopularArticles.addAll((ArrayList<Results>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public abstract void onArticleSelected(int position, Results selectedResult);
}
