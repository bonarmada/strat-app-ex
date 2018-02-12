package com.bonarmada.strat_app_ex.di.component;


import com.bonarmada.strat_app_ex.ui.detail.DetailActivity;
import com.bonarmada.strat_app_ex.ui.detail.DetailFragment;
import com.bonarmada.strat_app_ex.ui.main.MainActivity;
import com.bonarmada.strat_app_ex.di.module.DaoModule;
import com.bonarmada.strat_app_ex.di.module.RemoteModule;
import com.bonarmada.strat_app_ex.di.module.ViewModelModule;
import com.bonarmada.strat_app_ex.di.scope.AppScope;
import com.bonarmada.strat_app_ex.ui.main.MainFragment;

import dagger.Component;

/**
 * Created by Vaughn on 8/18/17.
 */

@AppScope
@Component(modules = {RemoteModule.class, DaoModule.class, ViewModelModule.class})

public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MainFragment activity);

    void inject(DetailActivity activity);
    void inject(DetailFragment activity);
}
