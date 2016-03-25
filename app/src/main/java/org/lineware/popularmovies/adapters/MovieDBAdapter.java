package org.lineware.popularmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lineware.popularmovies.R;
import org.lineware.popularmovies.pojos.Movie;

import java.util.List;

/**
 * Created by jmsykes15 on 3/14/16.
 */
public class MovieDBAdapter extends RecyclerView.Adapter<MovieDBAdapter.ViewHolder> {
    private List<Movie> mDataSet;

    public MovieDBAdapter(List<Movie> dataset){
        mDataSet = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
//        TextView mPlot;
//        TextView mRelease;
//        TextView mRating;
//        TextView mLength;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title_view);
//            mPlot;
//            mRelease;
//            mRating;
//            String mLength;

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_layout,parent,false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mDataSet.get(position).getmTitle());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
