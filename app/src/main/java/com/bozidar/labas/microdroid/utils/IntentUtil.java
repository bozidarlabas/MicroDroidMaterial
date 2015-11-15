package com.bozidar.labas.microdroid.utils;

import android.content.Context;
import android.content.Intent;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.MainActivity;
import com.bozidar.microdroid.model.User;


/**
 * Created by Bozidar on 12.11.2015..
 */
public class IntentUtil {

    private static final String KEY_USER = "rsc.bozidar.user";
    private static final String KEY_PROJECT = "rsc.bozidar.project";

    public static void startMainActivity(Context context, User user, int socialNetworkId,  String loginType){
        SharedPrefs prefs = SharedPrefs.getInstance();
        prefs.saveObject(context, context.getResources().getString(R.string.user_data), user);
        prefs.save(context, context.getResources().getString(R.string.login), loginType);

        String jsonUser = Serializator.serialize(user);
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.putExtra(KEY_USER, jsonUser);
        newIntent.putExtra("networkId", socialNetworkId);
        context.startActivity(newIntent);
    }
}
