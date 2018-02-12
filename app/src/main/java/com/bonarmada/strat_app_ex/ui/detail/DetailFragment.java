package com.bonarmada.strat_app_ex.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bonarmada.strat_app_ex.App;
import com.bonarmada.strat_app_ex.Constants;
import com.bonarmada.strat_app_ex.R;
import com.bonarmada.strat_app_ex.data.vo.Movie;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class DetailFragment extends Fragment {

    @BindView(R.id.empty_state)
    LinearLayout emptyStateContainer;

    @BindView(R.id.content_frame)
    LinearLayout contentFrame;

    @BindView(R.id.top_container)
    RelativeLayout topContainer;

    @BindView(R.id.ivBackdrop)
    AppCompatImageView ivBackdrop;

    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;

    @BindView(R.id.tvGenre)
    AppCompatTextView tvGenre;

    @BindView(R.id.tvRuntime)
    AppCompatTextView tvRuntime;

    @BindView(R.id.tvLanguage)
    AppCompatTextView tvLanguage;

    @BindView(R.id.tvYear)
    AppCompatTextView tvYear;

    @BindView(R.id.tvRating)
    AppCompatTextView tvRating;

    @BindView(R.id.tvOverview)
    AppCompatTextView tvOverview;


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private static final String OWM_ICON_PATH = "http://openweathermap.org/img/w/";
    private int movieId;
    private Movie movie;
    private DetailViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            emptyStateContainer.setVisibility(View.VISIBLE);
            contentFrame.setVisibility(View.GONE);
        } else {
            movieId = bundle.getInt("movieId");
            subscribeToViewModel();
        }
    }

    private void subscribeToViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
        viewModel.setMovieId(movieId);

        viewModel.movie.observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie temp) {
                if (temp != null) {
                    movie = temp;
                    updateUi();
                }
            }
        });
        viewModel.getMovieFromDb();
    }

    private void updateUi() {
        if (movie == null)
            return;

        Picasso.with(getActivity()).load(Constants.IMAGE_PATH + movie.getSlug() + Constants.BACKDROP).fit().into(ivBackdrop);
        tvTitle.setText(movie.getTitleLong());
        tvGenre.setText(TextUtils.join(" | ", movie.getGenres()));
        tvRuntime.setText(movie.getRuntime() + " minutes");
        tvLanguage.setText(movie.getLanguage());
        tvYear.setText(movie.getYear().toString());
        tvRating.setText(movie.getRating().toString());
        tvOverview.setText(movie.getOverview());
    }

    public void onListItemSelected(int id) {
        movieId = id;
        contentFrame.setVisibility(View.VISIBLE);
        emptyStateContainer.setVisibility(View.GONE);

        subscribeToViewModel();
    }
}
