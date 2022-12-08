package com.example.PRG3AirPollutionMonitor;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AirQualityPlot extends AppCompatActivity {

    TextView question3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality_plot);

        question3 = (TextView) findViewById(R.id.question3_id);
        question3.setText("Which area would you like to view history for?\n");

    }
}