package com.bozidar.microdroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public abstract class MicroSplashActivity extends AppCompatActivity {

    public abstract Class getNextClassActivity();

    public abstract int getSplashIntroTime();

    public abstract int setLayoutResource();

    public void delay() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                MicroSplashActivity.this.startActivity(new Intent(MicroSplashActivity.this.getBaseContext(), MicroSplashActivity.this.getNextClassActivity()));
                MicroSplashActivity.this.finish();
            }
        }, getSplashIntroTime());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResource());
        delay();
    }
}

