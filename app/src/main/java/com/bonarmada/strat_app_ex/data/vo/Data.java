package com.bonarmada.strat_app_ex.data.vo;

import com.bonarmada.strat_app_ex.data.vo.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bonbonme on 2/13/2018.
 */
public class Data {

    @SerializedName("movies")
    @Expose
    private List<Movie> movies = null;
    @SerializedName("page_number")
    @Expose
    private Integer pageNumber;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

}