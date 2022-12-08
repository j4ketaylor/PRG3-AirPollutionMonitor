package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Emergency extends AppCompatActivity {

    TextView emergency_information_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        emergency_information_text = (TextView) findViewById(R.id.emergency_information);
        emergency_information_text.setText("Here is some emergency information!\n");
    }


}