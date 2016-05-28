package org.lineware.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.lineware.popularmovies.pojos.Movie;

/**
 * Created by jmsykes15 on 5/27/16.
 */
public class MoviesCursorAdapter extends CursorRecyclerViewAdapter<MoviesCursorAdapter.ViewHolder>{
    public MoviesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        Movie movie = Movie.fromCursor(cursor);
        viewHolder.titleView.setText(movie.getmTitle());
        viewHolder.posterView.setImageURI(Uri.parse(movie.getPosterURI()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterView;
        TextView titleView;
        public ViewHolder(View itemView) {
            super(itemView);
            posterView = (ImageView) itemView.findViewById(R.id.poster_view);
            titleView = (TextView) itemView.findViewById(R.id.title_view);
        }
    }
}
