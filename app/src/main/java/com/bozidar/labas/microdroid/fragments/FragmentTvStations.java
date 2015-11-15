package com.bozidar.labas.microdroid.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.TvStationModels;
import com.bozidar.labas.microdroid.mvp.model.item.TvStationItem;
import com.bozidar.labas.microdroid.mvp.presenter.TvStationPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.TvStationPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.TvStationsListView;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import java.util.List;

import butterknife.InjectView;


public class FragmentTvStations extends MicroTabFrag implements TvStationsListView {

    private static final String ARG_PARAM1 = "data1";
    private String data1;
    private MicroRecyclerAdapter adapter;
    private TvStationPresenter presenter;

    @InjectView(R.id.list)
    RecyclerView tvStationsList;

    public static FragmentTvStations newInstance(String param1) {
        FragmentTvStations fragment = new FragmentTvStations();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTvStations() {}

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_custom_fragment_main;
    }

    @Override
    public void init() {
        presenter = new TvStationPresenterImpl(this);
    }

    @Override
    public String setTabTitle() {
        return getArguments().getString(ARG_PARAM1);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPrefs prefs = SharedPrefs.getInstance();
        User user = prefs.loadObject(getResources().getString(R.string.user_data), getMicroActivity());
        presenter.loadTvStationsList(user);
    }

    @Override
    public void showTvStations(List<TvStationModels> model, User user) {
        TokenManager.storeNewTokenLocaly(getMicroActivity(), user);
        setUpRecyclerView(model);
    }

    private void setUpRecyclerView(List<TvStationModels> models) {
        this.tvStationsList.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
        if (adapter == null)
            adapter = new MicroRecyclerAdapter();
        this.tvStationsList.setAdapter(adapter);

//        adapter.setOnMicroCLickListener(this);


        for(TvStationModels model : models){
            TvStationItem item = new TvStationItem(model);
            adapter.addItem(item);
        }
    }
}
