package com.bozidar.labas.microdroid.activities;

import android.widget.ImageView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;
import com.bumptech.glide.Glide;

import butterknife.InjectView;

public class ActivityTvShowDetails extends MicroActivity {

    @InjectView(R.id.ivHeader)
    ImageView ivHeader;


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.tv_show_details;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        Glide.with(this)
                .load(getIntent().getStringExtra("0"))
                .into(ivHeader);
    }
}
