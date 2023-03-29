package com.idcard.saverpro.Activity;

import static com.idcard.saverpro.Activity.adap.*;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.idcard.saverpro.Adapter.CustomAdapter;
import com.idcard.saverpro.Adapter.MainDataAdapter;
import com.idcard.saverpro.Adapter.UserCatDataAdapter;
import com.idcard.saverpro.Model.Dataa;
import com.idcard.saverpro.Model.Value;
import com.idcard.saverpro.Model.CategoryRowModel;
import com.idcard.saverpro.Model.UserCat;
import com.idcard.saverpro.R;
import com.idcard.saverpro.Utils.Adsload;
import com.idcard.saverpro.Utils.dataa;
import com.idcard.saverpro.Utils.StoredPreferencesValue;
import com.idcard.saverpro.database.DatabaseHelper;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static RecyclerView rvaadapter, aadapter;
    public static ImageView invboool, adboool, rebooladaop;
    public static TextView tvadapteebool;
    public static ArrayList<CategoryRowModel> strr;
    public static DatabaseHelper recyycl;
    public static MainDataAdapter maain;
    public static boolean isVissable = true;
    public static Adsload immi;
    public static List<Value> strr1 = new ArrayList<>();
    public static ArrayList<UserCat> strr2 = new ArrayList<>();

    public static CustomAdapter iswwork;
    public static UserCatDataAdapter doocat;
    public static Value vallue;
    public static FrameLayout draame, drrame2;
    public static int vieewClick = 0;
    public static ImageView seleectedImage;
    boolean doubleBackToExitPressedOnce = false;
    String[] strArr = new String[]{"Aadhar\nCard", "Pan\nCard", "Voting\nCard",
            "Driving\nLicense", "Passport", "Business\nCard", "RC\nBook",
            "Office ID\ncard", "Debit\nCard", "Credit\nCard", "Transport\nCard",
            "Insurance\nCard", "Shopping\nCards", "My\nCards", "Other\nCards"};
    int[] iArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataa.checkApp(this);
        popUp();
        init();
        setUpData();
        str2.addAll(recycl.getcategory1());
        docat = new UserCatDataAdapter(MainActivity.this, str2);
        view.setAdapter(docat);
        view.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        invbool.setOnClickListener(view -> {

            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        });
        rebooladap.setOnClickListener(view -> {
            Toast.makeText(this, "Refreshed!", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
        });
        tvadaptebool.setOnClickListener(view -> {
            Toast.makeText(this, "Refreshed!", Toast.LENGTH_SHORT).show();

//            startActivity(new Intent(MainActivity.this, MainActivity.class));
        });
        adbool.setOnClickListener(view -> {
            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogBox);
            LayoutInflater inflater = getLayoutInflater();
            View convertView = inflater.inflate(R.layout.dialogue_category, null);
            RecyclerView image2 = convertView.findViewById(R.id.recylcer);
            EditText input = convertView.findViewById(R.id.editname);
            LinearLayout itemspinner = (LinearLayout) convertView.findViewById(R.id.itemspinner);
            selectedImage = (ImageView) convertView.findViewById(R.id.selectedImage);
            TextView no = convertView.findViewById(R.id.no);
            TextView save = convertView.findViewById(R.id.yes);
            image2.setVisibility(View.GONE);
            itemspinner.setVisibility(View.VISIBLE);
            itemspinner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image2.setVisibility(View.VISIBLE);
                    itemspinner.setVisibility(View.VISIBLE);

                }
            });
            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image2.setVisibility(View.VISIBLE);
                    itemspinner.setVisibility(View.VISIBLE);
                }
            });

            if (anInt == 0) {
                anInt = 1;
                value = new Value();
                value.del(R.drawable.p1);
                str1.add(value);

//                catImages = new Value();
//                catImages.setImageType(R.drawable.p2);
//                catImagesList.add(catImages);
                value = new Value();
                value.del(R.drawable.p3);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a7);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a8);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.p4);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.p5);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.p6);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a7);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a8);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a9);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a10);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a11);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.a12);
                str1.add(value);

                value = new Value();
                value.del(R.drawable.p2);
                str1.add(value);

            }

            iswork = new CustomAdapter(MainActivity.this, str1);
            image2.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            image2.setHasFixedSize(true);
            image2.setAdapter(iswork);
            mBuilder.setView(convertView); // setView
            AlertDialog btn = mBuilder.create();
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (input.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Please Enter Category Name", Toast.LENGTH_SHORT).show();

                    } else {
                        /*Bitmap bitmap;
                        bitmap = ((BitmapDrawable) selectedImage.getDrawable()).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] bitMapData = stream.toByteArray();
                        dbhelper = new DatabaseHelper(MainActivity.this);
                        dbhelper.addcategory1(input.getText().toString(), bitMapData);*/

                        String name = input.getText().toString();
                        int image = str1.get(Stash.getInt(dataa.lat, 0)).getImageType();

                        Dataa dataa = new Dataa();
                        dataa.name = name;
                        dataa.image = image;
                        dataa.total_count = 0;
                        dataa.push_key = com.idcard.saverpro.Utils.dataa.instan().push().getKey();

                        com.idcard.saverpro.Utils.dataa.instan().child(com.idcard.saverpro.Utils.dataa.car)
                                .child(dataa.push_key)
                                .setValue(dataa);

                        btn.dismiss();
                        Toast.makeText(MainActivity.this, "Category saved!", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this, MainActivity.class));
