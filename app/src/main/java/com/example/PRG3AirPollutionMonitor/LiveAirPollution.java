package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;
import androidx.collection.LongSparseArray;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class LiveAirPollution extends AppCompatActivity {

    TextView location_live_viewer;
    TextView air_pollution_rating_viewer;
    String x;

    String[] items = {
            "Barking and Dagenham",
            "Barnet",
            "Bexley",
            "Brent",
            "Bromley",
            "Camden",
            "City of London",
            "Croydon",
            "Ealing",
            "Enfield",
            "Greenwich",
            "Hackney",
            "Hammersmith and Fulham",
            "Haringey",
            "Harrow",
            "Havering",
            "Hillingdon",
            "Hounslow",
            "Islington",
            "Kensington and Chelsea",
            "Kingston",
            "Lambeth",
            "Lewisham",
            "Merton",
            "Newham",
            "Redbridge",
            "Richmond",
            "Southwark",
            "Sutton",
            "Tower Hamlets",
            "Waltham Forest",
            "Wandsworth",
            "Westminster"
    };

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_air_pollution);
        RequestQueue queue = Volley.newRequestQueue(LiveAirPollution.this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        AirQualitySpecies CarbonMonoxide = new AirQualitySpecies(0,0,"Carbon Monoxide", " ");
        AirQualitySpecies NitrogenDioxide = new AirQualitySpecies(0,0,"Nitrogen Dioxide", " ");
        AirQualitySpecies Ozone = new AirQualitySpecies(0,0,"Ozone", " ");
        AirQualitySpecies PM10Particulate = new AirQualitySpecies(0,0,"PM10 Particulate", " ");
        AirQualitySpecies PM25Particulate = new AirQualitySpecies(0,0,"PM2.5 Particulate", " ");
        AirQualitySpecies SulphurDioxide = new AirQualitySpecies(0,0,"Sulphur Dioxide", " ");

        location_live_viewer = findViewById(R.id.location_live_viewer_text);
        location_live_viewer.setText("Select a location:");
        air_pollution_rating_viewer = findViewById(R.id.air_pollution_rating_viewer_text);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: " +item,Toast.LENGTH_SHORT).show();

                x = items[Arrays.asList(items).indexOf(item)].toLowerCase().replaceAll("\\s+","");
                String data = "";
                getAQI getInformation = new getAQI(x, data);
                String y = getInformation.AQIOutput();
                air_pollution_rating_viewer.setText(y);


            }
        });

    }

}
