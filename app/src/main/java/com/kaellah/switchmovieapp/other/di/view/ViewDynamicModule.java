package com.kaellah.switchmovieapp.other.di.view;

import com.kaellah.switchmovieapp.presenter.MovieListPresenter;
import com.kaellah.switchmovieapp.view.fragments.MovieListView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */
@Module
public class ViewDynamicModule {

    private MovieListView mView;

    public ViewDynamicModule(MovieListView view) {
        mView = view;
    }

    @Provides
    MovieListPresenter provideMovieListPresenter() {
        return new MovieListPresenter(mView);
    }
}
