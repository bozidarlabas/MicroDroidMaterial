package com.bozidar.microdroid.drawer;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.bozidar.microdroid.base.MicroActivity;

/**
 * Created by bozidar on 19.04.15..
 */
public abstract class MicroDrawerActivity extends MicroActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            MicroDrawerManager drawerManager = new MicroDrawerManager();
            drawerManager.setUp(this,getDrawerLayout(), getToolbar());
    }

    /**
     * Check if all necessarily objects are instanciated
     *
     * @return
     */
    private boolean checkInit() {
        return (setupToolbar() != 0 && setupDrawerLayoutRes() != 0);
    }

    /**
     * Get instance of DrawerLayout
     *
     * @return instance of drawer layout
     */
    private DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(setupDrawerLayoutRes());
    }

    /**
     * Setup root resource id for child of this abstract class
     *
     * @return id of root drawer layout
     */
    public abstract int setupDrawerLayoutRes();

    /**
     * Setup id of drawer header image
     *
     * @return id of drawer header image
     */
    public abstract int setupHeaderImageRes();

}