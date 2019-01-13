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


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_main);
        mErrorTextView = (TextView) findViewById(R.id.tv_error);

        URL url = NetworkUtils.buildUrl(NetworkUtils.SORT_POPULAR);
        new fetchMovies().execute(url);

        GridLayoutManager gridManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mPosterAdapter);
    }
@Override
public void onClick(int posterPosition) {

        }

public class fetchMovies extends AsyncTask<URL,Void,String> {

    @Override
    protected String doInBackground(URL... urls) {
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
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        PosterAdapter posterAdapter = new PosterAdapter(moviesArrayList,posterClickListener);
        mRecyclerView.setAdapter(posterAdapter);
    }
}

}
