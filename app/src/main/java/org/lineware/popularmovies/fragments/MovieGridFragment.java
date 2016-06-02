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
import org.lineware.popularmovies.adapters.MoviesCursorAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGridFragment extends Fragment {

    public static final String MOVIE_DATA = "movieData";
    private AutoFitRecyclerView mRecyclerView;
    private MoviesCursorAdapter mAdapter;

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

                Intent intent = new Intent(getContext(), DetailsActivity.class);
                startActivity(intent);

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

        return super.onOptionsItemSelected(item);
    }


//    private Cursor testDataGeneratator() {
//        Cursor cursor = new
//    }
}
