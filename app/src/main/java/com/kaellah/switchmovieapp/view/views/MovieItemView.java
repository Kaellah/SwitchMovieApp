package com.kaellah.switchmovieapp.view.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.presenter.MovieListPresenter;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.adapters.DataEntity;
import com.kaellah.switchmovieapp.view.fragments.View;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */

@SuppressLint("ViewConstructor")
public class MovieItemView extends FrameLayout
        implements DataEntity<Movie, MovieItemView> {

    private static final int IMAGE_WIDTH = 342;
    private static final int IMAGE_HEIGHT = IMAGE_WIDTH * 3;
//    private static int sCorner = 15;
//    private static int sMargin = 2;

    // VALUES
    private final RequestManager mGlide;
//    private final RoundedCornersTransformation mTransformation; // TODO uncomment if will have time
    private Movie mMovie;

    @BindDrawable(R.drawable.rect_corners_light)
    protected Drawable mPlaceholder;

    @Bind(R.id.iv_movie)
    protected ImageView mImageView;

    // PRESENTER
    private final MovieListPresenter mPresenter;

    @OnClick(R.id.iv_movie)
    public void onClickMovie(View v) {
        if( mPresenter != null) {
            mPresenter.clickMovie(mMovie);
        }
    }

    public MovieItemView(Context context, MovieListPresenter presenter) {
        super(context);
        {
            inflate(context, R.layout.view_item_movie, this);
        }
        ButterKnife.bind(this);

        mGlide = isInEditMode() ? null : Glide.with(context);
//        final int corner = getResources().getDimensionPixelSize(R.dimen.radius);
//        mTransformation = isInEditMode() ? null : new RoundedCornersTransformation(context, corner, 2);
        mPresenter = presenter;
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        params.width = LayoutParams.MATCH_PARENT;
        params.height = IMAGE_HEIGHT;

        super.setLayoutParams(params);
    }

    @Override
    public void setData(@NonNull Movie movie, @NonNull Object... objects) {
        mMovie = movie;

        final String url = Utils.getCorrectImageUrl(movie.getPosterPath(), IMAGE_WIDTH);
        mGlide
                .load(url)
                .placeholder(mPlaceholder)
//                .bitmapTransform(mTransformation)
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        invalidate();
//                        return false;
//                    }
//                })
                .into(mImageView);
    }

    @NonNull
    @Override
    public MovieItemView getSelf() {
        return this;
    }
}
