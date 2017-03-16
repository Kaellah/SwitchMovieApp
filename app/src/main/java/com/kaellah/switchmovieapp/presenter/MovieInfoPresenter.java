package com.kaellah.switchmovieapp.presenter;

import android.os.Bundle;

import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.fragments.MovieInfoView;
import com.kaellah.switchmovieapp.view.fragments.View;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class MovieInfoPresenter extends BasePresenter {

    private static final String BUNDLE_MOVIE_KEY = "BUNDLE_MOVIE_KEY";

    private MovieInfoView mView;

    private Movie mMovie;

    @Override
    protected View getView() {
        return mView;
    }

    public void onCreate(MovieInfoView view, Movie movie) {
        App.getComponent().inject(this);
        mView = view;
        mMovie = movie;
    }

    public void onCreateView(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mMovie = (Movie) savedInstanceState.getSerializable(BUNDLE_MOVIE_KEY);
        }

        mView.showMovieInfo(mMovie);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (mMovie != null) {
            outState.putSerializable(BUNDLE_MOVIE_KEY, mMovie);
        }
    }
}
