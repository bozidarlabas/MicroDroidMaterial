package com.bozidar.labas.microdroid.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.fragments.TutorialFragment;

/**
 * Created by macbook on 05.10.2015..
 */
public class TutorialAdapter extends FragmentPagerAdapter {

    private int pagerCount = 5;



    public TutorialAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return TutorialFragment.newInstance("TEST", R.drawable.logo);
            case 1:
                return TutorialFragment.newInstance("TEST2", 0);
            case 2:
                return TutorialFragment.newInstance("TEST3", 0);
            case 3:
                return TutorialFragment.newInstance("TEST4", 0);
            case 4:
                return TutorialFragment.newInstance("TEST5", 0);
        }
        return TutorialFragment.newInstance("TEST", 0);

    }

    @Override
    public int getCount() {
        return pagerCount;
    }
}
