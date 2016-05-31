package org.lineware.popularmovies.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.pojos.Movie;


public class DetailsFragment extends Fragment {

    public static Movie movie;
    private ImageView mBanner;
    private TextView mTitle;
    private TextView mPlot;
    private TextView mRelease;
    private RatingBar mRating;
    private ImageView mPoster;

    private static final int DETAIL_LOADER = 0;
    private Uri mUri;
    public static final String DETAIL_URI = "URI";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null && savedInstanceState.containsKey(DETAIL_URI)){
            movie = savedInstanceState.getParcelable(DETAIL_URI);
        }
    }
    public DetailsFragment() {setHasOptionsMenu(false);}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if(arguments != null) mUri = arguments.getParcelable(DetailsFragment.DETAIL_URI);

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        mBanner = (ImageView) rootView.findViewById(R.id.movie_banner);
        mTitle = (TextView) rootView.findViewById(R.id.title_view);
        mPlot = (TextView) rootView.findViewById(R.id.movie_plot);
        mRelease = (TextView) rootView.findViewById(R.id.movie_release);
        mRating = (RatingBar) rootView.findViewById(R.id.movie_rating);
        mPoster = (ImageView) rootView.findViewById(R.id.movie_poster);

        return rootView;
    }
}