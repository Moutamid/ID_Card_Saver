package com.idcard.saverpro.Activity;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.hbb20.CountryCodePicker;
import com.idcard.saverpro.R;
import com.idcard.saverpro.Utils.dataa;

public class SplashActivity extends AppCompatActivity {

    LinearLayout lGetStarted;
    public int STORAGE_PERMISSION_REQUEST_CODE = 12;
    InterstitialAd mInterstitialAd;
    public static int SYSTEM_LOCK = 35;
    String Code,Name;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash);
        dataa.checkApp(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Name = preferences.getString("FirstRun", "");
        Log.d("FirstRun",Name);
        InterstitialAd.load(this, getString(R.string.interstitial_ID), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;

            }
        });


        lGetStarted = findViewById(R.id.lGetStarted);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (SDK_INT >= 21) {
                    @SuppressLint("WrongConstant") KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
                    if (keyguardManager.isKeyguardSecure()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Please Enter Your Lock Screen Password/Finger Print/Face Lock\n\n");
//                        sb.append(getString(R.string.secutiry_layout));
                        startActivityForResult(keyguardManager.createConfirmDeviceCredentialIntent("Id Card Wallet", sb.toString()), SYSTEM_LOCK);
                        return;
                    }
                    lGetStarted.setVisibility(View.VISIBLE);
                }
            }
        }, 1000);
//        lGetStarted.setOnClickListener(view -> {
//            dialog.show();
//            dialog.getWindow().setAttributes(layoutParams);
//        });
        lGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(SplashActivity.this);

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            if (isWriteStoragePermissionGranted())
                            {
                                if(!Name.isEmpty())
                                {
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                    finish();
                                }
                                else {
                                    ShowPhoneDialog();
                                }
                            }
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            if (isWriteStoragePermissionGranted())
                            if(!Name.isEmpty())
                            {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }
                            else {
                                ShowPhoneDialog();
                            }

                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null;
                        }
                    });

                } else {
                    if (isWriteStoragePermissionGranted()) {
                        if (!Name.isEmpty()) {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        } else {
                            ShowPhoneDialog();
                        }
                    }
                }
            }
        });


    }
private void ShowPhoneDialog(){
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(SplashActivity.this, R.style.AlertDialogBox);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.dialog_device_id, null);
        mBuilder.setView(convertView); // setView
    CountryCodePicker country=convertView.findViewById(R.id.country);
    EditText phoneno=convertView.findViewById(R.id.phoneno);
    EditText tvv=convertView.findViewById(R.id.tvv);
    TextView ivi=convertView.findViewById(R.id.ivi);
    Code=country.getSelectedCountryCode();
    country.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
        @Override
        public void onCountrySelected() {
             Code=country.getSelectedCountryCode();
        }
    });

        AlertDialog btn = mBuilder.create();
    ivi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String mobileno=phoneno.getText().toString();
            String pass=tvv.getText().toString();
            if(mobileno.isEmpty() || mobileno.length()<=9){
                Toast.makeText(SplashActivity.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
            }  else if(pass.isEmpty() && pass.length()<6){
                Toast.makeText(SplashActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();

            }
            else{
                Stash.put(dataa.constan, "-"+Code+mobileno+"@!"+pass);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("FirstRun","1");
                editor.putString("MOBILE_NUMBER","-"+Code+mobileno+"@!"+pass);
                editor.apply();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                btn.dismiss();
            }
        }
    });
    btn.show();

}
//    private void ShowPhoneDialog() {
//        Dialog dialog = new Dialog(SplashActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_device_id);
//        dialog.setCancelable(true);
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.copyFrom(dialog.getWindow().getAttributes());
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        TextView customBtn = dialog.findViewById(R.id.ivi);
//        EditText customEditText = dialog.findViewById(R.id.tvv);
//
//        customBtn.setOnClickListener(v -> {
//            String id = customEditText.getText().toString();
//
//            if (id.isEmpty()){
//                return;
//            }
//
//            Stash.put(dataa.constan, id);
//
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            finish();
//        });
//
//        dialog.findViewById(R.id.image).setOnClickListener(v -> {
//            Stash.put(dataa.constan, dataa.getModel());
//
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            finish();
//        });
//
//    }

//    private void launchMainActivity() {
//        if (mInterstitialAd != null) {
//            mInterstitialAd.show(SplashActivity.this);
//
//            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    if (isWriteStoragePermissionGranted()) {
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        finish();
//                    }
//                }
//
//                @Override
//                public void onAdFailedToShowFullScreenContent(AdError adError) {
//                    if (isWriteStoragePermissionGranted()) {
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        finish();
//                    }
//                }
//
//                @Override
//                public void onAdShowedFullScreenContent() {
//                    mInterstitialAd = null;
//                }
//            });
//
//        } else {
//            if (isWriteStoragePermissionGranted()) {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }
//    }
public boolean isWriteStoragePermissionGranted() {
    if (Build.VERSION.SDK_INT >= 23) {
        if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_MEDIA_IMAGES},
                    STORAGE_PERMISSION_REQUEST_CODE);
            return true;
        }
        else if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED))
        {
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, STORAGE_PERMISSION_REQUEST_CODE);
            return false;
        }
    } else {
        return true;
    }
}

//    public boolean isWriteStoragePermissionGranted() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            ActivityCompat.requestPermissions(SplashActivity.this,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_MEDIA_IMAGES},
//                    STORAGE_PERMISSION_REQUEST_CODE);
//            return true;
//        }
//        else {
//            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED))
//            {
//                return true;
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, STORAGE_PERMISSION_REQUEST_CODE);
//                return false;
//            }
//        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 12) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                String che = Stash.getString(dataa.constan, "");
                if (che.isEmpty()){
                    ShowPhoneDialog();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("which", "WA");
                    startActivity(intent);
                }

            } else {
                showDailog();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SYSTEM_LOCK) {
            if (resultCode == -1) {
                lGetStarted.setVisibility(View.VISIBLE);
            }
            if (resultCode == 0) {
                finish();
            }
        }
    }

    public void showDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setMessage("You need to give permission to access feature.");
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("GIVE PERMISSION", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        STORAGE_PERMISSION_REQUEST_CODE);
            }
        });
        builder.show();
    }
}