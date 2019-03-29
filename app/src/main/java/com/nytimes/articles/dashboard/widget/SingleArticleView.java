package com.nytimes.articles.dashboard.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nytimes.apibase.services.models.MediaMetaData;
import com.nytimes.apibase.services.models.Results;
import com.nytimes.articles.R;
import com.nytimes.articles.core.Recyclable;
import com.nytimes.articles.core.circle_transformation.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Praveen on 29/03/19.
 */
public class SingleArticleView extends FrameLayout implements Recyclable<Results> {

    @BindView(R.id.article_avatar)
    ImageView articleAvatarView;

    @BindView(R.id.article_title)
    TextView articleTitleView;

    @BindView(R.id.article_sub_title)
    TextView articleSubTitleView;

    @BindView(R.id.article_date_view)
    TextView articlePublishDateView;

    private int position;

    public SingleArticleView(@NonNull Context context) {
        this(context, null);
    }

    public SingleArticleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleArticleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configureView(context, R.layout.view_single_article);
    }

    private void configureView(@NonNull Context context, @LayoutRes int articleLayoutResourceId) {
        inflate(context, articleLayoutResourceId, this);
        ButterKnife.bind(this);
    }

    @Override
    public void recycle(@Nullable Results results) {
        if (null == results) return;
        articleTitleView.setText(results.getTitle());
        articleSubTitleView.setText(TextUtils.isEmpty(results.getByline()) ? "N/A" : results.getByline());
        articlePublishDateView.setText(results.getPublishedDate());
        if (null != results.getMedia() && !results.getMedia().isEmpty()) {
            ArrayList<MediaMetaData> metaData = results.getMedia().get(0).getMediaMetaData();
            if (null != metaData && !metaData.isEmpty()) {
                Picasso.get().load(metaData.get(0).getUrl())
                        .resizeDimen(R.dimen.thumbnail_image_h_w, R.dimen.thumbnail_image_h_w)
                        .transform(new CircleTransform())
                        .into(articleAvatarView);
            }
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
