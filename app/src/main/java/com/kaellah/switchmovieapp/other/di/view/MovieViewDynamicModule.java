package com.kaellah.switchmovieapp.other.di.view;

import com.kaellah.switchmovieapp.presenter.MovieInfoPresenter;
import com.kaellah.switchmovieapp.view.fragments.MovieInfoView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */
@Module
public class MovieViewDynamicModule {

    private MovieInfoView mView;

    public MovieViewDynamicModule(MovieInfoView view) {
        mView = view;
    }

    @Provides
    MovieInfoPresenter provideMovieListPresenter() {
        return new MovieInfoPresenter(mView);
    }
}
