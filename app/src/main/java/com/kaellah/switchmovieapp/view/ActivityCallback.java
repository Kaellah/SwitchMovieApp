package com.kaellah.switchmovieapp.view;

import com.kaellah.switchmovieapp.presenter.vo.Movie;

public interface ActivityCallback {

    void startMovieInfoFragment(Movie movie);

    void showProgressBar();

    void hideProgressBar();

}
