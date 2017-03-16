package com.kaellah.switchmovieapp.other.di;

import com.kaellah.switchmovieapp.model.api.ApiConstant;
import com.kaellah.switchmovieapp.model.api.ApiInterface;
import com.kaellah.switchmovieapp.model.api.ApiModule;
import com.kaellah.switchmovieapp.other.AConstant;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */
@Module
public class ModelModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return ApiModule.getApiInterface(ApiConstant.BASE_URL);
    }

    @Provides
    @Singleton
    @Named(AConstant.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(AConstant.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }
}
