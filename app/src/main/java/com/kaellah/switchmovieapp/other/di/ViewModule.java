package com.kaellah.switchmovieapp.other.di;

import com.kaellah.switchmovieapp.presenter.MovieInfoPresenter;
import com.kaellah.switchmovieapp.presenter.MovieItemPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */
@Module
public class ViewModule {

    @Provides
    MovieInfoPresenter provideMovieInfoPresenter() {
        return new MovieInfoPresenter();
    }

    @Provides
    MovieItemPresenter provideMovieItemPresenter() {
        return new MovieItemPresenter();
    }
}
