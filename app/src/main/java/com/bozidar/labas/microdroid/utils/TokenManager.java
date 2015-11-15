package com.bozidar.labas.microdroid.utils;

import android.content.Context;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.model.User;

import java.util.List;

import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by macbook on 26.10.2015..
 */
public class TokenManager {

    public static String getTokenFromHeader(Response response){
        if(response != null){
            List<Header> headerList = response.getHeaders();
            for(Header header : headerList) {

                String[] headerValue = header.getValue().split(" ");

                if (headerValue[0].equals(Constants.BEARER_TOKEN)){
                    return headerValue[1];
                }
            }
        }
        return "";
    }

    public static void storeNewTokenLocaly(Context context, User user){
        SharedPrefs prefs = SharedPrefs.getInstance();
        prefs.saveObject(context, context.getResources().getString(R.string.user_data), user);
    }
}
