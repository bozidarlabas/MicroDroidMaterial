package com.bozidar.labas.microdroid.mvp.interactor;

import com.bozidar.labas.microdroid.mvp.listener.TvStationListener;

/**
 * Created by macbook on 18.10.2015..
 */
public interface TvStationListInteractor {
    void loadTvShowsList(TvStationListener listener, String token);
}
