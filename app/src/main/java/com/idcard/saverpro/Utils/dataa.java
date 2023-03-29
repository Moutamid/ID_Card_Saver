package com.idcard.saverpro.Utils;

import android.content.Context;
import android.provider.Settings;

import com.fxn.stash.Stash;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dataa {
    public static final String car = "CATEGORIES";
    public static final String date = "PARAMS";
    public static final String text = "NAME";
    public static final String data = "CURRENT_CARD";
    public static final String taxt = "CARDS";
    public static final String lat = "CURRENT_POSITION";
    public static final String IS_EDIT = "IS_EDIT";
    public static final String constan = "DEVICE_ID";
    public static final String IMG_CLICKED = "IMG_CLICKED";

    public static FirebaseAuth auth() {
        return FirebaseAuth.getInstance();
    }

    public static Context context;

    public static DatabaseReference instan() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference()
                .child("IdCardApp")
                .child(Stash.getString(constan, dataa.getModel()));
        db.keepSynced(true);
        return db;
    }

    public static String getModel() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
