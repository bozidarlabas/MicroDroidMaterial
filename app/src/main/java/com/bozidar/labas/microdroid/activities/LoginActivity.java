package com.bozidar.labas.microdroid.activities;

import android.content.Intent;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;

import butterknife.OnClick;

public class LoginActivity extends MicroActivity {

    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.btn_sign_up)
    public void goToRegistrationActivity(View v){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.email_sign_in_button)
    public void login(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}
