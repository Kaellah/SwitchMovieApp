package com.kaellah.switchmovieapp.view.views;

import android.support.annotation.NonNull;

import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.fragments.View;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 04.09.17
 */

public interface IMovieItemView extends View {

    void showMovieInfo(@NonNull Movie movie);
}
