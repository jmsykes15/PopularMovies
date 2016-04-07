package org.lineware.popularmovies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.activities.DetailsActivity;
import org.lineware.popularmovies.adapters.MovieDBAdapter;
import org.lineware.popularmovies.helper.AutoFitRecyclerView;
import org.lineware.popularmovies.helper.RecyclerViewOnItemTouchListener;
import org.lineware.popularmovies.pojos.Movie;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGridFragment extends Fragment {
    private AutoFitRecyclerView mRecyclerView;
    private MovieDBAdapter mAdapter;
    private ArrayList listOfData;
    public MovieGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(
                R.layout.fragment_movie_grid, container, false);

        mRecyclerView = (AutoFitRecyclerView) rootview.findViewById(R.id.rec_movie_grid);
        listOfData = testDataGeneratator();
        mAdapter = new MovieDBAdapter(listOfData);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerViewOnItemTouchListener(getActivity(), new RecyclerViewOnItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = mAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailsActivity.class)
                        .putExtra("moviedata", movie);
                startActivity(intent);

            }
        }));




        return rootview;
    }

    private ArrayList<Movie> testDataGeneratator() {
        ArrayList<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Title 1", " some random plot text", "12/25/20015", 5.5f, 125));
        movies.add(new Movie("Title 2", " some random plot text", "12/25/20015", 1.5f, 145));
        movies.add(new Movie("Title 3", " some random plot text", "12/25/20015", 2.5f, 55));
        movies.add(new Movie("Title 4", " some random plot text", "12/25/20015", 3.5f, 65));
        movies.add(new Movie("Title 5", " some random plot text", "12/25/20015", 4.5f, 175));
        movies.add(new Movie("Title 6", " some random plot text", "12/25/20015", 5.5f, 345));
        movies.add(new Movie("Title 7", " some random plot text", "12/25/20015", 6.5f, 235));
        movies.add(new Movie("Title 8", " some random plot text", "12/25/20015", 7.5f, 165));
        movies.add(new Movie("Title 9", " some random plot text", "12/25/20015", 8.5f, 127));
        movies.add(new Movie("Title 10", " some random plot text", "12/25/20015", 9.5f, 238));
        movies.add(new Movie("Title 11", " some random plot text", "12/25/20015", 0.5f, 342));

        return movies;
    }

}
