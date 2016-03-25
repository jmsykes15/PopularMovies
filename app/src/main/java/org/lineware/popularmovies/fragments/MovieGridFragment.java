package org.lineware.popularmovies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lineware.popularmovies.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGridFragment extends Fragment {
    public MovieGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(
                R.layout.fragment_movie_grid, container, false);

        return rootview;
    }
}
