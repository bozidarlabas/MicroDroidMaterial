package com.bozidar.labas.microdroid.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by macbook on 19.10.2015..
 */
public class MaterialModel {

    private static final String VIDEO = "video";

    private static final String IMAGE = "image";

    private static final String DOCUMENT = "document";

    @SerializedName(VIDEO)
    private ArrayList<String> videoPath;
    @SerializedName(IMAGE)
    private ArrayList<String> imagePath;
    @SerializedName(DOCUMENT)
    private ArrayList<String> documentPath;

    public ArrayList<String> getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(ArrayList<String> videoPath) {
        this.videoPath = videoPath;
    }

    public ArrayList<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(ArrayList<String> imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<String> getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(ArrayList<String> documentPath) {
        this.documentPath = documentPath;
    }
}


//video (min 2), galerija slika (min 5) i dokument (min 1).