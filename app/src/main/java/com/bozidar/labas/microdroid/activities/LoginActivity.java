package com.bozidar.labas.microdroid.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.fragments.SocialLoginFragment;
import com.bozidar.labas.microdroid.mvp.presenter.LoginPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.LoginPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.LoginView;
import com.bozidar.labas.microdroid.utils.IntentUtil;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.model.User;
import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnRequestSocialPersonCompleteListener;
import com.github.gorbin.asne.core.persons.SocialPerson;

import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends MicroActivity implements LoginView, SocialLoginFragment.SocialLoginListener, OnRequestSocialPersonCompleteListener {

    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";

    @InjectView(R.id.etEmail)
    TextView etEmail;

    @InjectView(R.id.etPassword)
    TextView etPassword;

    LoginPresenter presenter;

    private String loginType;

    private int socialNetworkId;

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
        loginType = "";
        socialNetworkId = 0;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new SocialLoginFragment())
                .commit();

        presenter = new LoginPresenterImpl(this);
    }


    @OnClick(R.id.btn_sign_up)
    public void goToRegistrationActivity(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void navigateToHome(User user) {
        IntentUtil.startMainActivity(this, user, socialNetworkId, "RSCLogin");
    }

    @Override
    public void setWrongAuthentication() {

    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestSocialPersonSuccess(int socialNetworkId, SocialPerson socialPerson) {

        String socialPersonString = socialPerson.toString();
        String infoString = socialPersonString.substring(socialPersonString.indexOf("{")+1, socialPersonString.lastIndexOf("}"));
        Log.d("TEST", infoString.replace(", ", "\n"));

        User user = new User();
        user.setId(socialPerson.id);
        user.setName(socialPerson.name);
        user.setAvatar(socialPerson.avatarURL);
        user.setEmail(socialPerson.email);
//        Log.d("email", socialPerson.email);
        user.setType(2);
        this.socialNetworkId = socialNetworkId;

        switch (loginType){
            case "facebook":
                presenter.facebookLogin(user);
                break;
            case "twitter":
                presenter.twitterLogin(user);
                break;
        }
    }

    @Override
    public void onError(int socialNetworkID, String requestID, String errorMessage, Object data) {
        Log.d("social login error", errorMessage);
    }


    @Override
    public void loginToFacebook(int networkId, SocialNetworkManager mSocialNetworkManager) {

        SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
        socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
        socialNetwork.requestCurrentPerson();
        Log.d("asasas", "face");
        loginType = "facebook";

    }

    @Override
    public void loginToTwitter(int networkId, SocialNetworkManager mSocialNetworkManager) {
        SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
        socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
        socialNetwork.requestCurrentPerson();
        loginType = "twitter";
    }
}