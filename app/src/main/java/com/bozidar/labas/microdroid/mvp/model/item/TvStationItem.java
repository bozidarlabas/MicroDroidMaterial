package com.bozidar.labas.microdroid.mvp.model.item;

import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.TvStationModels;
import com.bozidar.microdroid.iphoneization.circleimageview.CircleImageView;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by macbook on 18.10.2015..
 */
public class TvStationItem implements MicroItem {

    @InjectView(R.id.tv_station_name)
    TextView tvStationName;

    @InjectView(R.id.iv_station_image)
    CircleImageView ivTvStation;

    private TvStationModels model;

    public TvStationItem(TvStationModels model){
        this.model = model;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.tv_station_item;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        tvStationName.setText(model.getName());
        Glide.with(view.getContext())
                .load(model.getImgUrl())
                .into(ivTvStation);
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.inject(this, view);
    }
}
