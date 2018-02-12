package com.bonarmada.strat_app_ex.ui.main;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bonarmada.strat_app_ex.App;
import com.bonarmada.strat_app_ex.R;
import com.bonarmada.strat_app_ex.data.repository.MovieRepository;
import com.bonarmada.strat_app_ex.ui.detail.DetailActivity;
import com.bonarmada.strat_app_ex.ui.detail.DetailFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(int id) {
        DetailFragment detailFragment= (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragment);

        // Check whether fragment exists within the activity.. aka multi pane mode.
        if (detailFragment != null){
            detailFragment.onListItemSelected(id);
            return;
        }

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movieId", id);
        startActivity(intent);
    }
}
