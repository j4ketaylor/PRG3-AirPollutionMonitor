package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SortedAreas extends AppCompatActivity {
    String x;
    HashMap<String, String> values = new HashMap<String, String>();

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
//        for (int i = 0; i < 2; i++) {
//            System.out.println(i);
//            x = items[i].toLowerCase().replaceAll("\\s+","");
//            String y;
//            String data = "";
//            getAQI getInformation = new getAQI("camden", data);
//            y = getInformation.AQIOutput();
//            values.put(x,y);
//        }

    }
}