package org.lineware.popularmovies.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.lineware.popularmovies.AutoFitRecyclerView;
import org.lineware.popularmovies.R;
import org.lineware.popularmovies.activities.DetailsActivity;
import org.lineware.popularmovies.adapters.MoviesCursorAdapter;
import org.lineware.popularmovies.helper.FetchMovieTask;
import org.lineware.popularmovies.helper.MovieContract;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int MOVIE_LOADER = 0;

    public static final String MOVIE_DATA = "movieData";
    private AutoFitRecyclerView mRecyclerView;
    private MoviesCursorAdapter mAdapter;

    /*
    * TODO: Delete this method
    * */
    private void updateMovies(){
        FetchMovieTask fetchMovieTask = new FetchMovieTask(getActivity());
        fetchMovieTask.execute();
    }

    public MovieGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(
                R.layout.fragment_movie_grid, container, false);

        mRecyclerView = (AutoFitRecyclerView) rootview.findViewById(R.id.rec_movie_grid);
        mAdapter = new MoviesCursorAdapter(getActivity(), null);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View view = mRecyclerView.findChildViewUnder(event.getX(), event.getY());
                int position = mRecyclerView.getChildAdapterPosition(view);

                Cursor cursor = (Cursor) mRecyclerView.getChildAt(position);

                if(cursor != null){
                    Intent intent = new Intent(getActivity(), DetailsActivity.class)
                            .setData(MovieContract.MovieEntry.CONTENT_URI);
                    startActivity(intent);
                }

//                Toast.makeText(getContext(), mAdapter.getMovie(position).getmTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return rootview;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_refresh){
            updateMovies();
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
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }


//    private Cursor testDataGeneratator() {
//        Cursor cursor = new
//    }
}
