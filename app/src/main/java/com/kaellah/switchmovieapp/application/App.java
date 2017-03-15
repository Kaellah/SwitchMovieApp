package com.kaellah.switchmovieapp.application;

import android.app.Application;
import android.support.annotation.NonNull;

import com.kaellah.switchmovieapp.dagger.component.DaggerNetComponent;
import com.kaellah.switchmovieapp.dagger.component.NetComponent;
import com.kaellah.switchmovieapp.dagger.module.AppModule;
import com.kaellah.switchmovieapp.dagger.module.NetModule;

import retrofit2.Retrofit;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public class App extends Application {

    private NetComponent mNetComponent;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    @NonNull
    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
