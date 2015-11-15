package com.bozidar.labas.microdroid.mvp.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bozidar on 13.11.2015..
 */
public class LoginResponse {

    @SerializedName("email")
    private String email;

    @SerializedName("token")
    private String token;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar")
    private String avatar;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
