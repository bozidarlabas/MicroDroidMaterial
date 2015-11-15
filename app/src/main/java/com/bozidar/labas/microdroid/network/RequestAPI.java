package com.bozidar.labas.microdroid.network;

import com.bozidar.labas.microdroid.mvp.model.LoginModel;
import com.bozidar.labas.microdroid.mvp.model.TvShowModel;
import com.bozidar.labas.microdroid.mvp.model.TvStationModels;
import com.bozidar.labas.microdroid.mvp.model.response.LoginResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.microdroid.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by macbook on 18.10.2015..
 */
public interface RequestAPI {

    @GET("/api/stations")
    void fetchTvStations(
            @Query("token") String token,
            Callback<List<TvStationModels>> response);

    @GET("/tvshows")
    void fetchTvShows(
            Callback<List<TvShowModel>> response);

    @POST("/api/login")
    void login(
            @Body LoginModel loginModel,
            Callback<Response<LoginResponse>> response);

    @POST("/api/login/facebook")
    void facebookLogin(@Body User user, Callback<Response<LoginResponse>> response);

    @POST("/api/login/twitter")
    void twitterLogin(@Body User user, Callback<Response<LoginResponse>> response);

    @FormUrlEncoded
    @POST("/api/logout")
    void logout(
            @Field("token") String token,
            Callback<String> response
    );

    @FormUrlEncoded
    @POST("/api/register")
    void register(
            @Field("name") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("city") String city,
            @Field("birth_date") String birth_date,
            Callback<String> response
    );

}
//api.register(username, email, password, firstName, lastName, city, birthDate, this);