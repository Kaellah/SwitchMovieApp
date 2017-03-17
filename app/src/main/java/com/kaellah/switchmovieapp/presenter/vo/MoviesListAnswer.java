package com.kaellah.switchmovieapp.presenter.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class MoviesListAnswer
        implements Serializable {

    private List<Movie> mMovieList;
    private Integer mPage;

    public MoviesListAnswer(List<Movie> movies, Integer page) {
        mMovieList = movies;
        mPage = page;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        mPage = page;
    }
}
