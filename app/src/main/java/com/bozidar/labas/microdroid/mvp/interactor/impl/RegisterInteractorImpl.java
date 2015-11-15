package com.bozidar.labas.microdroid.mvp.interactor.impl;

import android.text.TextUtils;
import android.util.Log;

import com.bozidar.labas.microdroid.mvp.interactor.RegisterInteractor;
import com.bozidar.labas.microdroid.mvp.listener.OnregisterFinishedListener;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.microdroid.network.ServiceFactory;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kanta on 23.09.15..
 */
public class RegisterInteractorImpl implements RegisterInteractor, Callback<String> {
    private OnregisterFinishedListener listener;


    @Override
    public void register(String username, String password, String email, String firstName, String lastName, String city, String birthDate, OnregisterFinishedListener listener) {
        boolean error = false;
        this.listener = listener;
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
        }
        if (!error) {
            Log.d("test", username);
            Log.d("test", password);
            Log.d("test", email);
            Log.d("test", firstName);
            Log.d("test", lastName);
            Log.d("test", city);
            Log.d("test", birthDate);
            RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
            api.register(username, email, password, firstName, lastName, city, birthDate, this);
        }
    }


    @Override
    public void success(String s, Response response) {
        Log.d("success", s);
       // listener.onSuccess();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", error.toString());

    }

}

