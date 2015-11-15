package com.bozidar.labas.microdroid.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.ActivityTvShowDetails;
import com.bozidar.labas.microdroid.mvp.model.TvShowModel;
import com.bozidar.labas.microdroid.mvp.model.item.TvShowItem;
import com.bozidar.labas.microdroid.mvp.presenter.TvShowPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.TvShowPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.TvShowsListView;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;
import com.bozidar.microdroid.transition.TransitionHelper;

import java.util.List;

import butterknife.InjectView;


public class FragmentTvShows extends MicroTabFrag implements TvShowsListView, MicroRecyclerAdapter.onMicroItemCLickListener {

    private static final String ARG_PARAM1 = "data1";
    private TvShowPresenter presenter;
    private MicroRecyclerAdapter adapter;

    @InjectView(R.id.list)
    RecyclerView tvStationsList;

    // TODO: Rename and change types of parameters
    private String data1;


    public static FragmentTvShows newInstance(String param1) {
        FragmentTvShows fragment = new FragmentTvShows();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTvShows() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.loadTvShowsList();
    }


    @Override
    public int setLayoutResource() {
        return R.layout.fragment_custom_fragment_main;
    }

    @Override
    public void init() {
        presenter = new TvShowPresenterImpl(this);
    }

    @Override
    public String setTabTitle() {
        return getArguments().getString(ARG_PARAM1);
    }

    @Override
    public void showTvShows(List<TvShowModel> model) {
        setUpRecyclerView(model);
    }

    public void setUpRecyclerView(List<TvShowModel> models) {
        this.tvStationsList.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
        if (adapter == null)
            adapter = new MicroRecyclerAdapter();
        this.tvStationsList.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);


        for(TvShowModel model : models){
            TvShowItem item = new TvShowItem(model);
            adapter.addItem(item);
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void microItemClicked(View view, MicroItem item) {
        TvShowModel clickedItemModel = ((TvShowItem)item).getModel();
        View v = view.findViewById(R.id.iv_show_image);

        TransitionHelper transitionHelper = new TransitionHelper();
        transitionHelper.transitionToActivity(ActivityTvShowDetails.class, getMicroActivity(), v, v.getTransitionName(), clickedItemModel.getImagePath());

        //Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(getMicroActivity(), v, v.getTransitionName()).toBundle();
       // getMicroActivity().startActivity(intent, bundle);
    }
}