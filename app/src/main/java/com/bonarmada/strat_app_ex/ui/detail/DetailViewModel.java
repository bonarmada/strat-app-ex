package com.bonarmada.strat_app_ex.ui.detail;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.bonarmada.strat_app_ex.data.dao.RealmLiveData;
import com.bonarmada.strat_app_ex.data.repository.MovieRepository;
import com.bonarmada.strat_app_ex.data.vo.Movie;
import com.bonarmada.strat_app_ex.ui.main.MainPresenter;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class DetailViewModel extends ViewModel implements MainPresenter {

    private int movieId;
    public MutableLiveData<Movie> movie = new MutableLiveData<>();
    public MutableLiveData<Boolean> networkProcessIndicator = new MutableLiveData<>();

    MovieRepository repository;

    @Inject
    public DetailViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public void getMovieFromDb(){
        movie.setValue(repository.dao.get(movieId));
    }



    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    @Override
    public void onDispatchEvent() {
        networkProcessIndicator.setValue(true);
    }

    @Override
    public void onKillEvent() {
        networkProcessIndicator.setValue(false);
    }
}
