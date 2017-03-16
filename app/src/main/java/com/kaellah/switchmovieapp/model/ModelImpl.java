package com.kaellah.switchmovieapp.model;

import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.model.api.ApiConstant;
import com.kaellah.switchmovieapp.model.api.ApiInterface;
import com.kaellah.switchmovieapp.model.dto.MovieDTO;
import com.kaellah.switchmovieapp.other.AConstant;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    @Named(AConstant.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(AConstant.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread); // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
    }

    @Override
    public Observable<List<MovieDTO>> getMoviesList(int page) {
        return apiInterface
                .getMovies(ApiConstant.API_KEY, page)
                .compose(applySchedulers());
    }


    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
