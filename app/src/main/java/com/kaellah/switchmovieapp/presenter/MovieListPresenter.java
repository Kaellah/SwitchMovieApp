package com.kaellah.switchmovieapp.presenter;

import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.presenter.mappers.MovieListMapper;
import com.kaellah.switchmovieapp.view.fragments.MovieListView;
import com.kaellah.switchmovieapp.view.fragments.View;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class MovieListPresenter extends BasePresenter {

    private static final String BUNDLE_REPO_LIST_KEY = "BUNDLE_REPO_LIST_KEY";

    @Inject
    protected MovieListMapper mMovieListMapper;

    private MovieListView mView;

    private List<Movie> mMovieList;

    @Inject
    public MovieListPresenter() {
    }

    public MovieListPresenter(MovieListView view) {
        App.getComponent().inject(this);
        mView = view;
    }

    @Override
    protected View getView() {
        return mView;
    }

    public void loadMovies() {

    }
}
