package com.kaellah.switchmovieapp.view.fragments;

import android.graphics.drawable.BitmapDrawable;

import com.kaellah.switchmovieapp.presenter.vo.Movie;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public interface MovieInfoView extends View {

    void showMovieInfo(Movie movie);

    void setBackground(BitmapDrawable ob);
}
