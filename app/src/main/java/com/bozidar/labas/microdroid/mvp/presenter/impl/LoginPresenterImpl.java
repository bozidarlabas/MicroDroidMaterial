package com.bozidar.labas.microdroid.mvp.presenter.impl;


import com.bozidar.labas.microdroid.mvp.interactor.LoginInteractor;
import com.bozidar.labas.microdroid.mvp.interactor.impl.LoginInteractorImpl;
import com.bozidar.labas.microdroid.mvp.listener.OnLoginFinishedListener;
import com.bozidar.labas.microdroid.mvp.model.LoginModel;
import com.bozidar.labas.microdroid.mvp.model.response.LoginResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.mvp.presenter.LoginPresenter;
import com.bozidar.labas.microdroid.mvp.view.LoginView;
import com.bozidar.microdroid.model.User;

/**
 * Created by kanta on 23.09.15..
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

    LoginView view;
    LoginInteractor interactor;
    User logedUserModel;

    public LoginPresenterImpl(LoginView view){
        logedUserModel = new User();
        this.view = view;
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void login(String username, String password) {
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail(view.getEmail());
        loginModel.setPassword(view.getPassword());
        //TODO add gcm id
        interactor.login(loginModel, this);
    }

    @Override
    public void onSuccess(Response<LoginResponse> loginResponse) {
        LoginResponse response = loginResponse.getData();
        logedUserModel.setToken(response.getToken());
        logedUserModel.setName(response.getName());
        logedUserModel.setEmail(response.getEmail());
        logedUserModel.setAvatar(response.getAvatar());
        view.navigateToHome(logedUserModel);
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
    public void facebookLogin(User user) {
        logedUserModel = user;
        interactor.facebookLogin(user, this);
    }

    @Override
    public void twitterLogin(User user) {
        logedUserModel = user;
        interactor.twitterLogin(user, this);
    }
}
