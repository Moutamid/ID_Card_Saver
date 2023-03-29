package com.idcard.saverpro.Activity;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.idcard.saverpro.Adapter.CustomAdapter;
import com.idcard.saverpro.Adapter.MainDataAdapter;
import com.idcard.saverpro.Adapter.UserCatDataAdapter;
import com.idcard.saverpro.Model.Dataa;
import com.idcard.saverpro.Model.Value;
import com.idcard.saverpro.Model.CategoryRowModel;
import com.idcard.saverpro.Model.UserCat;
import com.idcard.saverpro.Utils.Adsload;
import com.idcard.saverpro.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class adap {
    public static RecyclerView rvadapter, view;
    public static ImageView invbool, adbool, rebooladap;
    public static TextView tvadaptebool;
    public static ArrayList<CategoryRowModel> str;
    public static DatabaseHelper recycl;
    public static MainDataAdapter main;
    public static boolean isVisable = true;
    public static Adsload imi;
    public static List<Value> str1 = new ArrayList<>();
    public static ArrayList<UserCat> str2 = new ArrayList<>();

    public static CustomAdapter iswork;
    public static UserCatDataAdapter docat;
    public static Value value;
    public static FrameLayout drame, drame2;
    public static int anInt = 0;
    public static ImageView selectedImage;
    public static ArrayList<Dataa> str3 = new ArrayList<>();

}
