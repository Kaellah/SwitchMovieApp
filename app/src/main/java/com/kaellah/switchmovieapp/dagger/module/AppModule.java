package com.kaellah.switchmovieapp.dagger.module;

import android.support.annotation.NonNull;

import com.kaellah.switchmovieapp.application.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */
@Module
public class AppModule {

    private App mApp;

    public AppModule(@NonNull App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    App provideApplication() {
        return mApp;
    }


}
