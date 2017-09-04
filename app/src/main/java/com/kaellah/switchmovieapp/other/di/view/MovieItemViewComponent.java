package com.kaellah.switchmovieapp.other.di.view;

import com.kaellah.switchmovieapp.view.views.MovieItemView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */
@Singleton
@Component(modules = {MovieItemViewDynamicModule.class})
public interface MovieItemViewComponent {

    void inject(MovieItemView view);
}
