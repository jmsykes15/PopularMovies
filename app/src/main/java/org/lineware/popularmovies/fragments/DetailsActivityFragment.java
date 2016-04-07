package org.lineware.popularmovies.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.pojos.Movie;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {
    private Movie mMovie;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        Intent intent = getActivity().getIntent();
        if(intent != null && intent.hasExtra("moviedata")){
            mMovie = intent.getParcelableExtra("moviedata");
            ((TextView) rootView.findViewById(R.id.details_duration))
                    .setText(""+mMovie.getmLength());
            ((TextView) rootView.findViewById(R.id.details_plot))
                    .setText(mMovie.getmPlot());
            ((TextView) rootView.findViewById(R.id.details_title))
                    .setText(mMovie.getmTitle());
            ((TextView) rootView.findViewById(R.id.details_releasedate))
                    .setText(mMovie.getmRelease());
            ((RatingBar) rootView.findViewById(R.id.details_rating))
                    .setRating(mMovie.getmRating());
        }

        return rootView;
    }
}
