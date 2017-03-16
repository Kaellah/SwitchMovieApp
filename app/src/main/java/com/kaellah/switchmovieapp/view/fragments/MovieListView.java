package com.kaellah.switchmovieapp.view.fragments;

import com.kaellah.switchmovieapp.presenter.vo.Movie;

import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public interface MovieListView extends View {

    void showMovieList(List<Movie> vo);

    void showEmptyList();

    void loadMore();

    void startMovieInfoFragment(Movie movie);
}
