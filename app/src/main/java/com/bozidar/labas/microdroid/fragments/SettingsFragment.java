package com.bozidar.labas.microdroid.fragments;

import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroFragment;

import butterknife.OnClick;


public class SettingsFragment extends MicroFragment {


    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    public SettingsFragment() {
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_settings;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.btn_language)
    public void choseLanguage(View v){
        new MaterialDialog.Builder(getMicroActivity())
                .title(R.string.select_language)
                .items(R.array.languages)
                .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        Log.d("selected language", text + "");
                        return true; // allow selection
                    }
                })
                .positiveText(R.string.confirm)
                .show();
    }
}

//https://github.com/afollestad/material-dialogs



