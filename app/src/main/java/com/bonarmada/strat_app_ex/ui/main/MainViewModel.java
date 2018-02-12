package com.bonarmada.strat_app_ex.ui.main;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.bonarmada.strat_app_ex.data.dao.RealmLiveData;
import com.bonarmada.strat_app_ex.data.repository.MovieRepository;
import com.bonarmada.strat_app_ex.data.vo.Movie;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class MainViewModel extends ViewModel implements MainPresenter {
    private static final String TAG = MainViewModel.class.getSimpleName();

    public LiveData<List<Movie>> movies;
    public MutableLiveData<Boolean> networkProcessIndicator = new MutableLiveData<>();

    MovieRepository repository;

    @Inject
    public MainViewModel(MovieRepository repository) {
        this.repository = repository;

        subscribeToWeatherData();
    }


    private void subscribeToWeatherData() {

        RealmLiveData<Movie> liveMovies = this.repository.dao.getAsLiveData();
        movies = Transformations.map(liveMovies, new Function<RealmResults<Movie>, List<Movie>>() {
            @Override
            public List<Movie> apply(RealmResults<Movie> input) {
                Log.e(TAG, input.toString());
                MainViewModel.this.onKillEvent();
                return repository.dao.get();
            }
        });

    }

    public void fetchDataFromNetwork() {
        this.onDispatchEvent();
        repository.getMoviesByPage(1);
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
