package com.kaellah.switchmovieapp.view.adapters;

import android.support.annotation.NonNull;
import android.view.View;

import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.views.MovieItemView;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */

public class MoviesAdapter extends BaseRecyclerAdapter<Movie, MovieItemView> {

    public MoviesAdapter() {
        super(null);
    }

    @NonNull
    @Override
    protected MovieItemView obtain(@NonNull View parent, int viewType) {
        return new MovieItemView(parent.getContext());
    }
}
