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

import com.squareup.picasso.Picasso;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.data.MovieContract;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
//    private ImageView mBanner;
//    private TextView mPlot;
//    private TextView mRelease;
//    private RatingBar mRating;

    public static final String DETAIL_URI = "URI";

    private static final int DETAIL_LOADER = 0;
    private Uri mUri;

    @Bind(R.id.movie_banner) ImageView mBanner;
    @Bind(R.id.movie_plot) TextView mPlot;
    @Bind(R.id.movie_release) TextView mRelease;
    @Bind(R.id.movie_rating) RatingBar mRating;

    public DetailsFragment() {setHasOptionsMenu(false);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();

        if (arguments != null) {
            mUri = arguments.getParcelable(DETAIL_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, rootView);

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
            mBanner.setAdjustViewBounds(true);

            mBanner.setPadding(0, 0, 0, 0);
            Picasso.with(getContext()).load(data.getString(MovieContract.COLUMN_BANNER)).fit().placeholder(R.drawable.backdrop_placeholder).into(mBanner);

            mPlot.setText(data.getString(MovieContract.COLUMN_PLOT));
            mRating.setRating(data.getLong(MovieContract.COLUMN_RATING));
            mRelease.setText(data.getString(MovieContract.COLUMN_RELEASE));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}