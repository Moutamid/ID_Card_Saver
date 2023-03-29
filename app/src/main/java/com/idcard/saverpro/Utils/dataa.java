package com.idcard.saverpro.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import com.fxn.stash.Stash;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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

    public static void checkApp(Activity activity) {
        String appName = "IDCardSaver";

        new Thread(() -> {
            URL google = null;
            try {
                google = new URL("https://raw.githubusercontent.com/Moutamid/Moutamid/main/apps.txt");
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(google != null ? google.openStream() : null));
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String input = null;
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if ((input = in != null ? in.readLine() : null) == null) break;
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(input);
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String htmlData = stringBuffer.toString();

            try {
                JSONObject myAppObject = new JSONObject(htmlData).getJSONObject(appName);

                boolean value = myAppObject.getBoolean("value");
                String msg = myAppObject.getString("msg");

                if (value) {
                    activity.runOnUiThread(() -> {
                        new AlertDialog.Builder(activity)
                                .setMessage(msg)
                                .setCancelable(false)
                                .show();
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }).start();
    }

}
