package com.bozidar.labas.microdroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class TutorialFragment extends Fragment {

    private static final String ARG_HEADER = "header";
    private static final String ARG_IMG = "image";

    private String headerText;
    private int headerImg;


    @InjectView(R.id.imgTutorial)
    ImageView ivHeader;
    @InjectView(R.id.txtText)
    TextView tvHeader;


    public static TutorialFragment newInstance(String title, int imgRes) {
        TutorialFragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HEADER, title);
        args.putInt(ARG_IMG, imgRes);
        fragment.setArguments(args);
        return fragment;
    }

    public TutorialFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            headerImg = getArguments().getInt(ARG_IMG);
            headerText = getArguments().getString(ARG_HEADER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tutorial, container, false);
        ButterKnife.inject(this, v);
        if(headerImg != 0)
            ivHeader.setImageResource(headerImg);
        else
            ivHeader.setVisibility(View.INVISIBLE);
        tvHeader.setText(headerText);
        return v;
    }







}
