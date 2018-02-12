package com.bonarmada.strat_app_ex.di.module;


import com.bonarmada.strat_app_ex.data.api.MovieRemote;
import com.bonarmada.strat_app_ex.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module(includes = NetworkModule.class)
public class RemoteModule {

    @AppScope
    @Provides
    MovieRemote provideMoviesRemote(Retrofit retrofit){
        return retrofit.create(MovieRemote.class);
    }
}
