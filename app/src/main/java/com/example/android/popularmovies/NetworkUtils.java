package com.example.android.popularmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "api_key";

    /* Add here your own API key */
    private static final String MY_API_KEY = "";

    public static final String SORT_POPULAR = "popular";
    public static final String SORT_TOP_RATED = "top_rated";

    public static URL buildUrl(String sortPopular) {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sortPopular).appendQueryParameter(API_KEY, MY_API_KEY).build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = connection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            connection.disconnect();
        }
    }
}