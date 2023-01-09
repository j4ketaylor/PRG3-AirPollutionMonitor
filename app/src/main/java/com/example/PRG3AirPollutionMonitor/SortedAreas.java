package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class SortedAreas extends AppCompatActivity {
    String x;
    HashMap<String, String> values = new HashMap<String, String>();

    static TextView list_of_areas_unsorted_text;
    static TextView numbers_of_areas_text;
    static TextView sorted_areas_progress_text;
    static String all_areas;
    static String all_numbers;

    Button sorted_areas_info_button;

    String[] items = {
            "Barking and Dagenham", "Barnet", "Bexley", "Brent", "Bromley", "Camden", "City of London", "Croydon",
            "Ealing", "Enfield", "Greenwich", "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow", "Havering",
            "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea", "Kingston", "Lambeth", "Lewisham",
            "Merton", "Newham", "Redbridge", "Richmond", "Southwark", "Sutton", "Tower Hamlets", "Waltham Forest",
            "Wandsworth", "Westminster"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_areas);

        sorted_areas_progress_text = (TextView) findViewById(R.id.sorted_areas_progress);

        sorted_areas_info_button = (Button) findViewById(R.id.sorted_areas_info);
        sorted_areas_info_button.setOnClickListener(v -> {
            Intent intent = new Intent(SortedAreas.this, IndexInfo.class);
            startActivity(intent);
        });

        list_of_areas_unsorted_text = findViewById(R.id.list_of_areas_unsorted);
        numbers_of_areas_text = findViewById(R.id.numbers_of_areas);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    all_areas = "";
                    all_numbers = "";
                    //Iterates through all boroughs
                    for (int i = 0; i < 33; i++) {
                        //Tells the user how far through the checking process the app is
                        sorted_areas_progress_text.setText("Checked area " + i + " of " + 32);
                        if (i == 32) {
                            sorted_areas_progress_text.setText("");
                        }
                        System.out.println(i);
                        x = items[i].toLowerCase().replaceAll("\\s+", "");
                        String data = "";
                        //Uses getAQI class to get AQI for each borough
                        getAQI getInformation = new getAQI(x, data);
                        String[] y = getInformation.AQIOutput();
                        System.out.println(y[1]);
                        String z = null;
                        if (Integer.parseInt(y[1]) == 0) {
                            y[1] = "No Info";
                        } else {
                            //Sets AQI colour to red or green, depending on if borough is safe or not
                            if (Integer.parseInt(y[1]) <= 1) {
                                y[1] = "\uD83D\uDFE2";
                            } else {
                                y[1] = "\uD83D\uDD34";
                            }
                        }
                        values.put(x, y[1]);
                        System.out.println(values.get(x));
                        all_areas = all_areas + items[i] + "\n";
                        all_numbers = all_numbers + values.get(x) + "\n";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list_of_areas_unsorted_text.setText(all_areas);
                                numbers_of_areas_text.setText(all_numbers);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}