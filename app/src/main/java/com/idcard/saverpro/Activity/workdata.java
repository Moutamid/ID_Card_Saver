package com.idcard.saverpro.Activity;

import android.app.ProgressDialog;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.idcard.saverpro.Model.CardModel;
import com.idcard.saverpro.databinding.ActivityAddCardBinding;

public class workdata {
    public static ActivityAddCardBinding b;
    public static BottomSheetDialog dialogSelectImage;
    public static LinearLayout lGallery, lCamera;
    public static  final int FRONT_CODE = 9990;
    public static  final int GALLERY_CODE = 9996;
    public static  final int CAMERA_CODE = 9992;
    public static int CURRENT_CODE = 1234;
    public static  final int BACK_CODE = 9997;
    public static  ProgressDialog progressDialog;

    public static CardModel currentModel = new CardModel();
}
