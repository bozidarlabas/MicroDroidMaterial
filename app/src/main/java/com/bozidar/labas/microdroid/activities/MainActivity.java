package com.bozidar.labas.microdroid.activities;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.fragments.CustomFragmentMain;
import com.bozidar.labas.microdroid.fragments.CustomFragmentMain2;
import com.bozidar.labas.microdroid.fragments.FragmentSearch;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.slidingtab.manager.MicroTabManager;

import butterknife.InjectView;

public class MainActivity extends MicroActivity implements
        CustomFragmentMain.OnFragmentInteractionListener,
        CustomFragmentMain2.OnFragmentInteractionListener2,
        FragmentSearch.FragmentCallbacks {

    private static final String NAV_ITEM_INDEX = "navItemIndex";
    private static final String FIRST_FRAGMENT_ADDED = "is_first_fragment_added";

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.pagerMy)
    ViewPager viewPager;
    @InjectView(R.id.toolbar_title)
    TextView tvToolbar;

    ActionBarDrawerToggle drawerToggle;

    private Fragment fragment;
    private int navItemIndex;
    private boolean isFirstFragmentAdded = false;

    /**
     * Replacement for OnCreate method that is first called
     */
    @Override
    public void init() {
        setUpDrawer();

        setUpTabs();

        if (savedInstanceState != null) {
            navItemIndex = savedInstanceState.getInt(NAV_ITEM_INDEX);
            isFirstFragmentAdded = savedInstanceState.getBoolean(FIRST_FRAGMENT_ADDED);
        } else
            navItemIndex = 0;

        customToolbar();
    }

    private void customToolbar(){
        Typeface courgetteFont = Typeface.createFromAsset(getAssets(), "Courgette-Regular.ttf");
        tvToolbar.setTypeface(courgetteFont);

    }

    /**
     * Method for setting up toolbar
     *
     * @return
     */
    @Override
    public int setupToolbar() {
        return R.id.toolbar;
    }

    /**
     * method for settin up Layout resource for this Activity
     *
     * @return
     */
    @Override
    public int setupLayoutRes() {
        return R.layout.activity_main;
    }

    /**
     * Method for setting up Menu resource for this Activity
     *
     * @return
     */
    @Override
    public int setupMenuRes() {
        return R.menu.menu_search;
    }

    /**
     * -------------------SEARCH--------------
     *
     * @return
     */

    public void menuSearch() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerSearch, new FragmentSearch()).addToBackStack(null).commit();
    }




    /*@Override
    public String[] setSearchableData() {
        return this.getResources().getStringArray(R.array.countries_array);
    }

    @Override
    public void onSearchItemClick(View v, String item) {
        Toast.makeText(this, "Clicked item: " + item, Toast.LENGTH_SHORT).show();
    }
    */


    /**
     * ------------DRAWER--------------------
     */
    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == com.bozidar.microdroid.R.id.action_search) {
            Log.d("Search", "Search");
            menuSearch();
        }

        return super.onOptionsItemSelected(item);
    }


    public void setUpTabs() {
        MicroTabManager microTabManager = new MicroTabManager(getSupportFragmentManager(), viewPager, tabLayout);
        CustomFragmentMain fragmentCustomMain = CustomFragmentMain.newInstance("jedan", "dva");
        CustomFragmentMain2 fragmentCustomMain2 = CustomFragmentMain2.newInstance("tri", "cetiri");
        microTabManager.addTab(fragmentCustomMain);
        microTabManager.addTab(fragmentCustomMain2);
        microTabManager.init();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_INDEX, navItemIndex);
        outState.putBoolean(FIRST_FRAGMENT_ADDED, isFirstFragmentAdded);
    }

    @Override
    public void searchClicked(String searchText) {

    }
}
