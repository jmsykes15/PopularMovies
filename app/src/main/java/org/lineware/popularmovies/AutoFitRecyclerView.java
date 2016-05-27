package org.lineware.popularmovies;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Source Location: https://github.com/chiuki/android-recyclerview/blob/master/app/src/main/java/com/sqisland/android/recyclerview/AutofitRecyclerView.java
 *
 * Created by jmsykes15 on 3/25/16.
 */
public class AutoFitRecyclerView extends CursorRecyclerViewAdapter {
    private GridLayoutManager manager;
    private int columnWidth = -1;

    public AutoFitRecyclerView(Context context, Cursor cursor) {
        super(context, cursor);
        init(context, null);
    }

    public AutoFitRecyclerView(Context context, Cursor cursor, AttributeSet attrs) {
        super(context, cursor, attrs);
        init(context, attrs);
    }

    public AutoFitRecyclerView(Context context, Cursor cursor, AttributeSet attrs, int defStyle) {
        super(context, cursor, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

        manager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(manager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
