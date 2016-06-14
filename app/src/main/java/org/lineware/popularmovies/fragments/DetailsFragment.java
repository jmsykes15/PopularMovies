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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.data.MovieContract;


public class DetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

//    public static Movie movie;
    private ImageView mBanner;
    private TextView mPlot;
    private TextView mRelease;
    private RatingBar mRating;

    public static final String DETAIL_URI = "URI";

    private static final int DETAIL_LOADER = 0;
    private Uri mUri;


    public DetailsFragment() {setHasOptionsMenu(false);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();

        if (arguments != null) {
            mUri = arguments.getParcelable(DETAIL_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        mBanner = (ImageView) rootView.findViewById(R.id.movie_banner);
        mPlot = (TextView) rootView.findViewById(R.id.movie_plot);
        mRelease = (TextView) rootView.findViewById(R.id.movie_release);
        mRating = (RatingBar) rootView.findViewById(R.id.movie_rating);



        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(Uri dateUri);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if ( null != mUri ) {
            // Now create and return a CursorLoader that will take care of
            // creating a Cursor for the data being displayed.
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    MovieContract.Movie_COLUMNS,
                    null,
                    null,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null && data.moveToFirst()){



            getActivity().setTitle(data.getString(MovieContract.COLUMN_TITLE));
            mBanner.setImageURI(Uri.parse(data.getString(MovieContract.COLUMN_BANNER)));
            mPlot.setText(data.getString(MovieContract.COLUMN_PLOT));
            mRating.setRating(data.getLong(MovieContract.COLUMN_RATING));
            mRelease.setText(data.getString(MovieContract.COLUMN_RELEASE));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}