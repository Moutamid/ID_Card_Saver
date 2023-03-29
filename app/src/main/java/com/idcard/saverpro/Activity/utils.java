package com.idcard.saverpro.Activity;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.idcard.saverpro.Adapter.CardAdapter;
import com.idcard.saverpro.Model.CardModel;
import com.idcard.saverpro.Utils.Adsload;

import java.util.ArrayList;
import java.util.List;

public class utils {
    public static  ImageView bool3, bool4;
    public static  RecyclerView datap;
    public static  TextView bool5;
    public static int CARD_InSERT_CODE = 2001;
    public static List<CardModel> datai = new ArrayList<>();
    public static  CardAdapter cardLis;
    public static Adsload loadu;
    public static FrameLayout loadn;
}
