package com.bozidar.labas.microdroid.mvp.listener;

import com.bozidar.microdroid.model.User;

/**
 * Created by kanta on 23.09.15..
 */
public interface OnregisterFinishedListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess(User user);
}
