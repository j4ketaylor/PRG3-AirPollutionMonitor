package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class AirQualityPlot extends AppCompatActivity {

    Button plot_info_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality_plot);

        plot_info_button = (Button) findViewById(R.id.plot_info);
        plot_info_button.setOnClickListener(v -> {
            Intent intent = new Intent(AirQualityPlot.this, PlotInfo.class);
            startActivity(intent);
        });

    }
}