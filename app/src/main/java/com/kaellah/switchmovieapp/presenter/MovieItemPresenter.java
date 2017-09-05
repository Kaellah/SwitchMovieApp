package com.kaellah.switchmovieapp.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.kaellah.switchmovieapp.other.AConstant;
import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.fragments.View;
import com.kaellah.switchmovieapp.view.views.IMovieItemView;

import javax.inject.Inject;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 04.09.17
 */

public class MovieItemPresenter extends BasePresenter {

    private IMovieItemView mView;
    private Movie mMovie;

    private final int mHeight;
    private final RequestManager mGlide;

    @Inject
    public MovieItemPresenter(IMovieItemView view, @NonNull Context context) {
        App.getComponent().inject(this);
        mView = view;
        mGlide = mView.isInEditMode() ? null : Glide.with(context);

        final int screenHeight = Utils.getScreenSize(context).y;
        final int scH = (int) (screenHeight / (Utils.isLand(context) ? 1.75 : 2.25));
        mHeight = scH < AConstant.IMAGE_HEIGHT ? scH : AConstant.IMAGE_HEIGHT;
    }

    @Override
    protected View getView() {
        return mView;
    }

    @NonNull
    public ViewGroup.LayoutParams getLayoutParams(@NonNull ViewGroup.LayoutParams params) {
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        params.height = mHeight;

        return params;
    }

    public void setData(@NonNull Movie movie, @NonNull Drawable placeholder, @NonNull ImageView target) {
        mMovie = movie;

        final String url = Utils.getCorrectImageUrl(movie.getPosterPath(), AConstant.IMAGE_WIDTH);
        mGlide
                .load(url)
                .placeholder(placeholder)
                .into(target);
    }

    public Movie getMovie() {
        return mMovie;
    }
}
