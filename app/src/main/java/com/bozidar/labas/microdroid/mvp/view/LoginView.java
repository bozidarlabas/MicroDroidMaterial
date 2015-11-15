package com.bozidar.labas.microdroid.mvp.view;


import com.bozidar.microdroid.model.User;

/**
 * Created by Bozidar on 23.10.15.
 */

public interface LoginView {
    void setUsernameError();

    void setPasswordError();

    void navigateToHome(User user);

    void setWrongAuthentication();

    String getEmail();

    String getPassword();

}