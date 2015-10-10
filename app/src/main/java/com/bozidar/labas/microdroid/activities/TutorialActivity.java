package com.bozidar.labas.microdroid.activities;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.iphoneization.ViewPager.CircleIndicator;
import com.bozidar.microdroid.slidingtab.adapter.MicroPagerAdapter;

import butterknife.InjectView;
import butterknife.OnClick;

public class TutorialActivity extends MicroActivity{

    @InjectView(R.id.viewpager_default)
    ViewPager viewPagerTutorial;

    @InjectView(R.id.indicator_default)
    CircleIndicator circleIndicator;

    @Override
    public void init() {
        MicroPagerAdapter pagerAdapter = new MicroPagerAdapter(getSupportFragmentManager());
        viewPagerTutorial.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(viewPagerTutorial);
    }


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_tutorial;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }


    private int getNextPageIndex(){
        return viewPagerTutorial.getCurrentItem() + 1;
    }


    @OnClick(R.id.btnNext)
    public void goToNextpage(View v){
        viewPagerTutorial.setCurrentItem(getNextPageIndex());
    }

    @OnClick(R.id.btnSkip)
    public void skipTutorial(View v){

    }
}