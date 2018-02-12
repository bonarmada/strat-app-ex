package com.bonarmada.strat_app_ex.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bonarmada.strat_app_ex.App;
import com.bonarmada.strat_app_ex.R;
import com.bonarmada.strat_app_ex.data.vo.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MainAdapter.AdapterListener, MainPresenter {

    public interface MainFragmentListener {
        void onItemClick(int id);
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainAdapter adapter;
    private MainViewModel viewModel;
    private MainFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (MainFragmentListener) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        // Observers
        viewModel.networkProcessIndicator.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean!=null){
                    if (aBoolean)
                        refreshLayout.setRefreshing(true);
                    else
                        refreshLayout.setRefreshing(false);
                }
            }
        });

        viewModel.movies.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                onKillEvent();
                adapter.refresh(movies);
            }
        });

        // Initial load of data.
        //TODO: block call when no internet access
        onRefresh();
    }


    private void setupRecyclerView() {
        // Refresh Layout
        refreshLayout.setOnRefreshListener(this);

        // Recycler
        adapter = new MainAdapter(getActivity(), null, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onRefresh() {
        onDispatchEvent();
        viewModel.fetchDataFromNetwork();
    }

    @Override
    public void onItemClick(int position, int id) {
        listener.onItemClick(id);
    }


    @Override
    public void onDispatchEvent() {
        viewModel.networkProcessIndicator.setValue(true);
    }

    @Override
    public void onKillEvent() {
        viewModel.networkProcessIndicator.setValue(false);
    }
}
