package com.kaellah.switchmovieapp.other.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kaellah.switchmovieapp.presenter.MovieInfoPresenter;
import com.kaellah.switchmovieapp.presenter.MovieItemPresenter;
import com.kaellah.switchmovieapp.view.fragments.MovieInfoView;
import com.kaellah.switchmovieapp.view.views.IMovieItemView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */
@Module
public class ViewModule {

    @Provides
    MovieInfoPresenter provideMovieInfoPresenter(MovieInfoView view, @NonNull Context context) {
        return new MovieInfoPresenter(view, context);
    }

    @Provides
    MovieItemPresenter provideMovieItemPresenter(IMovieItemView v, @NonNull Context context) {
        return new MovieItemPresenter(v, context);
    }
}
