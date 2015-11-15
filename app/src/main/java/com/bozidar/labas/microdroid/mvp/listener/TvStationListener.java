package com.bozidar.labas.microdroid.mvp.listener;

import com.bozidar.labas.microdroid.mvp.model.TvStationModels;

import java.util.List;

/**
 * Created by macbook on 18.10.2015..
 */
public interface TvStationListener {
    void onSuccess(List<TvStationModels> model, String token);
}
