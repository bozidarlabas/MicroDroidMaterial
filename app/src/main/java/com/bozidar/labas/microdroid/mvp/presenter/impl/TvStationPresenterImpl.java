package com.bozidar.labas.microdroid.mvp.presenter.impl;

import com.bozidar.labas.microdroid.mvp.interactor.TvStationListInteractor;
import com.bozidar.labas.microdroid.mvp.interactor.impl.TvStationListInteractorImpl;
import com.bozidar.labas.microdroid.mvp.listener.TvStationListener;
import com.bozidar.labas.microdroid.mvp.model.TvStationModels;
import com.bozidar.labas.microdroid.mvp.presenter.TvStationPresenter;
import com.bozidar.labas.microdroid.mvp.view.TvStationsListView;
import com.bozidar.microdroid.model.User;

import java.util.List;

/**
 * Created by macbook on 18.10.2015..
 */
public class TvStationPresenterImpl implements TvStationPresenter, TvStationListener {

    private TvStationsListView view;
    private TvStationListInteractor interactor;
    private User user;

    public TvStationPresenterImpl(TvStationsListView view) {
        this.view = view;
        interactor = new TvStationListInteractorImpl();
    }

    @Override
    public void loadTvStationsList(User user) {
        this.user = user;
        interactor.loadTvShowsList(this, user.getToken());
    }

    @Override
    public void onSuccess(List<TvStationModels> model, String newToken) {
        this.user.setToken(newToken);
        view.showTvStations(model, user);
    }
}
