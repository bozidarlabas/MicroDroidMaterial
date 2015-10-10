package com.bozidar.labas.microdroid.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.ListActivity;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import butterknife.OnClick;


public class CustomFragmentMain extends MicroTabFrag {

    private static final String ARG_PARAM1 = "data1";
    private static final String ARG_PARAM2 = "data2";

    // TODO: Rename and change types of parameters
    private String data1;
    private String data2;

    private OnFragmentInteractionListener mListener;


    public static CustomFragmentMain newInstance(String param1, String param2) {
        CustomFragmentMain fragment = new CustomFragmentMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CustomFragmentMain() {
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
            mListener = (OnFragmentInteractionListener) activity;
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

    @OnClick(R.id.btn_open_listactivity)
    public void openListActivity(View v){
        startActivity(new Intent(getMicroActivity(), ListActivity.class));
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
