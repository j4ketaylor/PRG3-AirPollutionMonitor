package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SortedAreas extends AppCompatActivity {
    String x;
    HashMap<String, String> values = new HashMap<String, String>();

    TextView sorted_areas_text;
    static TextView list_of_areas_unsorted_text;
    static TextView numbers_of_areas_text;
    static String all_areas;
    static String all_numbers;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_areas);

        sorted_areas_text = (TextView) findViewById(R.id.sorted_areas);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        sorted_areas_text.setTypeface(customFont);
        sorted_areas_text.setText("London Air Quality\n");

        list_of_areas_unsorted_text = findViewById(R.id.list_of_areas_unsorted);
        numbers_of_areas_text = findViewById(R.id.numbers_of_areas);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    all_areas = "";
                    all_numbers = "";
                    for (int i = 0; i < 33; i++) {
                        System.out.println(i);
                        x = items[i].toLowerCase().replaceAll("\\s+", "");
                        String y;
                        String data = "";
                        getAQI getInformation = new getAQI(x, data);
                        y = getInformation.AQIOutput();
                        if (Integer.parseInt(y) == 0) {
                            y = "No Info";
                        } else {
                            if (Integer.parseInt(y) <= 1) {
                                y = "\uD83D\uDFE2";
                            } else {
                                y = "\uD83D\uDFE0";
                            }
                        }
                        values.put(x, y);
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