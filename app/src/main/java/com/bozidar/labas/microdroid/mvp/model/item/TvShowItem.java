package com.bozidar.labas.microdroid.mvp.model.item;

import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.TvShowModel;
import com.bozidar.microdroid.iphoneization.circleimageview.CircleImageView;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by macbook on 19.10.2015..
 */
public class TvShowItem implements MicroItem {

    @InjectView(R.id.tv_show_name)
    TextView tvShowName;

    @InjectView(R.id.iv_show_image)
    CircleImageView ivShow;

    private TvShowModel model;

    public TvShowItem(TvShowModel model){
        this.model = model;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.tv_show_item;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        tvShowName.setText(model.getName());
        Glide.with(view.getContext())
                .load(model.getImagePath())
                .into(ivShow);
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.inject(this, view);
    }

    public TvShowModel getModel() {
        return model;
    }
}
