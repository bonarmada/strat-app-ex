package com.bonarmada.strat_app_ex.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.bonarmada.strat_app_ex.di.ViewModelKey;
import com.bonarmada.strat_app_ex.di.scope.AppScope;
import com.bonarmada.strat_app_ex.ui.detail.DetailViewModel;
import com.bonarmada.strat_app_ex.ui.main.MainViewModel;
import com.bonarmada.strat_app_ex.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module()
public abstract class ViewModelModule {

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindDetailViewModel(DetailViewModel detailViewModel);


    // Factory
    @AppScope
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
