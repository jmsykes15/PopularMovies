package org.lineware.popularmovies.pojos;

/**
 * Created by jmsykes15 on 3/14/16.
 */
public class Movie {
//    private ImageView mImageView;
    private String mTitle;
    private String mPlot;
    private String mRelease;
    private double mRating;
    private String mLength;

    public Movie(
//            ImageView mImageView,
            String mTitle,
            String mPlot,
            String mRelease,
            double mRating,
            String mLength) {
//        this.mImageView = mImageView;
        this.mTitle = mTitle;
        this.mPlot = mPlot;
        this.mRelease = mRelease;
        this.mRating = mRating;
        this.mLength = mLength;
    }

//    public ImageView getmImageView() {
//        return mImageView;
//    }

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
}
