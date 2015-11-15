package com.bozidar.labas.microdroid.fragments;

import android.support.v4.view.ViewPager;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroFragment;

import butterknife.InjectView;


public class FragmentHome extends MicroFragment {

    @InjectView(R.id.pagerMy)
    ViewPager viewPager;


    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;
    }

    public FragmentHome() {
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_fragment_home;
    }

    @Override
    public void init() {

    }

    public ViewPager getViewPager(){
        return viewPager;
    }
}
