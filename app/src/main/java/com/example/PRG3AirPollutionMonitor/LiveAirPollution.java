package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class LiveAirPollution extends AppCompatActivity {

    TextView location_live_viewer;

    TextView air_pollution_rating_viewer;

    String[] items = {
            "Barking & Dagenham",
            "Bexley",
            "Brent",
            "Brentwood",
            "Bromley",
            "Camden",
            "Castle Point",
            "City of London",
            "Croydon",
            "Dartford",
            "Ealing",
            "Enfield",
            "Greenwich",
            "Hackney",
            "Haringey",
            "Harrow",
            "Havering",
            "Islington",
            "Kingston",
            "Lambeth",
            "Lewisham",
            "Merton",
            "Newham",
            "Redbridge",
            "Reigate & Banstead",
            "Richmond",
            "Sevenoaks",
            "Southwark",
            "Sutton",
            "Thurrock",
            "Tower Hamlets",
            "Wansdsorth",
            "Westminster",
            "Windsor & Maidenhead",
    };

    Integer[] PolutionLevels = {
            1,
            2,
            2,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1
    };

    AutoCompleteTextView autoCompleteTxt;

    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_air_pollution);

        location_live_viewer = findViewById(R.id.location_live_viewer_text);
        location_live_viewer.setText("Select a Borough");
        air_pollution_rating_viewer = findViewById(R.id.air_pollution_rating_viewer_text);
        air_pollution_rating_viewer.setText("Polution Level:");

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);

        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: " +item,Toast.LENGTH_SHORT).show();
                air_pollution_rating_viewer.setText("Polution Level in " + item + " : " + PolutionLevels[Arrays.asList(items).indexOf(item)]);
            }
        });

    }
}