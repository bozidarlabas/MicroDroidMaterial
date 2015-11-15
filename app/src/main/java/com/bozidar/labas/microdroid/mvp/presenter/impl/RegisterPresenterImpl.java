package com.bozidar.labas.microdroid.mvp.presenter.impl;


import com.bozidar.labas.microdroid.mvp.interactor.RegisterInteractor;
import com.bozidar.labas.microdroid.mvp.interactor.impl.RegisterInteractorImpl;
import com.bozidar.labas.microdroid.mvp.listener.OnregisterFinishedListener;
import com.bozidar.labas.microdroid.mvp.presenter.RegisterPresenter;
import com.bozidar.labas.microdroid.mvp.view.RegisterView;
import com.bozidar.microdroid.model.User;

/**
 * Created by kanta on 23.09.15..
 */
public class RegisterPresenterImpl implements RegisterPresenter, OnregisterFinishedListener {

    RegisterView view;
    RegisterInteractor interactor;

    public RegisterPresenterImpl(RegisterView view){
        this.view = view;
        this.interactor = new RegisterInteractorImpl();
    }

    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onUsernameError() {
        view.setUsernameError();
    }

    @Override
    public void onPasswordError() {
        view.setPasswordError();
    }

    @Override
    public void register(String username, String password, String email, String firstName, String lastName, String city, String birthDate) {
        interactor.register(username, password, email, firstName, lastName, city, birthDate, this);
    }
}
