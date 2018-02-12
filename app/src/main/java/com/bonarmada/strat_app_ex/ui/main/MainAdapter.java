package com.bonarmada.strat_app_ex.ui.main;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonarmada.strat_app_ex.Constants;
import com.bonarmada.strat_app_ex.R;
import com.bonarmada.strat_app_ex.data.vo.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonbonme on 1/31/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public interface AdapterListener {
        void onItemClick(int position, int id);
    }

    private static Context context;
    private List<Movie> movies;
    private AdapterListener listener;


    public MainAdapter(Context context, List<Movie> movies, AdapterListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    public void refresh(List<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Movie mov = movies.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, mov.getId());
            }
        });

        Picasso.with(context).load(Constants.IMAGE_PATH + mov.getSlug() + Constants.COVER).fit().into(holder.ivCover);
        holder.tvName.setText(mov.getTitle());
        holder.tvGenre.setText(TextUtils.join(" | ", mov.getGenres()));
        holder.tvRating.setText("Rating: " + mov.getRating().toString());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Bind ui objects here
        @BindView(R.id.ivCover)
        AppCompatImageView ivCover;
        @BindView(R.id.tvName)
        AppCompatTextView tvName;
        @BindView(R.id.tvGenre)
        AppCompatTextView tvGenre;
        @BindView(R.id.tvRating)
        AppCompatTextView tvRating;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }
}