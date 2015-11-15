package com.bozidar.labas.microdroid.mvp.presenter;

/**
 * Created by kanta on 23.09.15..
 */
public interface RegisterPresenter {
    void register(String username, String password, String email, String firstName, String lastName, String city, String birthDate);
}
