package org.lineware.popularmovies.pojos;

import android.database.Cursor;

import org.lineware.popularmovies.fragments.MovieGridFragment;

/**
 * Created by jmsykes15 on 3/14/16.
 */
public class Movie {

    private String mBanner;
    private String mTitle;
    private String mPlot;
    private String mRelease;
    private double mRating;
    private String mLength;
    private String posterURI;

    public Movie(Cursor cursor){

//        static final int COLUMN_MOVIE_ID = 1;
//        static final int COLUMN_TITLE = 2;
//        static final int COLUMN_DATE = 3;
//        static final int COLUMN_RATING = 4;
//        static final int COLUMN_POSTER = 5;
//        static final int COLUMN_BANNER = 6;
//        static final int COLUMN_PLOT = 7;

        this.mTitle = cursor.getString(MovieGridFragment.COLUMN_TITLE);
        this.mPlot = cursor.getString(MovieGridFragment.COLUMN_PLOT);
        this.mRelease = cursor.getString(MovieGridFragment.COLUMN_RELEASE);
        this.mRating = cursor.getDouble(MovieGridFragment.COLUMN_RATING);
        this.mLength = cursor.getString(MovieGridFragment.COLUMN_LENGTH);
        this.posterURI = cursor.getString(MovieGridFragment.COLUMN_POSTER);
        this.mBanner = cursor.getString(MovieGridFragment.COLUMN_BANNER);
    }

    public Movie(
//            ImageView mImageView,
            String Title,
            String Plot,
            String Release,
            double Rating,
            String Length,
            String posterURI,
            String banner) {
        this.mTitle = Title;
        this.mPlot = Plot;
        this.mRelease = Release;
        this.mRating = Rating;
        this.mLength = Length;
        this.posterURI = posterURI;
        this.mBanner = banner;
    }

    public String getmBanner() {return mBanner;}

    public String getmTitle() {
        return mTitle;
    }

    public String getmPlot() {
        return mPlot;
    }

    public String getmRelease() {
        return mRelease;
    }

    public double getmRating() {
        return mRating;
    }

    public String getmLength() {
        return mLength;
    }

    public static Movie fromCursor(Cursor cursor) {
        return new Movie(cursor);
    }

    public String getPosterURI() {
        return posterURI;
    }
}
