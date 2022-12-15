package com.example.PRG3AirPollutionMonitor;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class OverdosePopup extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdose_popup);
        //sets size of popup
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.17));
    }
}