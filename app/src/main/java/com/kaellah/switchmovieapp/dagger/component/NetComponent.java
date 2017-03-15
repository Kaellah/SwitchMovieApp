package com.kaellah.switchmovieapp.dagger.component;

import com.kaellah.switchmovieapp.activity.MainActivity;
import com.kaellah.switchmovieapp.dagger.module.AppModule;
import com.kaellah.switchmovieapp.dagger.module.NetModule;
import com.kaellah.switchmovieapp.fragment.MoviesListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainActivity activity);

    void inject(MoviesListFragment fragment);
}
