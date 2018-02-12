package com.bonarmada.strat_app_ex.data.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonbonme on 2/13/2018.
 */

public class Movie extends RealmObject{

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("rating")
    @Expose
    private Float rating;
    @SerializedName("genres")
    @Expose
    private RealmList<String> genres = null;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title_long")
    @Expose
    private String titleLong;
    @SerializedName("imdb_code")
    @Expose
    private String imdbCode;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("mpa_rating")
    @Expose
    private String mpaRating;

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public RealmList<String> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<String> genres) {
        this.genres = genres;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public void setTitleLong(String titleLong) {
        this.titleLong = titleLong;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMpaRating() {
        return mpaRating;
    }

    public void setMpaRating(String mpaRating) {
        this.mpaRating = mpaRating;
    }

}