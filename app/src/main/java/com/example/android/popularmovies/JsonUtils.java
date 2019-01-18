package com.example.android.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static final String MOVIE_RESULTS = "results";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String SYNOPSIS = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String AVERAGE_VOTE = "vote_average";
    private static final String POSTER_PATH = "poster_path";
    private static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w185";

    public static ArrayList<Movies> estractMoviesFromJson(String json) {

        ArrayList<Movies> moviesList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray movieArray = jsonObject.getJSONArray(MOVIE_RESULTS);
            for (int i = 0; i < movieArray.length(); i++) {
                String title;
                String synopsis;
                String releaseDate;
                String averageVote;
                String image;
                JSONObject movie = movieArray.getJSONObject(i);
                title = movie.getString(ORIGINAL_TITLE);
                synopsis = movie.getString(SYNOPSIS);
                releaseDate = movie.getString(RELEASE_DATE);
                averageVote = movie.getString(AVERAGE_VOTE);
                image = IMAGE_PATH + movie.getString(POSTER_PATH);
                moviesList.add(new Movies(title, synopsis, releaseDate, averageVote, image));
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }
        return moviesList;
    }
}