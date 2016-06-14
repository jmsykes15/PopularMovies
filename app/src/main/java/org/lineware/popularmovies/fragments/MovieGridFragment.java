package org.lineware.popularmovies.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.adapters.MoviesCursorAdapter;
import org.lineware.popularmovies.data.MovieContract;
import org.lineware.popularmovies.model.Movies;
import org.lineware.popularmovies.pojos.Result;
import org.lineware.popularmovies.services.FetchMovieTask;
import org.lineware.popularmovies.services.MovieDBAPI;
import org.lineware.popularmovies.views.AutoFitRecyclerView;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private final String TAG = FetchMovieTask.class.getSimpleName();

    public static final int MOVIE_LOADER = 0;

//    public static final String MOVIE_DATA = "movieData";
    private AutoFitRecyclerView mRecyclerView;
    private MoviesCursorAdapter mAdapter;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private MovieDBAPI.PopularMoviesService movieDBAPI;
    private Call<Movies> resultsCall;
    private Movies movies;
    private List<Result> movieItems;

    /*
    * TODO: Delete this method
    */
//    private void updateMovies(){
//        FetchMovieTask fetchMovieTask = new FetchMovieTask(getContext());
//        fetchMovieTask.execute();
//    }

    public MovieGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieDBAPI = retrofit.create(MovieDBAPI.PopularMoviesService.class);

//        getMovies("popular");

        setHasOptionsMenu(true);
    }

    public interface Callback {
        void onItemSelected(Uri movieUri);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootview = inflater.inflate(
                R.layout.fragment_movie_grid, container, false);

        mRecyclerView = (AutoFitRecyclerView) rootview.findViewById(R.id.rec_movie_grid);
        mAdapter = new MoviesCursorAdapter(getActivity(), null);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnTouchListener(new MyOnTouchListener(savedInstanceState));

        return rootview;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {return true;}

        if(id == R.id.action_refresh){
            getMovies("popular");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(MOVIE_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri movieUri = MovieContract.MovieEntry.CONTENT_URI;

        return new CursorLoader(getActivity(),
                movieUri,
                MovieContract.Movie_COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {mAdapter.swapCursor(data);}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(mPosition != ListView.INVALID_POSITION){
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {mAdapter.swapCursor(null);}

    public void getMovies(String sort){
        resultsCall = movieDBAPI.listMovies(sort);

        resultsCall.enqueue(new retrofit2.Callback<Movies>(){

            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                movies = response.body();
                movieItems = movies.getResults();

                if(movieItems != null){
                    mAdapter.swapCursor(null);

                    Vector<ContentValues> cVVector =
                            new Vector<>(movieItems.size());

                    for(Result movie: movieItems){
                        ContentValues movieValues = new ContentValues();

                        movieValues.put(MovieContract.MovieEntry.COLUMN_TITLE,movie.getOriginalTitle());
                        movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, movie.getId());
                        movieValues.put(MovieContract.MovieEntry.COLUMN_RELEASE, movie.getReleaseDate());
                        movieValues.put(MovieContract.MovieEntry.COLUMN_POSTER, movie.getPosterPath());
                        movieValues.put(MovieContract.MovieEntry.COLUMN_BANNER, movie.getBackdropPath());
                        movieValues.put(MovieContract.MovieEntry.COLUMN_RATING, movie.getVoteAverage());
                        movieValues.put(MovieContract.MovieEntry.COLUMN_PLOT, movie.getOverview());

                        cVVector.add(movieValues);
                    }
                    int inserted = 0;

                    if(cVVector.size() > 0){
                        ContentValues[] cvArray = new ContentValues[cVVector.size()];
                        cVVector.toArray(cvArray);
                        getContext().getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI, cvArray);
                    }

                    Log.d(TAG, "getMovieDataFromJson: FetchMovieTask Complete. " + inserted + " Inserted");
                }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    private class MyOnTouchListener implements View.OnTouchListener {

        private static final int MAX_CLICK_DURATION = 1000;
        private static final int MAX_CLICK_DISTANCE = 15;
        private final Bundle savedInstanceState;

        private long pressStartTime;
        private float pressedX;
        private float pressedY;

        public MyOnTouchListener(Bundle savedInstanceState) {
            this.savedInstanceState = savedInstanceState;
        }

        float distance(float x1, float y1, float x2, float y2) {
            float dx = x1 - x2;
            float dy = y1 - y2;
            float distanceInPx = (float) Math.sqrt(dx * dx + dy * dy);
            return pxToDp(distanceInPx);
        }

        float pxToDp(float px) {
          return px / getResources().getDisplayMetrics().density;
      }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            View view = mRecyclerView.findChildViewUnder(event.getX(), event.getY());
            int position = mRecyclerView.getChildAdapterPosition(view);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    pressStartTime = System.currentTimeMillis();
                    pressedX = event.getX();
                    pressedY = event.getY();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    long pressDuration = System.currentTimeMillis() - pressStartTime;
                    if (pressDuration < MAX_CLICK_DURATION && distance(pressedX, pressedY, event.getX(), event.getY()) < MAX_CLICK_DISTANCE) {
                        ((Callback) getActivity())
                        .onItemSelected(MovieContract.MovieEntry.buildMovieUri(position+1));
                    }
                }
            }

            mPosition = position;

            if(savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)){
                mPosition = savedInstanceState.getInt(SELECTED_KEY);
            }

            return true;
        }
    }
}
