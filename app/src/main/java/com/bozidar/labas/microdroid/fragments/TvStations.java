package com.bozidar.labas.microdroid.fragments;

import android.support.v7.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroFragment;
import com.bozidar.microdroid.slidingtab.manager.MicroTabManager;

import butterknife.InjectView;

public class TvStations extends MicroFragment{


    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.pagerMy)
    ViewPager viewPager;


    public static TvStations newInstance() {
        TvStations fragment = new TvStations();
        return fragment;
    }

    public TvStations() {}

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_tv_stations;
    }

    @Override
    public void init() {
        ActionBar actionBar = getMicroActivity().getSupportActionBar();
        if(actionBar != null) getMicroActivity().getSupportActionBar().setElevation(0);
        setUpTabs();
    }



    public void setUpTabs() {
        MicroTabManager microTabManager = new MicroTabManager(getChildFragmentManager(), viewPager, tabLayout);
        FragmentTvStations fragmentCustomMain = FragmentTvStations.newInstance("Tv Stations");
        FragmentTvShows fragmentCustomMain2 = FragmentTvShows.newInstance("Tv Shows");
        microTabManager.addTab(fragmentCustomMain);
        microTabManager.addTab(fragmentCustomMain2);
        microTabManager.init();
    }



}
