package com.bozidar.labas.microdroid.mvp.interactor;


import com.bozidar.labas.microdroid.mvp.listener.OnLoginFinishedListener;
import com.bozidar.labas.microdroid.mvp.model.LoginModel;
import com.bozidar.microdroid.model.User;

/**
 * Created by kanta on 23.09.15..
 */
public interface LoginInteractor {
    void login(LoginModel loginModel, OnLoginFinishedListener listener);

    void facebookLogin(User user, OnLoginFinishedListener listener);

    void twitterLogin(User user, OnLoginFinishedListener listener);
}


