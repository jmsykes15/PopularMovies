package org.lineware.popularmovies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.lineware.popularmovies.AutoFitRecyclerView;
import org.lineware.popularmovies.R;
import org.lineware.popularmovies.activities.DetailsActivity;
import org.lineware.popularmovies.adapters.MovieDBAdapter;
import org.lineware.popularmovies.pojos.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGridFragment extends Fragment {

//    private static final String MOVIE_LIST = {
//
//    }

    public static final int COLUMN_MOVIE_ID = 1;
    public static final int COLUMN_TITLE = 2;
    public static final int COLUMN_RELEASE = 3;
    public static final int COLUMN_RATING = 4;
    public static final int COLUMN_POSTER = 5;
    public static final int COLUMN_BANNER = 6;
    public static final int COLUMN_PLOT = 7;
    public static final int COLUMN_LENGTH = 8;
    private AutoFitRecyclerView mRecyclerView;
    private MovieDBAdapter mAdapter;

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
        View rootview = inflater.inflate(
                R.layout.fragment_movie_grid, container, false);

        mRecyclerView = (AutoFitRecyclerView) rootview.findViewById(R.id.rec_movie_grid);
        mAdapter = new MovieDBAdapter(testDataGeneratator());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
//                intent.putExtra(MOVIE_DATA, )
                startActivity(intent);
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

        return super.onOptionsItemSelected(item);
    }


    private List<Movie> testDataGeneratator() {
        ArrayList<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Title 1", " some random plot text", "12/25/20015", 5.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 2", " some random plot text", "12/25/20015", 1.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 3", " some random plot text", "12/25/20015", 2.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 4", " some random plot text", "12/25/20015", 3.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 5", " some random plot text", "12/25/20015", 4.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 6", " some random plot text", "12/25/20015", 5.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 7", " some random plot text", "12/25/20015", 6.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 8", " some random plot text", "12/25/20015", 7.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 9", " some random plot text", "12/25/20015", 8.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 10", " some random plot text", "12/25/20015", 9.5, "5 minutes", "asdfasdf", "Banner"));
        movies.add(new Movie("Title 11", " some random plot text", "12/25/20015", 0.5, "5 minutes", "asdfasdf", "Banner"));

        return movies;
    }
}
