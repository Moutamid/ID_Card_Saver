package com.idcard.saverpro.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idcard.saverpro.Model.LocalBackup;
import com.idcard.saverpro.Model.RemoteBackup;
import com.idcard.saverpro.Permissions;
import com.idcard.saverpro.R;
import com.idcard.saverpro.Utils.dataa;
import com.idcard.saverpro.database.DatabaseHelper;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.io.File;

public class SettingActivity extends AppCompatActivity {

    ImageView ivBack;
    LinearLayout relRateUs, relShare, relPrivacy, relSuggestion, relMore, relbackup, relrestore;
    public static final int REQUEST_CODE_SIGN_IN = 0;
    public static final int REQUEST_CODE_OPENING = 1;
    public static final int REQUEST_CODE_CREATION = 2;
    public static final int REQUEST_CODE_PERMISSIONS = 2;
    private boolean isBackup = true;
    private SettingActivity activity;
    private LocalBackup localBackup;
    private RemoteBackup remoteBackup;
    SharedPreferences preferences;
    String[] separated;
    String pass;

    String bool = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        localBackup = new LocalBackup(this);
//        remoteBackup = new RemoteBackup(this);
        init();

        TextView deviceIdTv = findViewById(R.id.deviceIdTextView);
        TextView currentlyUsedIdTv = findViewById(R.id.currentIdTextView);

//        deviceIdTv.setText("Copy Your Device ID (" + dataa.getDeviceID() + ")");
        /*currentlyUsedIdTv.setText("Copy Currently Used ID ("
                + Stash.getString(dataa.DEVICE_ID, dataa.getDeviceID()) + ")");
        */

        LinearLayout id = findViewById(R.id.copyCurrentIdLayout);
//        LinearLayout dvid = findViewById(R.id.copyDeviceIdLayout);
//
        id.setOnClickListener(v -> {
            bool = Stash.getString(dataa.constan, preferences.getString("MOBILE_NUMBER", ""));
//            currentlyUsedIdTv.setText(bool);
            ShowPhoneDialog(bool);

//            if (Build.VERSION.SDK_INT >= 21) {
//                @SuppressLint("WrongConstant") KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
//                if (keyguardManager.isKeyguardSecure()) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("Please Enter Your Lock Screen Password/Finger Print/Face Lock\n\n");
////                        sb.append(getString(R.string.secutiry_layout));
//                    startActivityForResult(keyguardManager.createConfirmDeviceCredentialIntent("Id Card Wallet", sb.toString()), SYSTEM_LOCK);
//                    return;
//                }
////                copyToClipboard();
//            }
        });
//
//        dvid.setOnClickListener(v -> {
//            bool = dataa.getModel();
//            if (Build.VERSION.SDK_INT >= 21) {
//                @SuppressLint("WrongConstant") KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
//                if (keyguardManager.isKeyguardSecure()) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("Please Enter Your Lock Screen Password/Finger Print/Face Lock\n\n");
////                        sb.append(getString(R.string.secutiry_layout));
//                    startActivityForResult(keyguardManager.createConfirmDeviceCredentialIntent("Id Card Wallet", sb.toString()), SYSTEM_LOCK);
//                    return;
//                }
//                copyToClipboard();
//            }
//        });


