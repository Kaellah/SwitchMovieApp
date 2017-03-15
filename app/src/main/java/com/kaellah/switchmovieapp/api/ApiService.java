package com.kaellah.switchmovieapp.api;

import com.kaellah.switchmovieapp.api.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public interface ApiService {

//    http://api.themoviedb.org/3/movie/now_playing?api_key=ebea8cfca72fdff8d2624ad7bbf78e4c&page=2
//    String BASE_URL = "http://api.themoviedb.org/3/";

    @GET("movie/now_playing")
    Call<List<Movie>> getMovies(@Query("api_key") String api_key, @Query("page") String page);
}
