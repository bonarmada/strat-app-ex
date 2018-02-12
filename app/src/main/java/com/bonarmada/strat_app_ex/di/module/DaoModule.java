package com.bonarmada.strat_app_ex.di.module;


import com.bonarmada.strat_app_ex.data.dao.MovieDao;
import com.bonarmada.strat_app_ex.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module
public class DaoModule {

    @AppScope
    @Provides
    MovieDao provideMovieDao(){
        return new MovieDao();
    }
}
