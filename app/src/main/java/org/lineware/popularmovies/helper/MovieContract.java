package org.lineware.popularmovies.helper;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jmsykes15 on 6/1/16.
 */
public class MovieContract {

    public static final int COLUMN_MOVIE_ID = 1;
    public static final int COLUMN_TITLE = 2;
    public static final int COLUMN_RELEASE = 3;
    public static final int COLUMN_RATING = 4;
    public static final int COLUMN_POSTER = 5;
    public static final int COLUMN_BANNER = 6;
    public static final int COLUMN_PLOT = 7;

    public static final String[] Movie_COLUMNS = {
            MovieContract.MovieEntry.TABLE_NAME + "." + MovieContract.MovieEntry._ID,
            MovieContract.MovieEntry.COLUMN_MOVIE_ID,
            MovieContract.MovieEntry.COLUMN_TITLE,
            MovieContract.MovieEntry.COLUMN_RELEASE,
            MovieContract.MovieEntry.COLUMN_RATING,
            MovieContract.MovieEntry.COLUMN_POSTER,
            MovieContract.MovieEntry.COLUMN_BANNER,
            MovieContract.MovieEntry.COLUMN_PLOT,

    };


    public static final String CONTENT_AUTHORITY = "org.lineware.popularmovies";

    public static final Uri BASE_CONTENT_URL =  Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_MOVIE = "movie";

    public static class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URL.buildUpon().appendPath(PATH_MOVIE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +"/" +CONTENT_AUTHORITY +"/" +PATH_MOVIE;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" +CONTENT_AUTHORITY +"/" +PATH_MOVIE;

        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_BANNER = "banner";
        public static final String COLUMN_PLOT = "plot";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RELEASE = "release";

        public static Uri buildMovieUri(long id){
            long mid = id;
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

//        public static Uri buildMovieUriAt(int position){
//            return CONTENT_URI.buildUpon().appendPath(position)
//        }
    }
}
