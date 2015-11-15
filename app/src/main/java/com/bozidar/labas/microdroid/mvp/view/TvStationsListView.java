package com.bozidar.labas.microdroid.mvp.view;

import com.bozidar.labas.microdroid.mvp.model.TvStationModels;
import com.bozidar.microdroid.model.User;

import java.util.List;

/**
 * Created by macbook on 18.10.2015..
 */
public interface TvStationsListView {
    public void showTvStations(List<TvStationModels> model, User user);
}
