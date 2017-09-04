package com.kaellah.switchmovieapp.other.di.view;

import com.kaellah.switchmovieapp.presenter.MovieItemPresenter;
import com.kaellah.switchmovieapp.view.views.IMovieItemView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */
@Module
public class MovieItemViewDynamicModule {

    private IMovieItemView mView;

    public MovieItemViewDynamicModule(IMovieItemView view) {
        mView = view;
    }

    @Provides
    MovieItemPresenter provideMovieItemPresenter() {
        return new MovieItemPresenter(mView);
    }
}
