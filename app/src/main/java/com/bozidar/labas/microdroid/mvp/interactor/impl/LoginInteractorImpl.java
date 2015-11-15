package com.bozidar.labas.microdroid.mvp.interactor.impl;

import android.text.TextUtils;
import android.util.Log;

import com.bozidar.labas.microdroid.mvp.interactor.LoginInteractor;
import com.bozidar.labas.microdroid.mvp.listener.OnLoginFinishedListener;
import com.bozidar.labas.microdroid.mvp.model.LoginModel;
import com.bozidar.labas.microdroid.mvp.model.response.LoginResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.network.ServiceFactory;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by kanta on 23.09.15..
 */
public class LoginInteractorImpl implements LoginInteractor, Callback<Response<LoginResponse>>{

    private final String LOGIN = "login";
    private OnLoginFinishedListener listener;

    @Override
    public void login(LoginModel loginModel, OnLoginFinishedListener listener) {
        boolean error = false;
        this.listener = listener;
        if (TextUtils.isEmpty(loginModel.getEmail())) {
            listener.onUsernameError();
            error = true;
        }
        if (TextUtils.isEmpty(loginModel.getPassword())) {
            listener.onPasswordError();
            error = true;
        }
        if (!error) {
            RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
            api.login(loginModel, this);
        }
    }

//    @Override
//    public void success(User user, Response response) {
//        Log.d("tokenPravi", user.getToken());
//        listener.onSuccess(user);
//    }


    @Override
    public void success(Response<LoginResponse> loginResponseResponse, retrofit.client.Response response) {
        listener.onSuccess(loginResponseResponse);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d(LOGIN, error.toString());
    }

    @Override
    public void facebookLogin(User user, OnLoginFinishedListener listener) {
        this.listener = listener;
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        api.facebookLogin(user, this);
    }

    @Override
    public void twitterLogin(User user, OnLoginFinishedListener listener) {
        this.listener = listener;
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        api.twitterLogin(user, this);
    }

}

