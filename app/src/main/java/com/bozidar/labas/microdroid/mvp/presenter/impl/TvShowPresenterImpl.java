package com.bozidar.labas.microdroid.mvp.presenter.impl;

import com.bozidar.labas.microdroid.mvp.interactor.TvShowListInteractor;
import com.bozidar.labas.microdroid.mvp.interactor.impl.TvShowListInteractorImpl;
import com.bozidar.labas.microdroid.mvp.listener.TvShowListener;
import com.bozidar.labas.microdroid.mvp.model.TvShowModel;
import com.bozidar.labas.microdroid.mvp.presenter.TvShowPresenter;
import com.bozidar.labas.microdroid.mvp.view.TvShowsListView;

import java.util.List;

/**
 * Created by macbook on 19.10.2015..
 */
public class TvShowPresenterImpl implements TvShowPresenter, TvShowListener {

    TvShowsListView view;
    TvShowListInteractor interactor;

    public TvShowPresenterImpl(TvShowsListView view){
        this.view = view;
        interactor = new TvShowListInteractorImpl();

    }

    @Override
    public void loadTvShowsList() {
        interactor.loadTvShowList(this);
    }

    @Override
    public void onSuccess(List<TvShowModel> model) {
        view.showTvShows(model);
    }
}
