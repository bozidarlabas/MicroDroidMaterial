package com.bozidar.labas.microdroid.mvp.view;

import com.bozidar.labas.microdroid.mvp.model.TvShowModel;

import java.util.List;

/**
 * Created by macbook on 18.10.2015..
 */
public interface TvShowsListView {
    public void showTvShows(List<TvShowModel> model);
}
