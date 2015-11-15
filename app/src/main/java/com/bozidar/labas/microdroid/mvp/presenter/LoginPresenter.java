package com.bozidar.labas.microdroid.mvp.presenter;

import com.bozidar.microdroid.model.User;

/**
 * Created by kanta on 23.09.15..
 */
public interface LoginPresenter {
    void login(String username, String password);

    void facebookLogin(User user);

    void twitterLogin(User user);
}
