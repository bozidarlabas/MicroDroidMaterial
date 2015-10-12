package com.bozidar.labas.microdroid.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.MockModel;
import com.bozidar.microdroid.base.MicroActivity;

import butterknife.InjectView;

public class DetailsActivity extends MicroActivity {

    public final static String ID = "ID";

    @InjectView(R.id.DETAILS_name)
    TextView tvName;

    @Override
    public void init() {
        MockModel model = MockModel.getItem(getIntent().getIntExtra(ID, 0));

        String name = model.get(MockModel.Field.NAME);
        tvName.setText(name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public int setupToolbar() {
        return 0;
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
