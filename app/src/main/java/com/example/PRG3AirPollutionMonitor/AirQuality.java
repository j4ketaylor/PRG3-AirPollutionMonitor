package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AirQuality extends AppCompatActivity {

    Button recommended_places_to_live_button, previous_button,live_london_pollution_levels_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality);

        recommended_places_to_live_button = (Button) findViewById(R.id.recommended_places_to_live);
        live_london_pollution_levels_button = (Button) findViewById(R.id.live_london_pollution_levels);
        recommended_places_to_live_button.setOnClickListener(v -> {
            Intent intent = new Intent(AirQuality.this, AirQualityPlot.class);
            startActivity(intent);
        });

       live_london_pollution_levels_button.setOnClickListener(v -> {
            Intent intent = new Intent(AirQuality.this, LiveAirPollution.class);
            startActivity(intent);
        });

    }
}