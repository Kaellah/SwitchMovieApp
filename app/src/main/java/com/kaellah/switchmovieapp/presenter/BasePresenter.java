package com.kaellah.switchmovieapp.presenter;

import android.os.Bundle;

import com.kaellah.switchmovieapp.model.Model;
import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.view.fragments.View;

import javax.inject.Inject;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public abstract class BasePresenter
        implements Presenter {

    @Inject
    protected Model model;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onViewCreated(Bundle b) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    protected abstract View getView();

    protected void showLoadingState() {
        getView().showLoading();
    }

    protected void hideLoadingState() {
        getView().hideLoading();
    }

    protected void showError(Throwable e) {
        getView().showError(e.getMessage());
    }
}
