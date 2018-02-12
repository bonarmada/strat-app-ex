package com.bonarmada.strat_app_ex.data.api;

import com.bonarmada.strat_app_ex.data.vo.ApiResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bonbonme on 1/30/2018.
 */

public interface MovieRemote {
    @GET("list_movies_page{pageId}.json")
    Single<Response<ApiResponse>> getMovies(
            @Path("pageId") int pageId);
}
