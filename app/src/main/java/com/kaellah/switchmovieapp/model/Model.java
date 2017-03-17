package com.kaellah.switchmovieapp.model;

import com.kaellah.switchmovieapp.model.dto.MoviesListAnswerDTO;

import rx.Observable;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public interface Model {

    Observable<MoviesListAnswerDTO> getMoviesList(int page);
}
