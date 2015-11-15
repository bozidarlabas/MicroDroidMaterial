package com.bozidar.labas.microdroid.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by macbook on 19.10.2015..
 */
public class TvShowModel {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DAY = "day";
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String STATUS = "status";
    private static final String IMAGE_PATH = "path";

    @SerializedName(ID)
    private String id;
    @SerializedName(NAME)
    private String name;
    @SerializedName(DAY)
    private int day;
    @SerializedName(FROM)
    private String from;
    @SerializedName(TO)
    private String to;
    @SerializedName(STATUS)
    private int status;
    @SerializedName(IMAGE_PATH)
    private String imagePath;

    private MaterialModel materials;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    //TODO change status when calling this setter
    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MaterialModel getMaterials() {
        return materials;
    }

    public void setMaterials(MaterialModel materials) {
        this.materials = materials;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
