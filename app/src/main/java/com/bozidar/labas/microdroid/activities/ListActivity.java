package com.bozidar.labas.microdroid.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.MockModel;
import com.bozidar.labas.microdroid.mvp.model.item.MockModelItem;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.ArrayList;

import butterknife.InjectView;

public class ListActivity extends MicroActivity implements MicroRecyclerAdapter.onMicroItemCLickListener {

    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.toolbar_title)
    TextView tvToolbar;

    private MicroRecyclerAdapter adapter;


    public void setRecyclerView() {
        this.list.setLayoutManager(new LinearLayoutManager(this));
        if (this.adapter == null) adapter = new MicroRecyclerAdapter();

        this.list.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);

        ArrayList<MockModel> data = MockModel.getData();

        for (MockModel model : data) {
            Log.d("test", model.get(MockModel.Field.NAME));
            MockModelItem projectItem = new MockModelItem(model);
            adapter.addItem(projectItem);
        }
    }

    @Override
    public int setupToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_list;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setRecyclerView();
        setCustomToolbar();
    }

    private void setCustomToolbar() {
        tvToolbar.setTypeface(setToolbarFont("Courgette-Regular.ttf"));
    }


    @Override
    public void microItemClicked(View view, MicroItem item) {
        Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
        MockModel clickedItem = ((MockModelItem) item).getModel();
        intent.putExtra(DetailsActivity.ID, clickedItem.getId());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                ListActivity.this,
                new Pair<>(view.findViewById(R.id.CONTACT_circle),
                        getString(R.string.transition_name_circle))
        );

        ActivityCompat.startActivity(ListActivity.this, intent, options.toBundle());
    }


}
