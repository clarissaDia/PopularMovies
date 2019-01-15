package com.example.android.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PosterAdapter.PosterClickListener{
private Context context;
private RecyclerView mRecyclerView;
private PosterAdapter.PosterClickListener posterClickListener;
private ProgressBar mProgressbar;
private TextView mErrorTextView;
private PosterAdapter mPosterAdapter;
private ArrayList<Movies> moviesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesArrayList = new ArrayList<>();

        URL url = NetworkUtils.buildUrl(NetworkUtils.SORT_POPULAR);
        new fetchMovies().execute(url);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_main);
        mPosterAdapter = new PosterAdapter(moviesArrayList, posterClickListener);
        mErrorTextView = (TextView) findViewById(R.id.tv_error);


        GridLayoutManager gridManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setHasFixedSize(true);
        posterClickListener = this;
    }
@Override
public void onClick(int posterPosition) {

        }

public class fetchMovies extends AsyncTask<URL,Void,ArrayList<Movies>> {

    @Override
    protected ArrayList<Movies> doInBackground(URL... urls) {
        URL url = urls[0];
        String response = null;
        try {
            response = NetworkUtils.getResponseFromUrl(url);
        }catch (IOException e ){
            e.printStackTrace();
        }
        if (response != null){
            moviesArrayList = JsonUtils.estractMoviesFromJson(response);
        }
        return moviesArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Movies> movies) {
        super.onPostExecute(movies);
       PosterAdapter posterAdapter = new PosterAdapter(movies, posterClickListener);
       mRecyclerView.setAdapter(posterAdapter);
    }
}

}
