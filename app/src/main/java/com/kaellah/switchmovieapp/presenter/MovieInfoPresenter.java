package com.kaellah.switchmovieapp.presenter;

import android.os.Bundle;

import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.fragments.MovieInfoView;
import com.kaellah.switchmovieapp.view.fragments.View;

import javax.inject.Inject;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class MovieInfoPresenter extends BasePresenter {

    private static final String EXTRA_MOVIE = "local.EXTRA_MOVIE";

    private MovieInfoView mView;

    private Movie mMovie;

    @Override
    protected View getView() {
        return mView;
    }

    @Inject
    public MovieInfoPresenter() {
    }

    public MovieInfoPresenter(MovieInfoView view) {
        App.getComponent().inject(this);
        mView = view;
    }

    public void onCreate(Bundle bundle) {
        mMovie = bundle.getParcelable(EXTRA_MOVIE);
    }

    public void onViewCreated(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mMovie = savedInstanceState.getParcelable(EXTRA_MOVIE);
        }

        mView.showMovieInfo(mMovie);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (mMovie != null) {
            outState.putParcelable(EXTRA_MOVIE, mMovie);
        }
    }

    public Movie getMovie() {
        return mMovie;
    }
}
