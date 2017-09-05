package com.kaellah.switchmovieapp.other.di.view;

import android.content.Context;
import android.support.annotation.NonNull;

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

    private final Context mContext;

    public MovieItemViewDynamicModule(IMovieItemView view, @NonNull Context context) {
        mView = view;
        mContext = context;
    }

    @Provides
    MovieItemPresenter provideMovieItemPresenter(@NonNull Context context) {
        return new MovieItemPresenter(mView, context);
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
