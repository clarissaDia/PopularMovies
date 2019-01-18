package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImage = (ImageView) findViewById(R.id.iv_detail_image);
        TextView title = (TextView) findViewById(R.id.tv_title);
        TextView synopsis = (TextView) findViewById(R.id.tv_synopsis);
        TextView releaseDate = (TextView) findViewById(R.id.tv_release_date);
        TextView voteAverage = (TextView) findViewById(R.id.tv_vote_average);

        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            Movies movies = intent.getParcelableExtra(Intent.EXTRA_TEXT);
            Picasso.get().load(movies.getImage()).into(detailImage);
            title.setText(movies.getTitle());
            synopsis.setText(movies.getOverView());
            releaseDate.setText(movies.getReleaseDate());
            voteAverage.setText(movies.getVote());
        }
    }
}