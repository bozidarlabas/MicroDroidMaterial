package com.bozidar.labas.microdroid.mvp.interactor.impl;

import android.util.Log;

import com.bozidar.labas.microdroid.mvp.interactor.TvShowListInteractor;
import com.bozidar.labas.microdroid.mvp.listener.TvShowListener;
import com.bozidar.labas.microdroid.mvp.model.TvShowModel;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.microdroid.network.ServiceFactory;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by macbook on 19.10.2015..
 */
public class TvShowListInteractorImpl implements TvShowListInteractor, Callback<List<TvShowModel>> {

    TvShowListener listener;

    @Override
    public void loadTvShowList(TvShowListener listener) {
        this.listener = listener;
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT_MOCK);
        api.fetchTvShows(this);
    }

    @Override
    public void success(List<TvShowModel> model, Response response) {
        listener.onSuccess(model);
        Log.d("success", model.get(0).getName());
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", error.toString());
    }
}
