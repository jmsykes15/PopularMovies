package org.lineware.popularmovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.lineware.popularmovies.pojos.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmsykes15 on 6/14/16.
 */
public class Movies {
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
        return results;
    }
}
