package com.bozidar.labas.microdroid.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;


public class CustomFragmentMain2 extends MicroTabFrag {

    private static final String ARG_PARAM1 = "data1";
    private static final String ARG_PARAM2 = "data2";

    // TODO: Rename and change types of parameters
    private String data1;
    private String data2;

    private OnFragmentInteractionListener2 mListener;


    public static CustomFragmentMain2 newInstance(String param1, String param2) {
        CustomFragmentMain2 fragment = new CustomFragmentMain2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CustomFragmentMain2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data1 = getArguments().getString(ARG_PARAM1);
            data2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener2) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_custom_fragment_main;
    }

    @Override
    public void init() {

    }

    @Override
    public String setTabTitle() {
        return getArguments().getString(ARG_PARAM1);
    }


    public interface OnFragmentInteractionListener2 {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}