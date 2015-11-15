package com.bozidar.labas.microdroid.activities;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.microdroid.base.MicroSplashActivity;

public class SplashActivity extends MicroSplashActivity {

    private static final int TIME = 1850;

    @Override
    public Class getNextClassActivity() {
        if(isUserLogged())
            return MainActivity.class;
        return TutorialActivity.class;
    }

    @Override
    public int getSplashIntroTime() {
        return TIME;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.activity_splash;
    }

    public boolean isUserLogged(){
        String loggedIn  = SharedPrefs.getInstance().getValue(this, getResources().getString(R.string.login));
        return !loggedIn.equals("");
    }
}