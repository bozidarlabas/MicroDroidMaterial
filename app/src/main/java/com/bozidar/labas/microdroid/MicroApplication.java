package com.bozidar.labas.microdroid;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by macbook on 14.10.2015..
 */
public class MicroApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }

    private void printHashKey(){
        Log.d("kifla", "kifla");
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.bozidar.labas.microdroid",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("MicroKeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

}
