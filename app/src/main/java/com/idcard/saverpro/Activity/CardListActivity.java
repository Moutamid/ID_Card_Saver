package com.idcard.saverpro.Activity;

import static com.idcard.saverpro.Activity.widgets.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.idcard.saverpro.Adapter.CardAdapter;
import com.idcard.saverpro.Model.CardModel;
import com.idcard.saverpro.R;
import com.idcard.saverpro.Utils.Adsload;
import com.idcard.saverpro.Utils.dataa;
import com.idcard.saverpro.Utils.RecyclerItemClick;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends AppCompatActivity implements RecyclerItemClick {

    private ImageView ivBaack, ivDoone;
    private RecyclerView rvvList;
    private TextView txxtTitle;
    public static int CARD_InSERT_CODE = 2001;
    //    String card;
    List<CardModel> cardModeelArrayList = new ArrayList<>();
    CardAdapter cuustomAdapter;
    //    DatabaseHelper databaseHelper;
//    String title_for_toolbar;
    Adsload adload;
    FrameLayout framBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        init();
//        cardgetsets = new ArrayList();
//        databaseHelper = new DatabaseHelper(this);
//        card = getIntent().getStringExtra("card");
//        title_for_toolbar = getIntent().getStringExtra("cardget");
        card.setText(getIntent().getStringExtra(dataa.text));
//        Log.e("setupView: ", "" + card);
//        cardgetsets.addAll(databaseHelper.getcard(card));
        fetch();
        /*if (!databaseHelper.totalcardisAvailable(card)) {
            Intent intent = new Intent(this, SingleCardActivity.class);
            intent.putExtra("categoryId", card);
            intent.putExtra("categoryName", title_for_toolbar);
            intent.putExtra("isforInsert", true);
            startActivityForResult(intent, CARD_InSERT_CODE);
        }*/

        bool1.setOnClickListener(view -> finish());

        /*ivDone.setOnClickListener(view -> {
            Intent intent = new Intent(this, SingleCardActivity.class);
            intent.putExtra("categoryId", card);
            intent.putExtra("categoryName", title_for_toolbar);
            intent.putExtra("isforInsert", true);
            intent.setFlags(67108864);
            startActivityForResult(intent, CARD_InSERT_CODE);
        });*/

        boolIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardListActivity.this, AddCardActivity.class)
                        .putExtra(dataa.IS_EDIT, false)
                        .putExtra(dataa.date, getIntent().getStringExtra(dataa.date))
                        .putExtra(dataa.text, getIntent().getStringExtra(dataa.text)));
            }
        });

    }

    public void init() {
        card = findViewById(R.id.txtTitle);
        bool1 = findViewById(R.id.ivBack);
        datey = findViewById(R.id.rvList);
        boolIv = findViewById(R.id.ivDone);
        load2 = findViewById(R.id.frameBanner);
        load1 = new Adsload(CardListActivity.this);
        load1.loadBanner(CardListActivity.this, load2);

    }

    private void fetch() {
        dataa.instan().child(dataa.taxt)
                .child(getIntent().getStringExtra(dataa.date))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                            datao.clear();

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                datao.add(dataSnapshot.getValue(CardModel.class));

                            }

                            boole = new CardAdapter(CardListActivity.this, datao, CardListActivity.this);
                            datey.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            datey.setAdapter(boole);

                        } else {
                            startActivity(new Intent(CardListActivity.this, AddCardActivity.class)
                                    .putExtra(dataa.IS_EDIT, false)
                                    .putExtra(dataa.date, getIntent().getStringExtra(dataa.date))
                                    .putExtra(dataa.text, getIntent().getStringExtra(dataa.text)));
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @SuppressLint("WrongConstant")
    public void onClick(int i, String str) {
        /*Intent intent = new Intent(this, SingleCardActivity.class);
        intent.putExtra("CardId", String.valueOf(i));
        intent.putExtra("categoryId", String.valueOf(str));
        intent.putExtra("isforInsert", false);
        intent.setFlags(67108864);
        startActivityForResult(intent, 1001);*/
    }

   /* protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == CARD_InSERT_CODE) {
            notifyDatabase();
        }
        if (i == 1001) {
            notifyDatabase();
        }
    }*/

    void notifyDatabase() {
        /*cardModelArrayList.clear();
        cardModelArrayList.addAll(databaseHelper.getcard(card));
        if (cardModelArrayList.size() == 0) {
            finish();
        } else {
            customAdapter.notifyDataSetChanged();
        }*/
    }

}