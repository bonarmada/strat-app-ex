package com.bonarmada.strat_app_ex.di.module;

import android.content.Context;

import com.bonarmada.strat_app_ex.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context provideContext() {
        return context;
    }
}
