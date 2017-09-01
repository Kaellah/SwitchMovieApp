package com.kaellah.switchmovieapp.presenter;

import android.os.Bundle;

import com.kaellah.switchmovieapp.other.AConstant;
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

//    public void onCreate(Bundle bundle) {
//        mMovie = bundle.getParcelable(AConstant.EXTRA_MOVIE);
//    }

    @Override
    public void onViewCreated(Bundle b) {
        if (b != null) {
            mMovie = b.getParcelable(AConstant.EXTRA_MOVIE);
            mView.showMovieInfo(mMovie);

        } else {
            mView.showError("No data");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mMovie != null) {
            outState.putParcelable(AConstant.EXTRA_MOVIE, mMovie);
        }
    }

    public Movie getMovie() {
        return mMovie;
    }
}
