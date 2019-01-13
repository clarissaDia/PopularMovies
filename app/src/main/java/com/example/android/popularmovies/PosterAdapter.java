package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {


    Context context;
    private ArrayList<Movies> mMoviesList;

    final private PosterClickListener mPosterClick;

    public interface PosterClickListener {
        void onClick (int posterPosition);
    }

    public PosterAdapter (ArrayList<Movies> movies,PosterClickListener clickListener){
        mMoviesList = movies;
        mPosterClick = clickListener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        boolean attachToParentImmediately = false;
        View view = inflater.inflate(R.layout.grid_movie_item, viewGroup,attachToParentImmediately);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindView(position);

    }


    @Override
    public int getItemCount() {
        if (mMoviesList == null) return 0;
        return mMoviesList.size();

    }

    public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imageViewHolder;

        PosterViewHolder (View itemView){
            super(itemView);

            imageViewHolder = itemView.findViewById(R.id.iv_movie_poster);
            imageViewHolder.setOnClickListener(this);
        }

        void bindView (int index){


            Picasso.with(context)
                    .load(mMoviesList.get(index).getImage())
                    .fit()
                    .into(imageViewHolder);

        }

        @Override
        public void onClick(View poster) {
            int posterPosition = getAdapterPosition();
            mPosterClick.onClick(posterPosition);

        }
    }
}