//                        finish();
                    }
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn.dismiss();
                }
            });
            btn.show();
        });

        dataa.instan().child(dataa.car)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            str3.clear();

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                str3.add(dataSnapshot.getValue(Dataa.class));
                            }

                            main = new MainDataAdapter(MainActivity.this, str3);
                            rvadapter.setAdapter(main);
                            rvadapter.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

                        } else {
                            makeinit();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void makeinit() {
        Dataa dataa1 = new Dataa();
        dataa1.name = "Aadhar\nCard";
        dataa1.image = R.drawable.icon_aadhar;
        dataa1.total_count = 0;
        dataa1.push_key = dataa.instan().push().getKey();

        Dataa dataa2 = new Dataa();
        dataa2.name = "Pan\nCard";
        dataa2.image = R.drawable.icon_pan_card;
        dataa2.total_count = 0;
        dataa2.push_key = dataa.instan().push().getKey();

        Dataa dataa3 = new Dataa();
        dataa3.name = "Voting\nCard";
        dataa3.image = R.drawable.icon_voting_card;
        dataa3.total_count = 0;
        dataa3.push_key = dataa.instan().push().getKey();

        Dataa dataa4 = new Dataa();
        dataa4.name = "Driving\nLicense";
        dataa4.image = R.drawable.icon_driving_lice;
        dataa4.total_count = 0;
        dataa4.push_key = dataa.instan().push().getKey();

        Dataa dataa5 = new Dataa();
        dataa5.name = "Passport";
        dataa5.image = R.drawable.icon_passport;
        dataa5.total_count = 0;
        dataa5.push_key = dataa.instan().push().getKey();

        Dataa dataa6 = new Dataa();
        dataa6.name = "Business\nCard";
        dataa6.image = R.drawable.icon_bu_card;
        dataa6.total_count = 0;
        dataa6.push_key = dataa.instan().push().getKey();

        Dataa dataa7 = new Dataa();
        dataa7.name = "RC\nBook";
        dataa7.image = R.drawable.icon_rc_book;
        dataa7.total_count = 0;
        dataa7.push_key = dataa.instan().push().getKey();

        Dataa dataa8 = new Dataa();
        dataa8.name = "Office ID\ncard";
        dataa8.image = R.drawable.icon_office_id;
        dataa8.total_count = 0;
        dataa8.push_key = dataa.instan().push().getKey();

        Dataa dataa9 = new Dataa();
        dataa9.name = "Debit\nCard";
        dataa9.image = R.drawable.icon_debit_card;
        dataa9.total_count = 0;
        dataa9.push_key = dataa.instan().push().getKey();

        Dataa dataa10 = new Dataa();
        dataa10.name = "Credit\nCard";
        dataa10.image = R.drawable.icon_credit_card;
        dataa10.total_count = 0;
        dataa10.push_key = dataa.instan().push().getKey();

        Dataa dataa11 = new Dataa();
        dataa11.name = "Transport\nCard";
        dataa11.image = R.drawable.icon_transport_card;
        dataa11.total_count = 0;
        dataa11.push_key = dataa.instan().push().getKey();

        Dataa dataa12 = new Dataa();
        dataa12.name = "Insurance\nCard";
        dataa12.image = R.drawable.icon_insurance_card;
        dataa12.total_count = 0;
        dataa12.push_key = dataa.instan().push().getKey();

        Dataa dataa13 = new Dataa();
        dataa13.name = "Shopping\nCards";
        dataa13.image = R.drawable.icon_shopping_card;
        dataa13.total_count = 0;
        dataa13.push_key = dataa.instan().push().getKey();

        Dataa dataa14 = new Dataa();
        dataa14.name = "My\nCards";
        dataa14.image = R.drawable.ic_baseline_how_to_vote_24;
        dataa14.total_count = 0;
        dataa14.push_key = dataa.instan().push().getKey();

        Dataa dataa15 = new Dataa();
        dataa15.name = "Other\nCards";
        dataa15.image = R.drawable.icon_other_card;
        dataa15.total_count = 0;
        dataa15.push_key = dataa.instan().push().getKey();

        dataa.instan().child(dataa.car)
                .child(dataa1.push_key)
                .setValue(dataa1);

        dataa.instan().child(dataa.car)
                .child(dataa2.push_key)
                .setValue(dataa2);

        dataa.instan().child(dataa.car)
                .child(dataa3.push_key)
                .setValue(dataa3);

        dataa.instan().child(dataa.car)
                .child(dataa4.push_key)
                .setValue(dataa4);

        dataa.instan().child(dataa.car)
                .child(dataa5.push_key)
                .setValue(dataa5);
        dataa.instan().child(dataa.car)
                .child(dataa6.push_key)
                .setValue(dataa6);
        dataa.instan().child(dataa.car)
                .child(dataa7.push_key)
                .setValue(dataa7);
        dataa.instan().child(dataa.car)
                .child(dataa8.push_key)
                .setValue(dataa8);
        dataa.instan().child(dataa.car)
                .child(dataa9.push_key)
                .setValue(dataa9);

        dataa.instan().child(dataa.car)
                .child(dataa10.push_key)
                .setValue(dataa10);

        dataa.instan().child(dataa.car)
                .child(dataa11.push_key)
                .setValue(dataa11);

        dataa.instan().child(dataa.car)
                .child(dataa12.push_key)
                .setValue(dataa12);

        dataa.instan().child(dataa.car)
                .child(dataa13.push_key)
                .setValue(dataa13);

        dataa.instan().child(dataa.car)
                .child(dataa14.push_key)
                .setValue(dataa14);

        dataa.instan().child(dataa.car)
                .child(dataa15.push_key)
                .setValue(dataa15);
    }

    private void mekeinit() {
        Dataa dataa1 = new Dataa();
        dataa1.name = "Aadhar\nCard";
        dataa1.image = R.drawable.icon_aadhar;
        dataa1.total_count = 0;
        dataa1.push_key = dataa.instan().push().getKey();

        Dataa dataa2 = new Dataa();
        dataa2.name = "Pan\nCard";
        dataa2.image = R.drawable.icon_pan_card;
        dataa2.total_count = 0;
        dataa2.push_key = dataa.instan().push().getKey();

        Dataa dataa3 = new Dataa();
        dataa3.name = "Voting\nCard";
        dataa3.image = R.drawable.icon_voting_card;
        dataa3.total_count = 0;
        dataa3.push_key = dataa.instan().push().getKey();

        Dataa dataa4 = new Dataa();
        dataa4.name = "Driving\nLicense";
        dataa4.image = R.drawable.icon_driving_lice;
        dataa4.total_count = 0;
        dataa4.push_key = dataa.instan().push().getKey();

        Dataa dataa5 = new Dataa();
        dataa5.name = "Passport";
        dataa5.image = R.drawable.icon_passport;
        dataa5.total_count = 0;
        dataa5.push_key = dataa.instan().push().getKey();

        Dataa dataa6 = new Dataa();
        dataa6.name = "Business\nCard";
        dataa6.image = R.drawable.icon_bu_card;
        dataa6.total_count = 0;
        dataa6.push_key = dataa.instan().push().getKey();

        Dataa dataa7 = new Dataa();
        dataa7.name = "RC\nBook";
        dataa7.image = R.drawable.icon_rc_book;
        dataa7.total_count = 0;
        dataa7.push_key = dataa.instan().push().getKey();

        Dataa dataa8 = new Dataa();
        dataa8.name = "Office ID\ncard";
        dataa8.image = R.drawable.icon_office_id;
        dataa8.total_count = 0;
        dataa8.push_key = dataa.instan().push().getKey();

        Dataa dataa9 = new Dataa();
        dataa9.name = "Debit\nCard";
        dataa9.image = R.drawable.icon_debit_card;
        dataa9.total_count = 0;
        dataa9.push_key = dataa.instan().push().getKey();

        Dataa dataa10 = new Dataa();
        dataa10.name = "Credit\nCard";
        dataa10.image = R.drawable.icon_credit_card;
        dataa10.total_count = 0;
        dataa10.push_key = dataa.instan().push().getKey();

        Dataa dataa11 = new Dataa();
        dataa11.name = "Transport\nCard";
        dataa11.image = R.drawable.icon_transport_card;
        dataa11.total_count = 0;
        dataa11.push_key = dataa.instan().push().getKey();

        Dataa dataa12 = new Dataa();
        dataa12.name = "Insurance\nCard";
        dataa12.image = R.drawable.icon_insurance_card;
        dataa12.total_count = 0;
        dataa12.push_key = dataa.instan().push().getKey();

        Dataa dataa13 = new Dataa();
        dataa13.name = "Shopping\nCards";
        dataa13.image = R.drawable.icon_shopping_card;
        dataa13.total_count = 0;
        dataa13.push_key = dataa.instan().push().getKey();

        Dataa dataa14 = new Dataa();
        dataa14.name = "My\nCards";
        dataa14.image = R.drawable.ic_baseline_how_to_vote_24;
        dataa14.total_count = 0;
        dataa14.push_key = dataa.instan().push().getKey();

        Dataa dataa15 = new Dataa();
        dataa15.name = "Other\nCards";
        dataa15.image = R.drawable.icon_other_card;
        dataa15.total_count = 0;
        dataa15.push_key = dataa.instan().push().getKey();

        dataa.instan().child(dataa.car)
                .child(dataa1.push_key)
                .setValue(dataa1);

        dataa.instan().child(dataa.car)
                .child(dataa2.push_key)
                .setValue(dataa2);

        dataa.instan().child(dataa.car)
                .child(dataa3.push_key)
                .setValue(dataa3);

        dataa.instan().child(dataa.car)
                .child(dataa4.push_key)
                .setValue(dataa4);

        dataa.instan().child(dataa.car)
                .child(dataa5.push_key)
                .setValue(dataa5);
        dataa.instan().child(dataa.car)
                .child(dataa6.push_key)
                .setValue(dataa6);
        dataa.instan().child(dataa.car)
                .child(dataa7.push_key)
                .setValue(dataa7);
        dataa.instan().child(dataa.car)
                .child(dataa8.push_key)
                .setValue(dataa8);
        dataa.instan().child(dataa.car)
                .child(dataa9.push_key)
                .setValue(dataa9);

        dataa.instan().child(dataa.car)
                .child(dataa10.push_key)
                .setValue(dataa10);

        dataa.instan().child(dataa.car)
                .child(dataa11.push_key)
                .setValue(dataa11);

        dataa.instan().child(dataa.car)
                .child(dataa12.push_key)
                .setValue(dataa12);

        dataa.instan().child(dataa.car)
                .child(dataa13.push_key)
                .setValue(dataa13);

        dataa.instan().child(dataa.car)
                .child(dataa14.push_key)
                .setValue(dataa14);

        dataa.instan().child(dataa.car)
                .child(dataa15.push_key)
                .setValue(dataa15);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void setUpData() {
        recycl = new DatabaseHelper(this);
        if (StoredPreferencesValue.isFirstLaunch(this)) {
            firstdefaultinsert();
        }

        str = new ArrayList();
        str.addAll(recycl.getcategory());
        /*mainDataAdapter = new MainDataAdapter(MainActivity.this, catList);
        rvMainlist.setAdapter(mainDataAdapter);
        rvMainlist.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));*/
    }

    public void firstdefaultinsert() {
        String[] strArr = new String[]{"Aadhar\nCard", "Pan\nCard", "Voting\nCard",
                "Driving\nLicense", "Passport", "Business\nCard", "RC\nBook",
                "Office ID\ncard", "Debit\nCard", "Credit\nCard", "Transport\nCard",
                "Insurance\nCard", "Shopping\nCards", "My\nCards", "Other\nCards"};
        int[] iArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        if (recycl != null) {
            for (int i = 0; i < strArr.length; i++) {
                recycl.addcategory(strArr[i], iArr[i]);
            }
        }
        StoredPreferencesValue.setFirstLaunch(this, false);
    }

    public void init() {
        rvadapter = findViewById(R.id.rvMainlist);
        view = findViewById(R.id.rvuserlist);
        invbool = findViewById(R.id.ivSetting);
        drame = findViewById(R.id.frameBanner);
        drame2 = findViewById(R.id.frameBanner1);
        adbool = findViewById(R.id.addnew);
        rebooladap = findViewById(R.id.refresh);
        tvadaptebool = findViewById(R.id.tvAppNAme);
        imi = new Adsload(MainActivity.this);
        imi.loadBanner(MainActivity.this, drame);
        imi.loadBanner1(MainActivity.this, drame2);
    }

    public void popUp() {

        PopupDialog.getInstance(this)
                .setStyle(Styles.ALERT)
                .setHeading("Data Safety")
                .setDescription("Your ID card information is not stored on our server." + " It is saved on your local mobile device and is completely safe and secure."
                        +  "\n"  + "\n"  + " If you have any queries about your Data or Card " + "\n"  + "\n" + "Feel free to contact us.")
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
}