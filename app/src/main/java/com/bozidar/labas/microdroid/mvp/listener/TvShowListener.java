package com.bozidar.labas.microdroid.mvp.listener;

import com.bozidar.labas.microdroid.mvp.model.TvShowModel;

import java.util.List;

/**
 * Created by macbook on 19.10.2015..
 */
public interface TvShowListener {
    void onSuccess(List<TvShowModel> model);
}
