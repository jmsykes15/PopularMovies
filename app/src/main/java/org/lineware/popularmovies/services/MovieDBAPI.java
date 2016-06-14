package org.lineware.popularmovies.services;

import org.lineware.popularmovies.BuildConfig;
import org.lineware.popularmovies.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jmsykes15 on 6/13/16.
 */
public class MovieDBAPI {
    public interface PopularMoviesService{
        @GET("movie/{sort}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<Movies> listMovies(@Path("sort") String order);
    }
}
