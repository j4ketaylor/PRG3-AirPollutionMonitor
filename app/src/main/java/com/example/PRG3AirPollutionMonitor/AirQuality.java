package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AirQuality extends AppCompatActivity {

    Button recommended_places_to_live_button,live_london_pollution_levels_button,news_updates_button,sorted_areas_button, bad_areas_button;
    TextView air_quality_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality);

        air_quality_text_view = (TextView) findViewById(R.id.air_quality_text);

        recommended_places_to_live_button = (Button) findViewById(R.id.recommended_places_to_live);
        live_london_pollution_levels_button = (Button) findViewById(R.id.live_london_pollution_levels);
        news_updates_button = (Button) findViewById(R.id.news_updates);
        sorted_areas_button = (Button) findViewById(R.id.list_of_areas);
        bad_areas_button = (Button) findViewById(R.id.list_of_bad_areas);
        recommended_places_to_live_button.setOnClickListener(v -> {
            Intent intent = new Intent(AirQuality.this, AirQualityPlot.class);
            startActivity(intent);
        });

       live_london_pollution_levels_button.setOnClickListener(v -> {
            Intent intent = new Intent(AirQuality.this, LiveAirPollution.class);
            startActivity(intent);
        });

       news_updates_button.setOnClickListener(v -> {
           Intent intent = new Intent(AirQuality.this,NewsUpdates.class);
           startActivity(intent);
       });

        sorted_areas_button.setOnClickListener(v -> {
            Intent intent = new Intent(AirQuality.this,SortedAreas.class);
            startActivity(intent);
        });

        bad_areas_button.setOnClickListener(v ->  {
            Intent intent = new Intent(AirQuality.this,BadAreas.class);
            startActivity(intent);
        });

    }
}