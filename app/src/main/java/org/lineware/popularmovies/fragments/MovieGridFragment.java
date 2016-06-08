package org.lineware.popularmovies.fragments;

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
import android.widget.ListView;

import org.lineware.popularmovies.AutoFitRecyclerView;
import org.lineware.popularmovies.R;
import org.lineware.popularmovies.adapters.MoviesCursorAdapter;
import org.lineware.popularmovies.helper.FetchMovieTask;
import org.lineware.popularmovies.helper.MovieContract;

public class MovieGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int MOVIE_LOADER = 0;

    public static final String MOVIE_DATA = "movieData";
    private AutoFitRecyclerView mRecyclerView;
    private MoviesCursorAdapter mAdapter;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";


    /*
    * TODO: Delete this method
    * */
    private void updateMovies(){
        FetchMovieTask fetchMovieTask = new FetchMovieTask(getContext());
        fetchMovieTask.execute();
    }

    public MovieGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        CursorLoader cursorLoader = new CursorLoader(getActivity(),
                movieUri,
                MovieContract.Movie_COLUMNS,
                null,
                null,
                null);

        return cursorLoader;
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
    public void onLoaderReset(Loader<Cursor> loader) {mAdapter.swapCursor(null);
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
