package com.kaellah.switchmovieapp.other.di.view;

import android.content.Context;
import android.support.annotation.NonNull;

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

    private final Context mContext;

    public MovieViewDynamicModule(MovieInfoView view, @NonNull Context context) {
        mView = view;
        mContext = context;
    }

    @Provides
    MovieInfoPresenter provideMovieListPresenter(@NonNull Context context) {
        return new MovieInfoPresenter(mView, context);
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