        ivBack.setOnClickListener(view -> finish());
//        relbackup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SettingActivity.this, "Data already backed up!", Toast.LENGTH_SHORT).show();
//
//                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    if (Environment.isExternalStorageManager()) {
//                        String outFileName = Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + File.separator;
//                        Log.d("outFileName", outFileName);
//                        localBackup.performBackup(db, outFileName);
//                        // If you don't have access, launch a new activity to show the user the system's dialog
//                        // to allow access to the external storage
//                    } else {
//                        Intent intent = new Intent();
//                        intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                        Uri uri = Uri.fromParts("package", SettingActivity.this.getPackageName(), null);
//                        intent.setData(uri);
//                        startActivity(intent);
//                    }
//                }*/
//
////                isBackup = true;
////                remoteBackup.connectToDrive(isBackup);
//            }
//        });
//        Dialog progress = new Dialog(SettingActivity.this);
//        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        progress.setContentView(R.layout.dialog_device_id);
//        progress.setCancelable(true);
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.copyFrom(progress.getWindow().getAttributes());
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        TextView stre = progress.findViewById(R.id.ivi);
//        EditText viewBy = progress.findViewById(R.id.tvv);
//
//        stre.setOnClickListener(v -> {
//            String iid = viewBy.getText().toString();
//
//            if (iid.isEmpty()) {
//                return;
//            }
//
//            Stash.put(dataa.constan, iid);
//
//        });
//
//        progress.findViewById(R.id.image).setOnClickListener(v -> {
//            Stash.put(dataa.constan, dataa.getModel());
//        });
//
//        relrestore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                localBackup.performRestore(db);
//                progress.show();
//                progress.getWindow().setAttributes(layoutParams);
//            }
//        });
        relRateUs.setOnClickListener(view -> {
            try {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
                startActivity(intent1);
            } catch (Exception ex) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                startActivity(intent1);
            }
        });
        relShare.setOnClickListener(view -> {
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");
            intentShare.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            intentShare.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
            startActivity(intentShare);
        });
        relPrivacy.setOnClickListener(view -> {
            popUp();

//            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("YOUR_PRIVACY_POLICY_URL"));//replace with your privacy policy url
//            startActivity(intent1);
        });

        relSuggestion.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] recipients = {"jaibhavaniservices1@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
//            intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
//            intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
//            intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));

//            try {
//                Intent intent = new Intent("android.intent.action.SEND");
//                intent.putExtra("android.intent.extra.EMAIL", new String[]{"jaibhavaniservices1@gmail.com"});
//                intent.setType("text/plain");
//                ResolveInfo resolveInfo = null;
//                for (ResolveInfo resolveInfo2 : getPackageManager().queryIntentActivities(intent, 0)) {
//                    if (resolveInfo2.activityInfo.packageName.endsWith(".gm") || resolveInfo2.activityInfo.name.toLowerCase().contains("gmail")) {
//                        resolveInfo = resolveInfo2;
//                    }
//                }
//                if (resolveInfo != null) {
//                    intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
//                }
//                startActivity(intent);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });
        relMore.setOnClickListener(view -> {
           // popUpMore();
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/dev?id=4756135779194388058")));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    public static int SYSTEM_LOCK = 35;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SYSTEM_LOCK) {
//            if (resultCode == -1) {
//                copyToClipboard();
//            }
//            if (resultCode == 0) {
////                finish();
//            }
//        }
//    }
//
//    private void copyToClipboard(){
//        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//        ClipData clip = ClipData.newPlainText("Copied Text", bool);
//        clipboard.setPrimaryClip(clip);
//        Toast.makeText(activity, "Copied!", Toast.LENGTH_SHORT).show();
//    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        relRateUs = findViewById(R.id.relRateUs);
        relShare = findViewById(R.id.relShare);
        relPrivacy = findViewById(R.id.relPrivacy);
        relSuggestion = findViewById(R.id.relSuggestion);
        relMore = findViewById(R.id.relMore);
        relrestore = findViewById(R.id.relrestore);
        relbackup = findViewById(R.id.relbackup);
    }

    public void performRestore(final DatabaseHelper db) {

        Permissions.verifyStoragePermissions(activity);

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + activity.getResources().getString(R.string.app_name));
        if (folder.exists()) {

            final File[] files = folder.listFiles();

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.select_dialog_item);
            for (File file : files)
                arrayAdapter.add(file.getName());

            AlertDialog.Builder builderSingle = new AlertDialog.Builder(activity);
            builderSingle.setTitle("Restore:");
            builderSingle.setNegativeButton(
                    "cancel",
                    (dialog, which) -> dialog.dismiss());
            builderSingle.setAdapter(
                    arrayAdapter,
                    (dialog, which) -> {
                        try {
                            db.importDB(files[which].getPath());
                        } catch (Exception e) {
                            Toast.makeText(activity, "Unable to restore. Retry", Toast.LENGTH_SHORT).show();
                        }
                    });
            builderSingle.show();
        } else
            Toast.makeText(activity, "Backup folder not present.\nDo a backup before a restore!", Toast.LENGTH_SHORT).show();
    }

    public void popUp() {

        PopupDialog.getInstance(this)
                .setStyle(Styles.SUCCESS)
                .setHeading("Privacy Policy")
                .setDescription("We are committed to maintaining the accuracy, confidentiality, and security of your personally identifiable information (\"Personal Information\"). As part of this commitment, our privacy policy governs our actions as they relate to the collection, use and disclosure of Personal Information.")
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onPositiveClicked(Dialog dialog) {
                        super.onPositiveClicked(dialog);
                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                    }

                    @Override
                    public void onDismissClicked(Dialog dialog) {
                        super.onDismissClicked(dialog);
                    }
                });
    }

    public void popUpMore() {

        PopupDialog.getInstance(this)
                .setStyle(Styles.ALERT)
                .setHeading("More Apps")
                .setDescription("Our team working on this Module. Waiting For More Apps")
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onPositiveClicked(Dialog dialog) {
                        super.onPositiveClicked(dialog);
                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                    }

                    @Override
                    public void onDismissClicked(Dialog dialog) {
                        super.onDismissClicked(dialog);
                    }
                });
    }
    private void ShowPhoneDialog(String bool){
        String currentString = bool;
        String[] separated = currentString.split("@!");
//        separated[0];
//        separated[1];
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingActivity.this, R.style.AlertDialogBox);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.dialog_password, null);
        mBuilder.setView(convertView); // setView
        TextView password=convertView.findViewById(R.id.password);
        EditText tvv=convertView.findViewById(R.id.tvv);
        TextView ivi=convertView.findViewById(R.id.ivi);
        TextView no=convertView.findViewById(R.id.no);
        password.setText(separated[1]);
        AlertDialog btn = mBuilder.create();
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.dismiss();
            }
        });
        ivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 pass=tvv.getText().toString();
                if(pass.isEmpty() && pass.length()<6){
                    Toast.makeText(SettingActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();

                }
                else{
//                    Log.d("ahaha",Stash.getString(dataa.constan,separated[0]+"@!"+separated[1]));
                    Log.d("ahaha",  dataa.instan().getKey());
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference()
                            .child("IdCardApp")
                            .child(separated[0]+"@!"+pass);
                    DatabaseReference dbpre = FirebaseDatabase.getInstance().getReference()
                            .child("IdCardApp")
                            .child(separated[0]+"@!"+separated[1]);
                    db.keepSynced(true);
                    copyRecord(dbpre,db);

                    btn.dismiss();
                }
            }
        });
        btn.show();

    }
    private void copyRecord(DatabaseReference fromPath, final DatabaseReference toPath) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            Log.d("TAG", "Success!");
                            Stash.put(dataa.constan, separated[0]+"@!"+pass);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("MOBILE_NUMBER",separated[0]+"@!"+pass);
                            editor.apply();                        }
                        else {
                            Log.d("TAG", "Copy failed!");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        fromPath.addListenerForSingleValueEvent(valueEventListener);
    }


}