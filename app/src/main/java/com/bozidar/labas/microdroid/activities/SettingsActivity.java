package com.bozidar.labas.microdroid.activities;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;

public class SettingsActivity extends MicroActivity {

    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.fragment_settings;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {

    }



}
