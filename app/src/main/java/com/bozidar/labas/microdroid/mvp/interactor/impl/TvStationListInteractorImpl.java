package com.bozidar.labas.microdroid.mvp.interactor.impl;

import android.util.Log;

import com.bozidar.labas.microdroid.mvp.interactor.TvStationListInteractor;
import com.bozidar.labas.microdroid.mvp.listener.TvStationListener;
import com.bozidar.labas.microdroid.mvp.model.TvStationModels;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.network.ServiceFactory;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by macbook on 18.10.2015..
 */
public class TvStationListInteractorImpl implements TvStationListInteractor, Callback<List<TvStationModels>>{

    TvStationListener listener;

    @Override
    public void loadTvShowsList(TvStationListener listener, String token) {
        this.listener = listener;
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        api.fetchTvStations(token, this);
    }

    @Override
    public void success(List<TvStationModels> model, Response response) {


        String newToken = TokenManager.getTokenFromHeader(response);
        listener.onSuccess(model, newToken);

    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("success", error.toString());
    }
}
