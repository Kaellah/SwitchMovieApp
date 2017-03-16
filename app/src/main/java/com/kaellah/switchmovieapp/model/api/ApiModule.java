package com.kaellah.switchmovieapp.model.api;

import com.kaellah.switchmovieapp.BuildConfig;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

@SuppressWarnings("ALL")
public class ApiModule {

    private static final boolean ENABLE_LOG = BuildConfig.DEBUG;

    private ApiModule() {
    }

    public static ApiInterface getApiInterface(String url) {
        final OkHttpClient httpClient = new OkHttpClient();

        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(interceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(httpClient);

        final ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
