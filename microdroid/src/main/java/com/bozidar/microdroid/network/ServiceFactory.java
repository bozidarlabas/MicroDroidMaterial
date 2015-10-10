package com.bozidar.microdroid.network;

import android.util.Log;

import retrofit.RestAdapter;

/**
 * Created by bozidar on 09.09.15..
 */
public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLog((message) -> Log.d("Rest-Adapter", message))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        return restAdapter.create(clazz);
    }
}
