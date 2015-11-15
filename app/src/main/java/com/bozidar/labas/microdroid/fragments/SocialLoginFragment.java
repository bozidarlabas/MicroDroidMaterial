package com.bozidar.labas.microdroid.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.LoginActivity;
import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SocialLoginFragment extends Fragment implements SocialNetworkManager.OnInitializationCompleteListener, OnLoginCompleteListener {
    public static SocialNetworkManager mSocialNetworkManager;
    /**
     * SocialNetwork Ids in ASNE:
     * 1 - Twitter
     * 2 - LinkedIn
     * 3 - Google Plus
     * 4 - Facebook
     * 5 - Vkontakte
     * 6 - Odnoklassniki
     * 7 - Instagram
     */
    private ImageView facebook;
    private ImageView twitter;
    private String loginType;

    SocialLoginListener listener;

    public SocialLoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginType = "";
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        //((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        // init buttons and set Listener
        facebook = (ImageView) rootView.findViewById(R.id.facebook);
        facebook.setOnClickListener(facebookloginClick);
        twitter = (ImageView) rootView.findViewById(R.id.twitter);
        twitter.setOnClickListener(twitterloginClick);

        //Get Keys for initiate SocialNetworks
        String TWITTER_CONSUMER_KEY = getActivity().getString(R.string.twitter_consumer_key);
        String TWITTER_CONSUMER_SECRET = getActivity().getString(R.string.twitter_consumer_secret);
        String TWITTER_CALLBACK_URL = "oauth://ASNE";


        //Chose permissions
        ArrayList<String> fbScope = new ArrayList<String>();
        fbScope.addAll(Arrays.asList("public_profile, email, user_friends, user_location, user_birthday"));


        //Use manager to manage SocialNetworks
        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().findFragmentByTag(LoginActivity.SOCIAL_NETWORK_TAG);

        //Check if manager exist
        if (mSocialNetworkManager == null) {
            mSocialNetworkManager = new SocialNetworkManager();

            //Init and add to manager FacebookSocialNetwork
            FacebookSocialNetwork fbNetwork = new FacebookSocialNetwork(this, fbScope);
            mSocialNetworkManager.addSocialNetwork(fbNetwork);

            //Init and add to manager TwitterSocialNetwork
            TwitterSocialNetwork twNetwork = new TwitterSocialNetwork(this, TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, TWITTER_CALLBACK_URL);
            mSocialNetworkManager.addSocialNetwork(twNetwork);


            //Initiate every network from mSocialNetworkManager
            getFragmentManager().beginTransaction().add(mSocialNetworkManager, LoginActivity.SOCIAL_NETWORK_TAG).commit();
            mSocialNetworkManager.setOnInitializationCompleteListener(this);
        } else {
            //if manager exist - get and setup login only for initialized SocialNetworks
            if (!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                for (SocialNetwork socialNetwork : socialNetworks) {
                    socialNetwork.setOnLoginCompleteListener(this);
                    initSocialNetwork(socialNetwork);
                }
            }
        }
        return rootView;
    }

    private void initSocialNetwork(SocialNetwork socialNetwork) {
        if (socialNetwork.isConnected()) {
            switch (socialNetwork.getID()) {
                case FacebookSocialNetwork.ID:
                    //facebook.setText("Show Facebook profile");
                    break;
                case TwitterSocialNetwork.ID:
                    //twitter.setText("Show Twitter profile");
                    break;

            }
        }
    }

    @Override
    public void onSocialNetworkManagerInitialized() {
        //when init SocialNetworks - get and setup login only for initialized SocialNetworks
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
            initSocialNetwork(socialNetwork);
        }
    }

    //Login listener

    private View.OnClickListener facebookloginClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int networkId = 0;
            loginType = "facebook";
            networkId = FacebookSocialNetwork.ID;

            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
            if (!socialNetwork.isConnected()) {
                if (networkId != 0) {
                    socialNetwork.requestLogin();
                    //MainActivity.showProgress("Loading social person");
                } else {
                    Toast.makeText(getActivity(), "Wrong networkId", Toast.LENGTH_LONG).show();
                }
            } else {
                //listener.loginToFacebook(socialNetwork.getID());
            }
        }
    };

    private View.OnClickListener twitterloginClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int networkId = 0;
            loginType = "twitter";
            networkId = TwitterSocialNetwork.ID;

            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
            if (!socialNetwork.isConnected()) {
                if (networkId != 0) {
                    socialNetwork.requestLogin();
                    //MainActivity.showProgress("Loading social person");
                } else {
                    Toast.makeText(getActivity(), "Wrong networkId", Toast.LENGTH_LONG).show();
                }
            } else {
                //listener.loginToTwitter(socialNetwork.getID());
            }
        }
    };

    @Override
    public void onLoginSuccess(int networkId) {
        switch (loginType){
            case "facebook":
                listener.loginToFacebook(networkId, mSocialNetworkManager);
                break;
            case "twitter":

                listener.loginToTwitter(networkId, mSocialNetworkManager);
                break;
        }
    }

    @Override
    public void onError(int networkId, String requestID, String errorMessage, Object data) {
        //MainActivity.hideProgress();
        Toast.makeText(getActivity(), "ERROR: " + errorMessage, Toast.LENGTH_LONG).show();
    }



    public interface SocialLoginListener {
        void loginToFacebook(int networkId, SocialNetworkManager mSocialNetworkManager);
        void loginToTwitter(int networkId, SocialNetworkManager mSocialNetworkManager);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (SocialLoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement Fragment Two.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
