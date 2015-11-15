package com.bozidar.labas.microdroid.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Bozidar on 14.11.2015..
 */
public class LoginModel implements Serializable {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("registrationId")
    private String regId;

    @SerializedName("socialLoginId")
    private String socialLoginId;

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public void setSocialLoginId(String socialLoginId) {
        this.socialLoginId = socialLoginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }
}
