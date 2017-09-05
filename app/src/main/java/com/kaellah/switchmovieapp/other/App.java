package com.kaellah.switchmovieapp.other;

import android.app.Application;

import com.kaellah.switchmovieapp.other.di.AppComponent;
import com.kaellah.switchmovieapp.other.di.DaggerAppComponent;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent
                .builder()
                .build();
    }
}
