package com.bozidar.labas.microdroid.mvp.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Bozidar on 13.11.2015..
 */
public class Response<T> implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private T data;

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
}
