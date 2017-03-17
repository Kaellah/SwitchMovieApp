package com.kaellah.switchmovieapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */

public class MoviesListAnswerDTO {
    @SerializedName("dates")
    @Expose
    private DatesDTO dates;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<MovieDTO> results = null;

    public DatesDTO getDates() {
        return dates;
    }

    public void setDates(DatesDTO dates) {
        this.dates = dates;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MovieDTO> getResults() {
        return results;
    }

    public void setResults(List<MovieDTO> results) {
        this.results = results;
    }
}
