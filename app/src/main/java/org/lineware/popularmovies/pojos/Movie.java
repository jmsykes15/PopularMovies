package org.lineware.popularmovies.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jmsykes15 on 3/14/16.
 */
public class Movie implements Parcelable{
    private String mTitle;
    private String mPlot;
    private String mRelease;
    private float mRating;
    private int mLength;

    public Movie(
            String mTitle,
            String mPlot,
            String mRelease,
            float mRating,
            int mLength) {
        this.mTitle = mTitle;
        this.mPlot = mPlot;
        this.mRelease = mRelease;
        this.mRating = mRating;
        this.mLength = mLength;
    }

    protected Movie(Parcel in) {
        mTitle = in.readString();
        mPlot = in.readString();
        mRelease = in.readString();
        mRating = in.readFloat();
        mLength = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getmTitle() {
        return mTitle;
    }

    public String getmPlot() {
        return mPlot;
    }

    public String getmRelease() {
        return mRelease;
    }

    public float getmRating() {
        return mRating;
    }

    public int getmLength() {
        return mLength;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mPlot);
        dest.writeString(mRelease);
        dest.writeFloat(mRating);
        dest.writeInt(mLength);
    }
}
