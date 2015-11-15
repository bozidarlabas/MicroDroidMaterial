package com.bozidar.labas.microdroid.mvp.listener;

import com.bozidar.labas.microdroid.mvp.model.response.LoginResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;

/**
 * Created by kanta on 23.09.15..
 */
public interface OnLoginFinishedListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess(Response<LoginResponse> loginResponseResponse);
}
