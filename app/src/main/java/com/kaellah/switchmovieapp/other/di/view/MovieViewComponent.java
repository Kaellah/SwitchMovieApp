package com.kaellah.switchmovieapp.other.di.view;

import com.kaellah.switchmovieapp.view.fragments.MovieInfoFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */
@Singleton
@Component(modules = {MovieViewDynamicModule.class})
public interface MovieViewComponent {

    void inject(MovieInfoFragment fragment);
}
