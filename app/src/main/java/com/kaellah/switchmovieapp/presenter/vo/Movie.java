package com.kaellah.switchmovieapp.presenter.vo;

import java.io.Serializable;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class Movie
        implements Serializable {

    private String mOriginalTitle;
    private String mOverview;
    private String mPosterPath;

    public Movie(String originalTitle, String overview, String posterPath) {
        mOriginalTitle = originalTitle;
        mOverview = overview;
        mPosterPath = posterPath;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }
}
