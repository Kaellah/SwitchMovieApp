package com.kaellah.switchmovieapp.presenter.mappers;

import com.kaellah.switchmovieapp.model.dto.MovieDTO;
import com.kaellah.switchmovieapp.presenter.vo.Movie;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class MovieListMapper implements Func1<List<MovieDTO>, List<Movie>> {

    @Inject
    public MovieListMapper() {
    }

    @Override
    public List<Movie> call(List<MovieDTO> movieDTOs) {
        if (movieDTOs == null) {
            return null;
        }

        return Observable.from(movieDTOs)
                .map(movieDTO -> new Movie(movieDTO.getOriginalTitle(), movieDTO.getOverview()))
                .toList()
                .toBlocking()
                .first();
    }
}
