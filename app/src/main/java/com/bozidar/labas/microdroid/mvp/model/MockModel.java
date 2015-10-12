package com.bozidar.labas.microdroid.mvp.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by macbook on 10.10.2015..
 */
public class MockModel {

    // The fields associated to the person
    private final String mName, mPhone, mEmail, mCity, mColor;

    MockModel(String name, String color, String phone, String email, String city) {
        mName = name; mColor = color; mPhone = phone; mEmail = email; mCity = city;
    }

    static ArrayList<MockModel> model;

    public static ArrayList<MockModel> getData(){
        if(model == null){
            model = new ArrayList<>();
            for(int i = 0; i < CONTACTS.length; i++){
                model.add(CONTACTS[i]);
            }
        }
        return model;
    }

    public static final MockModel[] CONTACTS = new MockModel[] {
            new MockModel("John", "#33b5e5", "+01 123456789", "john@example.com", "Venice"),
            new MockModel("Valter", "#ffbb33", "+01 987654321", "valter@example.com", "Bologna"),
            new MockModel("Eadwine", "#ff4444", "+01 123456789", "eadwin@example.com", "Verona"),
            new MockModel("Teddy", "#99cc00", "+01 987654321", "teddy@example.com", "Rome"),
            new MockModel("Ives", "#33b5e5", "+01 11235813", "ives@example.com", "Milan"),
            new MockModel("Alajos", "#ffbb33", "+01 123456789", "alajos@example.com", "Bologna"),
            new MockModel("Gianluca", "#ff4444", "+01 11235813", "me@gian.lu", "Padova"),
            new MockModel("Fane", "#99cc00", "+01 987654321", "fane@example.com", "Venice"),
    };

    // This method allows to get the item associated to a particular id,
    // uniquely generated by the method getId defined below
    public static MockModel getItem(int id) {
        for (MockModel item : model) {
            Log.d("itemm", item.getId() + "");
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    // since mName and mPhone combined are surely unique,
    // we don't need to add another id field
    public int getId() {
        return mName.hashCode() + mPhone.hashCode();
    }

    public static enum Field {
        NAME, COLOR, PHONE, EMAIL, CITY
    }
    public String get(Field f) {
        switch (f) {
            case COLOR: return mColor;
            case PHONE: return mPhone;
            case EMAIL: return mEmail;
            case CITY: return mCity;
            case NAME: default: return mName;
        }
    }

}