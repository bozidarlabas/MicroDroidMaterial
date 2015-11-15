package com.bozidar.labas.microdroid.fragments;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.ListActivity;
import com.bozidar.microdroid.base.MicroFragment;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import butterknife.InjectView;
import butterknife.OnClick;


public class FavoritesFragment extends MicroFragment {

    @InjectView(R.id.fab)
    FloatingActionsMenu fab;

    private OnFavoritesShowListener listener;


    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    public FavoritesFragment() {}


    @Override
    public int setLayoutResource() {
        return R.layout.fragment_favorites;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.btn_fab_sub1)
    public void onClick(View v) {
        startActivity(new Intent(getMicroActivity(), ListActivity.class));
        fab.collapse();
    }

    @OnClick(R.id.btnChoseTvShow)
    public void openTvShows(){
        listener.opensTvShows();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFavoritesShowListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFavoritesShowListener {
        public void opensTvShows();
    }

}
