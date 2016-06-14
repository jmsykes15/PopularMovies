package org.lineware.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Bhupendra Singh on 24/3/16.
 *
 * Content Provider for serving the saved favourite movies data
 */


public class MovieProvider extends ContentProvider{

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final int MOVIE_WITH_ID = 200;
    private MovieDbHelper mOpenHelper ;
    static final int MOVIE = 100;

    private static final SQLiteQueryBuilder movieBuilder;

    static {
        movieBuilder = new SQLiteQueryBuilder();
        movieBuilder.setTables(
                MovieContract.MovieEntry.TABLE_NAME
        );
    }

    private static final String sMovieSelection =
            MovieContract.MovieEntry.TABLE_NAME + "." + MovieContract.MovieEntry._ID + " = ? ";

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MovieContract.PATH_MOVIE,MOVIE);
        matcher.addURI(authority,MovieContract.PATH_MOVIE + "/#",MOVIE_WITH_ID);

        return  matcher;

    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new MovieDbHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor ;

        final int match = sUriMatcher.match(uri);
        if(MOVIE == match){
            retCursor  = mOpenHelper.getReadableDatabase().query(
                    MovieContract.MovieEntry.TABLE_NAME, projection, selection, selectionArgs,
                    null, null, sortOrder);
        }else if (MOVIE_WITH_ID == match){
            retCursor = getMovieWithID(uri,projection,sortOrder);
        }
        else throw new UnsupportedOperationException("Cannot find uri: " + uri);



        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        if (match == MOVIE) return MovieContract.MovieEntry.CONTENT_TYPE;
        else throw new UnsupportedOperationException();

    }

    private Cursor getMovieWithID(Uri uri, String[] projection, String sortOrder){
        String pathSegment = uri.getLastPathSegment();
        return movieBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sMovieSelection,new String[]{pathSegment},
                null,null,
                sortOrder);
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {


        final SQLiteDatabase db =  mOpenHelper.getWritableDatabase();
        Uri uri1;
        final int match = sUriMatcher.match(uri);
        if (match == MOVIE) {
                long _id = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
                if( _id >0){
                    uri1  = MovieContract.MovieEntry.buildMovieUri(_id);
                }
                else{
                    throw new android.database.SQLException();
                }

            }
        else throw new UnsupportedOperationException();


        getContext().getContentResolver().notifyChange(uri, null);
        return uri1;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int deleted;

        final int match = sUriMatcher.match(uri);
        if (null == selection) selection = "1";

        if (match == MOVIE) deleted = db.delete(MovieContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
        else throw new UnsupportedOperationException();

        if (deleted != 0) getContext().getContentResolver().notifyChange(uri, null);

        return deleted;


    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int updated =0;

        final int match = sUriMatcher.match(uri);

        if (match == MOVIE) updated = db.update(MovieContract.MovieEntry.TABLE_NAME, values, selection, selectionArgs);
        else throw new UnsupportedOperationException();

        if(updated != 0 ) getContext().getContentResolver().notifyChange(uri,null);

        return updated;

    }
}
