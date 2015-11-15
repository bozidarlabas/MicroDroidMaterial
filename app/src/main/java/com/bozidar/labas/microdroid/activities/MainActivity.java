package com.bozidar.labas.microdroid.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.fragments.FavoritesFragment;
import com.bozidar.labas.microdroid.fragments.FragmentFeatureOne;
import com.bozidar.labas.microdroid.fragments.FragmentSearch;
import com.bozidar.labas.microdroid.fragments.SettingsFragment;
import com.bozidar.labas.microdroid.fragments.SocialLoginFragment;
import com.bozidar.labas.microdroid.fragments.TvStations;
import com.bozidar.labas.microdroid.mvp.presenter.LoginPresenter;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.network.ServiceFactory;
import com.bumptech.glide.Glide;
import com.github.gorbin.asne.core.SocialNetwork;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends MicroActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        FragmentSearch.FragmentCallbacks,
        FavoritesFragment.OnFavoritesShowListener
         {

    private static final String NAV_ITEM_INDEX = "navItemIndex";
    private static final String FIRST_FRAGMENT_ADDED = "is_first_fragment_added";
    private static final String NETWORK_ID = "NETWORK_ID";
    private int networkId;

    private SocialNetwork socialNetwork;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private LoginPresenter loginPresenter;

    @InjectView(R.id.toolbar_title)
    TextView tvToolbar;
    @InjectView(R.id.nav_view)
    NavigationView navigationView;


    @InjectView(R.id.ivLoggedUser)
    ImageView ivLoggedUser;

    @InjectView(R.id.tvName)
    TextView tvLoggedUserName;

    @InjectView(R.id.tvMail)
    TextView tvLoggedUserMail;

    ActionBarDrawerToggle drawerToggle;


    @Override
    public void opensTvShows() {
        setFragment(R.id.content, TvStations.newInstance());
        this.openedFragment = OpenedFragment.FRAGMENT_TVSTATIONS;
    }



    public enum OpenedFragment {
        FRAGMENT_MAIN,
        FRAGMENT_FAVORITES,
        FRAGMENT_TVSTATIONS,
        FRAGMENT_FEATURE_ONE,
        FRAGMENT_SETTINGS
    }

    OpenedFragment openedFragment;

    private int navItemIndex;
    private boolean isFirstFragmentAdded = false;




    /**
     * Replacement for OnCreate method that is first called
     */
    @Override
    public void init() {
        networkId = getIntent().getIntExtra("networkId", 0);
        if(SocialLoginFragment.mSocialNetworkManager != null && networkId != 0){
            socialNetwork = SocialLoginFragment.mSocialNetworkManager.getSocialNetwork(networkId);
        }



        setUpDrawer();
        if (savedInstanceState != null) {
            navItemIndex = savedInstanceState.getInt(NAV_ITEM_INDEX);
            isFirstFragmentAdded = savedInstanceState.getBoolean(FIRST_FRAGMENT_ADDED);
        } else
            navItemIndex = 0;
        customToolbar();


        showUserData();

        openedFragment = OpenedFragment.FRAGMENT_MAIN;
        setFragment(R.id.content, FavoritesFragment.newInstance());
        //setUpNextFragment(false, View.GONE, OpenedFragment.FRAGMENT_FAVORITES);

    }

    private void showUserData() {
        SharedPrefs prefs = SharedPrefs.getInstance();
        User user = prefs.loadObject(getResources().getString(R.string.user_data), this);

//        Log.d("Token", user.getToken());
        if (user != null) {
            if (user.getAvatar() != null) {
                Log.d("trpaj", user.getAvatar());
                Glide.with(this)
                        .load(user.getAvatar())
                        .into(ivLoggedUser);
            }
            if (user.getName() != null) {
                tvLoggedUserName.setText(user.getName());
            }
            if (user.getEmail() != null) {
                tvLoggedUserMail.setText(user.getEmail());
            }
        }
    }

    private void customToolbar() {
        getSupportActionBar().setElevation(10f);
        getSupportActionBar().setTitle(null);
        tvToolbar.setTypeface(setToolbarFont("Courgette-Regular.ttf"));
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


    /**
     * ------------DRAWER--------------------
     */
    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));

        navigationView.setNavigationItemSelectedListener(this);
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
        Log.d("test", "test");
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


    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_INDEX, navItemIndex);
        outState.putBoolean(FIRST_FRAGMENT_ADDED, isFirstFragmentAdded);
    }

    @Override
    public void searchClicked(String searchText) {
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {

            case R.id.nav_settings:
                if (openedFragment != OpenedFragment.FRAGMENT_SETTINGS) {
                    setFragment(R.id.content, SettingsFragment.newInstance());
                    setUpNextFragment(false, View.GONE, OpenedFragment.FRAGMENT_SETTINGS);
                }
                break;

            case R.id.nav_home:
                if (openedFragment != OpenedFragment.FRAGMENT_FAVORITES) {
                    getSupportActionBar().setElevation(10f);
                    setFragment(R.id.content, FavoritesFragment.newInstance());
                    setUpNextFragment(false, View.GONE, OpenedFragment.FRAGMENT_FAVORITES);
                }
                break;

            case R.id.nav_feature_one:
                if (openedFragment != OpenedFragment.FRAGMENT_FEATURE_ONE) {
                    setFragment(R.id.content, FragmentFeatureOne.newInstance());
                    setUpNextFragment(false, View.GONE, OpenedFragment.FRAGMENT_FEATURE_ONE);
                }
                break;

            case R.id.nav_tv_stations:
                if (openedFragment != OpenedFragment.FRAGMENT_TVSTATIONS) {
                    setFragment(R.id.content, TvStations.newInstance());
                    setUpNextFragment(false, View.GONE, OpenedFragment.FRAGMENT_TVSTATIONS);
                }
                break;

            case R.id.nav_logout:
                if(socialNetwork != null)
                    socialNetwork.logout();
                SharedPrefs.getInstance().removeUser(this);

                startActivity(new Intent(this, LoginActivity.class));
                finish();
        }
        return false;
    }

    private void removeTokenFromServer(String token){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);

        api.logout(token, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d("success", s);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
            }
        });
    }

    private void setUpNextFragment(boolean searchItemVisibility, int tabVisibility, OpenedFragment openedFragment) {
        MenuItem itemMenu = menu.findItem(R.id.action_search);
        itemMenu.setVisible(searchItemVisibility);
        toolbar.invalidate();
        this.openedFragment = openedFragment;
        drawerLayout.closeDrawers();
    }

    private void logoutFromFacebook() {

    }

    private void logoutFromTwiter() {

    }


}