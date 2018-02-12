package com.bonarmada.strat_app_ex.data.repository;

import android.util.Log;

import com.bonarmada.strat_app_ex.data.api.MovieRemote;
import com.bonarmada.strat_app_ex.data.dao.MovieDao;
import com.bonarmada.strat_app_ex.data.vo.ApiResponse;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by bonbonme on 2/13/2018.
 */

public class MovieRepository {
    public static final String TAG = MovieRepository.class.getSimpleName();
    public static MovieRemote remote;
    public static MovieDao dao;

    @Inject
    public MovieRepository(MovieRemote remote, MovieDao dao) {
        this.remote = remote;
        this.dao = dao;
    }

    public static void getMoviesByPage(int pageId) {
        remote.getMovies(pageId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<ApiResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(Response<ApiResponse> apiResponseResponse) {
                        Log.e(TAG, apiResponseResponse.code() + "");
                        if (apiResponseResponse.code() == 200) {
                            if (apiResponseResponse.body().getData().getMovies() != null) {
                                dao.save(apiResponseResponse.body().getData().getMovies());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                    }
                });
    }
}
