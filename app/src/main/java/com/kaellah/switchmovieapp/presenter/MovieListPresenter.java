package com.kaellah.switchmovieapp.presenter;

import android.os.Bundle;

import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.presenter.mappers.MovieListMapper;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.fragments.MovieListView;
import com.kaellah.switchmovieapp.view.fragments.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class MovieListPresenter extends BasePresenter {

    private static final String BUNDLE_MOVIE_LIST_KEY = "BUNDLE_MOVIE_LIST_KEY";

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

    public void loadMovies(int page) {
        showLoadingState();

        Subscription subscription = model.getMoviesList(page)
                .map(mMovieListMapper)
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        hideLoadingState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingState();
                        showError(e);
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        if (movies != null && !movies.isEmpty()) {
                            if (mMovieList == null) {
                                mMovieList = new ArrayList<>();
                            }
                            mMovieList.addAll(movies);
                            mView.showMovieList(mMovieList);

                        } else {
                            mView.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (isRepoListNotEmpty()) {
            outState.putSerializable(BUNDLE_MOVIE_LIST_KEY, new ArrayList<>(mMovieList));
        }
    }

    public void clickMovie(Movie movie) {
        mView.startMovieInfoFragment(movie);
    }

    public void onViewCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mMovieList = (List<Movie>) savedInstanceState.getSerializable(BUNDLE_MOVIE_LIST_KEY);

        } else {
            loadMovies(1);
        }
//        if (isRepoListNotEmpty()) {
//            mView.showMovieList(mMovieList);
//        }
    }

    private boolean isRepoListNotEmpty() {
        return (mMovieList != null && !mMovieList.isEmpty());
    }
}
