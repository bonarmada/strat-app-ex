package com.bonarmada.strat_app_ex.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bonarmada.strat_app_ex.R;

/**
 * Created by bonbonme on 1/31/2018.
 */

public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        // Create fragment instance
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, fragment, null)
                .commit();
    }

}
