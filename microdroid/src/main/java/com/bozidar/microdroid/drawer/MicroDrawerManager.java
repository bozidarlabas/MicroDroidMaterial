package com.bozidar.microdroid.drawer;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bozidar.microdroid.R;

/**
 * Created by Bozidar on 2.7.2015..
 */
public class MicroDrawerManager {

    private ActionBarDrawerToggle drawerToggle;

    public void setUp(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar) {
        this.drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.post(new Runnable() {

            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }
}
