package com.kaellah.switchmovieapp.presenter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

    @Inject
    public MovieItemPresenter() {
    }

    public MovieItemPresenter(IMovieItemView view) {
        App.getComponent().inject(this);
        mView = view;
    }

    @Override
    protected View getView() {
        return mView;
    }

    @NonNull
    public ViewGroup.LayoutParams getLayoutParams(@NonNull ViewGroup.LayoutParams params) {
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
//        params.height = mHeight;

        return params;
    }

    public void setData(@NonNull Movie movie, @NonNull Object... objects) {
        mMovie = movie;

        final String url = Utils.getCorrectImageUrl(movie.getPosterPath(), AConstant.IMAGE_WIDTH);
//        mGlide
//                .load(url)
//                .placeholder(mPlaceholder)
//                .into(mImageView);
    }
}
