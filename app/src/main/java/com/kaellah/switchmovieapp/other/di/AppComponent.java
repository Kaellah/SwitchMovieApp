package com.kaellah.switchmovieapp.other.di;

import com.kaellah.switchmovieapp.model.ModelImpl;
import com.kaellah.switchmovieapp.presenter.BasePresenter;
import com.kaellah.switchmovieapp.presenter.MovieListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */
@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(MovieListPresenter movieListPresenter);
}
