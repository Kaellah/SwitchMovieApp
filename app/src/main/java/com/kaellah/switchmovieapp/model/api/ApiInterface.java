package com.kaellah.switchmovieapp.model.api;

import com.kaellah.switchmovieapp.model.dto.MoviesListAnswerDTO;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public interface ApiInterface {

    @GET("movie/now_playing")
    Observable<MoviesListAnswerDTO> getMovies(@Query("api_key") String api_key, @Query("page") int page);
}
