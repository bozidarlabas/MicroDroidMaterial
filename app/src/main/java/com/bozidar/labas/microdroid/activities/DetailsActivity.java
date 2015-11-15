package com.bozidar.labas.microdroid.activities;

import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.MockModel;
import com.bozidar.microdroid.base.MicroActivity;

import butterknife.InjectView;

public class DetailsActivity extends MicroActivity {

    public final static String ID = "ID";

    @InjectView(R.id.DETAILS_name)
    TextView tvName;
    @InjectView(R.id.toolbar_title)
    TextView toobarTitle;

    @Override
    public void init() {
        MockModel model = MockModel.getItem(getIntent().getIntExtra(ID, 0));
        String name = model.get(MockModel.Field.NAME);
        tvName.setText(name);
        setCustomToolbar();
    }

    private void setCustomToolbar() {
        toobarTitle.setTypeface(setToolbarFont("Courgette-Regular.ttf"));
    }


    @Override
    public int setupToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }






}
