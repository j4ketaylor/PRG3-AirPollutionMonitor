package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.util.Arrays;

// This activity produces a list of every borough in London and the corresponding asthma index
public class LiveAirPollution extends AppCompatActivity {

    TextView location_live_viewer;
    TextView air_pollution_rating_viewer;
    String x;

    Button live_info_button;

    String[] items = {
            "Barking and Dagenham", "Barnet", "Bexley", "Brent", "Bromley", "Camden", "City of London", "Croydon",
            "Ealing", "Enfield", "Greenwich", "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow", "Havering",
            "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea", "Kingston", "Lambeth", "Lewisham",
            "Merton", "Newham", "Redbridge", "Richmond", "Southwark", "Sutton", "Tower Hamlets", "Waltham Forest",
            "Wandsworth", "Westminster"
    };

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_air_pollution);

        live_info_button = (Button) findViewById(R.id.live_info);
        live_info_button.setOnClickListener(v -> {
            Intent intent = new Intent(LiveAirPollution.this, IndexInfo.class);
            startActivity(intent);
        });

        RequestQueue queue = Volley.newRequestQueue(LiveAirPollution.this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        location_live_viewer = findViewById(R.id.location_live_viewer_text);
        air_pollution_rating_viewer = findViewById(R.id.air_pollution_rating_viewer_text);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                //Displays selected location at the bottom of the screen
                Toast.makeText(getApplicationContext(),"Location: " +item,Toast.LENGTH_SHORT).show();

                //Input of getAQI from String array of items
                x = items[Arrays.asList(items).indexOf(item)].toLowerCase().replaceAll("\\s+","");
                String data = "";
                getAQI getInformation = new getAQI(x, data);
                String[] y = getInformation.AQIOutput();
                if (y[0] == "0") {
                    air_pollution_rating_viewer.setText("No information :(");
                } else {
                    //Gives the user information about all recorded particulates in the area from the output of getAQI
                    air_pollution_rating_viewer.setText(y[0]);
                }
            }
        });
    }
}
