package com.bozidar.labas.microdroid.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by macbook on 18.10.2015..
 */
public class TvStationModels {

    private static final String NAME = "name";
    private static final String IMG_URL = "img_url";
    private static final String ID = "id";

    @SerializedName(ID)
    private int id;

    @SerializedName(NAME)
    private String name;

    @SerializedName(IMG_URL)
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
