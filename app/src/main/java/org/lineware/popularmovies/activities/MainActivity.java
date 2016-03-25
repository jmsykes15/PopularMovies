package org.lineware.popularmovies.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.adapters.MovieDBAdapter;
import org.lineware.popularmovies.pojos.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rec_movie_grid);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MovieDBAdapter(testDataGeneratator());
        mRecyclerView.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private List<Movie> testDataGeneratator() {
        ArrayList<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Title 1", " some random plot text", "12/25/20015", 5.5, "5 minutes"));
        movies.add(new Movie("Title 2", " some random plot text", "12/25/20015", 1.5, "5 minutes"));
        movies.add(new Movie("Title 3", " some random plot text", "12/25/20015", 2.5, "5 minutes"));
        movies.add(new Movie("Title 4", " some random plot text", "12/25/20015", 3.5, "5 minutes"));
        movies.add(new Movie("Title 5", " some random plot text", "12/25/20015", 4.5, "5 minutes"));
        movies.add(new Movie("Title 6", " some random plot text", "12/25/20015", 5.5, "5 minutes"));
        movies.add(new Movie("Title 7", " some random plot text", "12/25/20015", 6.5, "5 minutes"));
        movies.add(new Movie("Title 8", " some random plot text", "12/25/20015", 7.5, "5 minutes"));
        movies.add(new Movie("Title 9", " some random plot text", "12/25/20015", 8.5, "5 minutes"));
        movies.add(new Movie("Title 10", " some random plot text", "12/25/20015", 9.5, "5 minutes"));
        movies.add(new Movie("Title 11", " some random plot text", "12/25/20015", 0.5, "5 minutes"));

        return movies;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
