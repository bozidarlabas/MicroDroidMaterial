package com.bozidar.labas.microdroid.mvp.model.item;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.MockModel;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by macbook on 10.10.2015..
 */
public class MockModelItem implements MicroItem {

    MockModel model;

    @InjectView(R.id.CONTACT_name)
    TextView tvName;

    @InjectView(R.id.CONTACT_phone)
    TextView tvPhone;

    @InjectView(R.id.CONTACT_circle)
    View viewCircle;

    public MockModelItem(MockModel model){
        this.model = model;
    }


    @Override
    public int getLayoutResource() {
        return R.layout.list_item_simple;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        tvName.setText(model.get(MockModel.Field.NAME));
        tvPhone.setText(model.get(MockModel.Field.PHONE));

        GradientDrawable bgShape = (GradientDrawable) viewCircle.getBackground();
        bgShape.setColor(Color.parseColor(model.get(MockModel.Field.COLOR)));
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.inject(this, view);
    }

}
