package com.kaellah.switchmovieapp.presenter.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.kaellah.switchmovieapp.model.dto.MovieDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class Movie
        implements Serializable, Parcelable {

    private String mOriginalTitle;
    private String mOverview;
    private String mPosterPath;
    private Boolean mAdult;
    private String mBackdropPath;
    private List<Integer> mGenreIds;
    private Integer mId;
    private String mOriginalLanguage;
    private String mReleaseDate;
    private Float mPopularity;
    private String mTitle;
    private Boolean mVideo;
    private Float mVoteAverage;
    private Integer mVoteCount;

    public Movie(MovieDTO movieDTO) {
        mOriginalTitle = movieDTO.getOriginalTitle();
        mOverview = movieDTO.getOverview();
        mPosterPath = movieDTO.getPosterPath();
        mAdult = movieDTO.getAdult();
        mBackdropPath = movieDTO.getBackdropPath();
        mGenreIds = movieDTO.getGenreIds();
        mId = movieDTO.getId();
        mOriginalLanguage = movieDTO.getOriginalLanguage();
        mReleaseDate = movieDTO.getReleaseDate();
        mPopularity = movieDTO.getPopularity();
        mTitle = movieDTO.getTitle();
        mVideo = movieDTO.getVideo();
        mVoteAverage = movieDTO.getVoteAverage();
        mVoteCount = movieDTO.getVoteCount();
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

    public Boolean getAdult() {
        return mAdult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public Integer getId() {
        return mId;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public Float getPopularity() {
        return mPopularity;
    }

    public String getTitle() {
        return mTitle;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public Float getVoteAverage() {
        return mVoteAverage;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOverview);
        dest.writeString(this.mPosterPath);
        dest.writeValue(this.mAdult);
        dest.writeString(this.mBackdropPath);
        dest.writeList(this.mGenreIds);
        dest.writeValue(this.mId);
        dest.writeString(this.mOriginalLanguage);
        dest.writeString(this.mReleaseDate);
        dest.writeValue(this.mPopularity);
        dest.writeString(this.mTitle);
        dest.writeValue(this.mVideo);
        dest.writeValue(this.mVoteAverage);
        dest.writeValue(this.mVoteCount);
    }

    protected Movie(Parcel in) {
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPosterPath = in.readString();
        this.mAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mBackdropPath = in.readString();
        this.mGenreIds = new ArrayList<Integer>();
        in.readList(this.mGenreIds, Integer.class.getClassLoader());
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mOriginalLanguage = in.readString();
        this.mReleaseDate = in.readString();
        this.mPopularity = (Float) in.readValue(Float.class.getClassLoader());
        this.mTitle = in.readString();
        this.mVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mVoteAverage = (Float) in.readValue(Float.class.getClassLoader());
        this.mVoteCount = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
