package com.kaellah.switchmovieapp.other.di.view;

import com.kaellah.switchmovieapp.view.fragments.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */
@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(MovieListFragment fragment);
}
