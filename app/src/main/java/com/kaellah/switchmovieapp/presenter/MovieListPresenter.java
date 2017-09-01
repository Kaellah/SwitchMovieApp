package com.kaellah.switchmovieapp.presenter;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.kaellah.switchmovieapp.other.AConstant;
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

    @Inject
    protected MovieListMapper mMovieListMapper;

    private MovieListView mView;

    private List<Movie> mMovieList;

    private int mPage = 0;

    private Subscription mSubscription;

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
        Log.e("loadMovies", "page = " + page);

        mPage = page < 0 ? mPage + 1 : page;
        showLoadingState();

        mSubscription = model.getMoviesList(mPage)
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
                            mView.showMovieList(movies);

                        } else {
                            mView.showEmptyList();
                        }
                        Log.e("loadMovies", "size = " + mMovieList.size());
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (isMovieListNotEmpty()) {
            outState.putParcelableArrayList(AConstant.EXTRA_MOVIE_LIST, (ArrayList<? extends Parcelable>) mMovieList);
            outState.putInt(AConstant.EXTRA_PAGE, mPage);
        }
    }

    @Override
    public void onViewCreated(Bundle b) {
        if (b != null) {
            //noinspection unchecked
            mMovieList = b.getParcelableArrayList(AConstant.EXTRA_MOVIE_LIST);
            mPage = b.getInt(AConstant.EXTRA_PAGE);

            if (isMovieListNotEmpty()) {
                mView.showMovieList(mMovieList);

            } else {
                mView.showEmptyList();
            }

        } else {
            if (!isMovieListNotEmpty()) {
                loadMovies(1);
            }
        }
    }

    private boolean isMovieListNotEmpty() {
        return (mMovieList != null && !mMovieList.isEmpty());
    }
}
