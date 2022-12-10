package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import java.util.Arrays;


public class LiveAirPollution extends AppCompatActivity {

    TextView location_live_viewer;
    TextView air_pollution_rating_viewer;
    String x;
    String api_output;
    String url;
    String url2;
    String formatted_url;
    JSONObject json_url;

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
            "Hammershith and Fulham",
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

        location_live_viewer = findViewById(R.id.location_live_viewer_text);
        location_live_viewer.setText("Select a Borough");
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

                class fetchData extends Thread {

                    String data = "";

                    @Override
                    public void run() {
                        try {
                            URL new_url = new URL("https://api.erg.ic.ac.uk/AirQuality/Daily/MonitoringIndex/Latest/GroupName="+x+"/Json");
                            HttpURLConnection httpURLConnection = (HttpURLConnection) new_url.openConnection();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String line;

                            while ((line = bufferedReader.readLine()) != null) {
                                System.out.println(line);
                                data = data + line;
                            }

                            JSONObject myObject = new JSONObject(data);
                            JSONObject groupName = myObject.getJSONObject("DailyAirQualityIndex").getJSONObject("LocalAuthority");
                            String information_string = "";

                            JSONArray arr = groupName.getJSONArray("Site");
                            for (int i = 0; i < arr.length(); i++)
                            {
                                JSONObject sites = arr.getJSONObject(i);
                                try {
                                    JSONObject species = sites.getJSONObject("Species");
                                    System.out.println("Species i+ " + i + " " + species);
                                    information_string = information_string + species + "\n";
                                } catch (Exception e){
                                    JSONArray species_array = sites.getJSONArray("Species");
                                    for (int j = 0; i <= species_array.length(); j++) {
                                        try {
                                            JSONObject species = species_array.getJSONObject(j);
                                            System.out.println("Species j+ " + j + " " + species);
                                            information_string = information_string + species + "\n";
                                        } catch (Exception f) {
                                            break;
                                        }

                                    }
                                }

                            }
                            System.out.println("\n" + information_string);

                            air_pollution_rating_viewer.setText(information_string);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                new fetchData().run();
            }
        });

    }





}

