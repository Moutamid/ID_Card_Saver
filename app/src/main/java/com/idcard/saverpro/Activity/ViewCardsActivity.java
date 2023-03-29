package com.idcard.saverpro.Activity;

import static com.bumptech.glide.Glide.with;
import static com.bumptech.glide.load.engine.DiskCacheStrategy.DATA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.idcard.saverpro.Model.CardModel;
import com.idcard.saverpro.R;
import com.idcard.saverpro.Utils.dataa;
import com.idcard.saverpro.databinding.ActivityViewCardsBinding;

public class ViewCardsActivity extends AppCompatActivity {

    private ActivityViewCardsBinding b;
    CardModel cardModel = (CardModel) Stash.getObject(dataa.data, CardModel.class);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityViewCardsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        b.txtTitle.setText(cardModel.name);

        with(getApplicationContext())
                .asBitmap()
                .load(cardModel.img_back)
                /*.apply(new RequestOptions()
                        .placeholder(R.color.lighterGrey)
                        .error(R.color.lighterGrey)
                )*/
                .diskCacheStrategy(DATA)
                .into(b.imgBack);

        with(getApplicationContext())
                .asBitmap()
                .load(cardModel.img_front)
                /*.apply(new RequestOptions()
                        .placeholder(R.color.lighterGrey)
                        .error(R.color.lighterGrey)
                )*/
                .diskCacheStrategy(DATA)
                .into(b.imgFront);

//        b.imgBack.setOnClickListener(v -> {
//            startActivity(new Intent(ViewCardsActivity.this, ViewImageActivity.class));
//        });
//
//        b.imgFront.setOnClickListener(v -> {
//            startActivity(new Intent(ViewCardsActivity.this, ViewImageActivity.class));
//        });
        b.imgBack.setOnClickListener(v -> {
            startActivity(new Intent(ViewCardsActivity.this, ViewImageActivity.class)
                    .putExtra(dataa.IMG_CLICKED, 1));
        });

        b.imgFront.setOnClickListener(v -> {
            startActivity(new Intent(ViewCardsActivity.this, ViewImageActivity.class)
                    .putExtra(dataa.IMG_CLICKED, 0));
        });

        
        b.lDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(ViewCardsActivity.this).inflate(R.layout.bottomsheet_delete_dialog, null);
                BottomSheetDialog image = new BottomSheetDialog(ViewCardsActivity.this, R.style.BottomSheetDialogThemeNew);
                image.setContentView(view);
                image.setCanceledOnTouchOutside(false);
                image.setDismissWithAnimation(true);

                LinearLayout ivCloseDelete = image.findViewById(R.id.ivCloseDelete);
                LinearLayout ivDoneDelete = image.findViewById(R.id.ivDoneDelete);

                ivDoneDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();
                        dataa.instan().child(dataa.car)
                                .child(getIntent().getStringExtra(dataa.date))
                                .child("total_count")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Log.d("TAG", "onDataChange: ");
//                                    if (snapshot.exists()){

                                        int totalCount = snapshot.getValue(Integer.class);
                                        totalCount--;
                                        dataa.instan().child(dataa.car)
                                                .child(getIntent().getStringExtra(dataa.date))
                                                .child("total_count")
                                                .setValue(totalCount);

                                        dataa.instan().child(dataa.taxt)
                                                .child(getIntent().getStringExtra(dataa.date))
                                                .child(cardModel.push_key)
                                                .removeValue();
                                        progressDialog.dismiss();

                                        Toast.makeText(ViewCardsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        finish();
//                                    }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        progressDialog.dismiss();
                                        Toast.makeText(ViewCardsActivity.this, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                ivCloseDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        image.dismiss();
                    }
                });
                image.show();

            }
        });

        b.lEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewCardsActivity.this, AddCardActivity.class)
                        .putExtra(dataa.IS_EDIT, true)
                        .putExtra(dataa.date, getIntent().getStringExtra(dataa.date))
                        .putExtra(dataa.text, cardModel.name));
            }
        });

        b.ivBack.setOnClickListener(v -> {
            finish();
        });

    }
}