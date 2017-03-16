package com.kaellah.switchmovieapp.model.api;

import com.kaellah.switchmovieapp.model.dto.MovieDTO;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public interface ApiInterface {

//    http://api.themoviedb.org/3/movie/now_playing?api_key=ebea8cfca72fdff8d2624ad7bbf78e4c&page=2
//    String BASE_URL = "http://api.themoviedb.org/3/";

    @GET("movie/now_playing")
    Observable<List<MovieDTO>> getMovies(@Query("api_key") String api_key, @Query("page") int page);
}
