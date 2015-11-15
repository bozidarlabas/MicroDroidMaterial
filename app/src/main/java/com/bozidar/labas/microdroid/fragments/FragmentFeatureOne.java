package com.bozidar.labas.microdroid.fragments;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroFragment;


public class FragmentFeatureOne extends MicroFragment {

    // TODO: Rename and change types and number of parameters
    public static FragmentFeatureOne newInstance() {
        FragmentFeatureOne fragment = new FragmentFeatureOne();
        return fragment;
    }


    @Override
    public int setLayoutResource() {
        return R.layout.fragment_feature_one;
    }

    @Override
    public void init() {

    }
}
