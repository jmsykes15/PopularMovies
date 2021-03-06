package org.lineware.popularmovies.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.data.MovieContract;

/**
 * Created by jmsykes15 on 5/27/16.
 */
public class MoviesCursorAdapter extends CursorRecyclerViewAdapter<MoviesCursorAdapter.ViewHolder>{
    private final Context mContext;

    public MoviesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        viewHolder.titleView.setText(cursor.getString(MovieContract.COLUMN_TITLE));
        viewHolder.posterView.setAdjustViewBounds(true);
        Picasso.with(mContext).load(cursor.getString(MovieContract.COLUMN_POSTER)).fit().placeholder(R.drawable.poster_placeholder).into(viewHolder.posterView);
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
